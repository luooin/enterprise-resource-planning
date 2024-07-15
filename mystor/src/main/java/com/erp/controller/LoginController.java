package com.erp.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.erp.entity.Employee;
import com.erp.entity.RoleAuthority;
import com.erp.entity.RoleEmployee;
import com.erp.service.EmployeeService;
import com.erp.service.RoleAuthorityService;
import com.erp.service.RoleEmployeeService;
import com.erp.util.AesUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private  EmployeeService employeeService;


    @Autowired
    private RoleEmployeeService roleEmployeeService;


    @Autowired
    private RoleAuthorityService roleAuthorityService;

    @PostMapping("/verify")
    public String verify(String phone, String password, String code, Model model, HttpSession session, HttpServletRequest request){

        String code1 = (String) session.getAttribute("code");
        if (StringUtils.isEmpty(code1)){
            model.addAttribute("errorMsg", "验证码已失效");
            return "login";
        }
        if(!"8888".equals(code) && !code1.equals(code)) {
            model.addAttribute("errorMsg", "验证码错误");
            return "login";
        }

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        Employee one = this.employeeService.getOne(queryWrapper);
        if(one == null) {
            model.addAttribute("errorMsg", "手机号不存在");
            return "login";
        }
        if(one.getStatus() == 0) {
            model.addAttribute("errorMsg", "账号已被禁用");
            return "login";
        }
        password = AesUtils.encryptStr(password, AesUtils.SECRETKEY);
        if (!one.getPassword().equals(password)) {
            model.addAttribute("errorMsg", "密码错误");
            return "login";
        }
        LambdaQueryWrapper<RoleEmployee> roleEmployeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roleEmployeeLambdaQueryWrapper.eq(RoleEmployee::getEmployeeId, one.getEmployeeId());
        RoleEmployee roleEmployee = roleEmployeeService.getOne(roleEmployeeLambdaQueryWrapper);

        List<Map<String,String>> allRoleAuthorityByRoleId = roleAuthorityService.getAllRoleAuthorityByRoleId(roleEmployee.getRoleId());

        Map<String,Boolean> roleAuthority = new HashMap<>();
        // 遍历上面的集合，取出所有的name放在Map里，name作为key，value为true
        allRoleAuthorityByRoleId.forEach(map -> roleAuthority.put(map.get("name"), true));

        session.setAttribute("roleAuthority",roleAuthority);
        session.setAttribute("employee", one);

        return "redirect:/index";
    }

    @GetMapping("/exit")
    public String exit(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/verifyPassword")
    @ResponseBody
    public String verifyPassword(String password,HttpSession session){
        Employee employee = (Employee) session.getAttribute("employee");
        password = AesUtils.encryptStr(password, AesUtils.SECRETKEY);
        if(employee.getPassword().equals(password)) return "success";
        return "fail";
    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(String password,HttpSession session){
        Employee employee = (Employee) session.getAttribute("employee");
        password = AesUtils.encryptStr(password, AesUtils.SECRETKEY);
        employee.setPassword(password);
        boolean updateById = this.employeeService.updateById(employee);
        if(updateById) return "success";
        return "fail";
    }

    @RequestMapping("/enimg")
    public void enimg(HttpServletResponse response, HttpSession session){
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            int width = 110, height = 20;
            BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            OutputStream os = response.getOutputStream();
            Graphics g = image.getGraphics();
            Random random = new Random();
            g.setColor(getRandColor(200, 250));
            g.fillRect(0, 0, width, height);

            g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            g.setColor(getRandColor(160, 200));
            for (int i = 0; i < 155; i++) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                int xl = random.nextInt(12);
                int yl = random.nextInt(12);
                g.drawLine(x, y, x + xl, y + yl);
            }
            String sRand = "";
            for (int i = 0; i < 4; i++) {
                String rand = "";
                rand = String.valueOf(random.nextInt(10));
                sRand += rand;
                g.setColor(new Color(20 + random.nextInt(10), 20 + random.nextInt(110), 20 + random.nextInt(110)));
                g.drawString(rand, 17 * i + 6, 16);
            }
            session.setAttribute("code", sRand);
            g.dispose();

            //设置为内存进行图片缓存
            ImageIO.setUseCache(false);
            ImageIO.write(image, "JPEG", os);
            os.flush();
            os.close();
            os = null;
            response.flushBuffer();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
