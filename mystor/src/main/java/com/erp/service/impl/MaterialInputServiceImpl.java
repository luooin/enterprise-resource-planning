package com.erp.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.entity.*;
import com.erp.form.MaterialInputSearchForm;
import com.erp.mapper.*;
import com.erp.mo.MaterialInputMO;
import com.erp.service.MaterialInputService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2023-12-25
 */
@Service
public class MaterialInputServiceImpl extends ServiceImpl<MaterialInputMapper, MaterialInput> implements MaterialInputService {
    @Autowired
    private MaterialInputMapper materialInputMapper;
    @Autowired
    private MaterialMapper materialMapper;
    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private StorageMapper storageMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    /*
    数据导入
     */
    @Override
    public ImportResult excelImport(InputStream inputStream) {
        List<MaterialInputExcelModel> list = new ArrayList<>();
        try {
            EasyExcel.read(inputStream)
                    .head(MaterialInputExcelModel.class)
                    .sheet()
                    .registerReadListener(new AnalysisEventListener<MaterialInputExcelModel>() {
                        @Override
                        public void invoke(MaterialInputExcelModel excelData, AnalysisContext analysisContext) {
                            list.add(excelData);
                        }

                        @Override
                        public void doAfterAllAnalysed(AnalysisContext analysisContext) {

                        }
                    }).doRead();
        } catch (Exception e) {
            e.getStackTrace();
        }
        //把List数据存入数据库，持久化
        ImportResult importResult = new ImportResult();
        for (MaterialInputExcelModel materialInputExcelModel : list) {
            int row = 0;
            MaterialInput materialInput = new MaterialInput();
            BeanUtils.copyProperties(materialInputExcelModel, materialInput);
            //查询物料id
            QueryWrapper<Material> materialQueryWrapper = new QueryWrapper<>();
            materialQueryWrapper.eq("material_code", materialInput.getMaterialCode());
            Material material = this.materialMapper.selectOne(materialQueryWrapper);
            if (material == null) {
                importResult.setCode(-1);
                importResult.setMsg("【第" + row + "行错误】，物料不存在！");
                return importResult;
            }
            materialInput.setMaterialId(material.getMaterialId());
            //查询供应商名称id
            QueryWrapper<Supplier> supplierQueryWrapper = new QueryWrapper<>();
            supplierQueryWrapper.eq("supplier_code", materialInputExcelModel.getSupplierCode());
            Supplier supplier = this.supplierMapper.selectOne(supplierQueryWrapper);
            if (supplier == null) {
                importResult.setCode(-1);
                importResult.setMsg("【第" + row + "行错误】，供应商不存在！");
                return importResult;
            }
            materialInput.setSupplierId(supplier.getSupplierId());
            materialInput.setSupplierName(supplier.getSupplierName());
            //查询仓库名称
            QueryWrapper<Storage> storageQueryWrapper = new QueryWrapper<>();
            storageQueryWrapper.eq("storage_code", materialInputExcelModel.getStorageCode());
            Storage storage = this.storageMapper.selectOne(storageQueryWrapper);
            if (storage == null) {
                importResult.setCode(-1);
                importResult.setMsg("【第" + row + "行错误】，仓库不存在！");
                return importResult;
            }
            materialInput.setStorageName(storage.getStorageName());
            materialInput.setStorageId(storage.getStorageId());
            //登录用户
            materialInput.setUserName("张三");
            materialInput.setStatus(0);
            //日期
            materialInput.setOrderDate(CommonUtils.parseString(materialInputExcelModel.getOrderDate()));

            QueryWrapper<MaterialInput> materialInputQueryWrapper = new QueryWrapper<>();
            materialInputQueryWrapper.eq("batch_no", materialInput.getBatchNo());
            MaterialInput materialInput1 = this.materialInputMapper.selectOne(materialInputQueryWrapper);
            if (materialInput1 != null) {
                Integer materialInputId = materialInput1.getMaterialInputId();
                //判断订单状态，如果是未审核则进行覆盖
                if (materialInput1.getStatus().equals(0)) {
                    //覆盖
                    BeanUtils.copyProperties(materialInput, materialInput1);
                    materialInput1.setMaterialInputId(materialInputId);
                    int updateById = this.materialInputMapper.updateById(materialInput1);
                    if (updateById != 1) {
                        importResult.setCode(-1);
                        importResult.setMsg("更新失败！");
                        return importResult;
                    }
                }
            } else {
                int insert = this.materialInputMapper.insert(materialInput);
                if (insert != 1) {
                    importResult.setCode(-1);
                    importResult.setMsg("保存失败！");
                    return importResult;
                }
            }
        }
        importResult.setCode(0);
        importResult.setMsg("保存成功!");
        return importResult;
    }

    /*
    数据分页
     */
    @Override
    public PageObject materialInputList(PageObject pageObject, MaterialInputSearchForm materialInputSearchForm) {
        Page<MaterialInput> page = new Page<>(pageObject.getCurrent(), pageObject.getSize());
        boolean supplierIdFlag = materialInputSearchForm.getSupplierId() != null;
        boolean statusFalg = materialInputSearchForm.getStatus() != null;
        QueryWrapper<MaterialInput> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(supplierIdFlag, "supplier_id", materialInputSearchForm.getSupplierId())
                .eq(StringUtils.isNotBlank(materialInputSearchForm.getMaterialName()), "material_name", materialInputSearchForm.getMaterialName())
                .eq(StringUtils.isNotBlank(materialInputSearchForm.getBatchNo()), "batch_no", materialInputSearchForm.getBatchNo())
                .eq(statusFalg, "status", materialInputSearchForm.getStatus())
                .between(StringUtils.isNotBlank(materialInputSearchForm.getOrderDate1()) && StringUtils.isNotBlank(materialInputSearchForm.getOrderDate2()),
                        "order_date", materialInputSearchForm.getOrderDate1(), materialInputSearchForm.getOrderDate2());
        Page<MaterialInput> resultPage = this.materialInputMapper.selectPage(page, queryWrapper);
        PageObject result = new PageObject();
        result.setCurrent(resultPage.getCurrent());
        result.setSize(resultPage.getSize());
        result.setTotal(resultPage.getTotal());
        result.setData(resultPage.getRecords());
        return result;
    }

    @Override
    public List<MaterialInputExportModel> getExportList() {
        List<MaterialInput> materialInputs = this.materialInputMapper.selectList(null);
        List<MaterialInputExportModel> list = new ArrayList<>();
        for (MaterialInput materialInput : materialInputs) {
            MaterialInputExportModel model = new MaterialInputExportModel();
            BeanUtils.copyProperties(materialInput, model);
            //日期
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String format = materialInput.getOrderDate().format(dateTimeFormatter);
            model.setOrderDate(format);
            //状态
            String status = "";
            switch (materialInput.getStatus()) {
                case 0:
                    status = "未审核";
                    break;
                case 1:
                    status = "已审核";
                    break;
                case 2:
                    status = "已入库";
                    break;
            }
            model.setStatus(status);
            list.add(model);
        }
        return list;
    }
    @Override
    public boolean verify(Integer status,String idArray){
        boolean flag=false;
        String[] ids=idArray.split(",");
        switch (status){
            case 1:
                //审核
                List<Integer> idList=new ArrayList<>();
                for (String id:ids){
                    idList.add(Integer.parseInt(id));
//                    MaterialInput materialInput=this.materialInputMapper.selectById(id);
//                    materialInput.setStatus(status);
//                    int updateById = this.materialInputMapper.updateById(materialInput);
//                    if(updateById!=1) return  false;
                }
                MaterialInputMO materialInputMO=new MaterialInputMO();
                materialInputMO.setIds(idList);
                materialInputMO.setStatus(status);
                int verify = this.materialInputMapper.verify(materialInputMO);
                if(verify==0) return false;
                flag=true;
                break;
            case 2:
                //入库:
                for (String id:ids){
                    MaterialInput materialInput=this.materialInputMapper.selectById(id);
                    //将数据同步到orders和ordersDetail表中
                    Orders orders=new Orders();
                    BeanUtils.copyProperties(materialInput,orders);
                    Integer count = this.ordersMapper.selectCount(null);
                    orders.setOrderNo(CommonUtils.createOrderNo(count,1));
                    orders.setInvalid(1);
                    orders.setVerifyPerson("李四");
                    orders.setVerifyDate(LocalDateTime.now());
                    orders.setEmployeeName(materialInput.getUserName());
                    orders.setOrderType(1);
                    int insert=this.ordersMapper.insert(orders);
                    if(insert!=1) return false;
                    //将数据同步到orderDetail中
                    OrderDetail orderDetail=new OrderDetail();
                    BeanUtils.copyProperties(materialInput,orderDetail);
                    orderDetail.setOrderNo(orders.getOrderNo());
                    orderDetail.setOrderFlag("正常");

                    int insert1 = this.orderDetailMapper.insert(orderDetail);
                    if(insert1!=1) return false;
                    materialInput.setStatus(status);
                    //生产订单号

                    materialInput.setOrderNo(orders.getOrderNo());
                    int updateById = this.materialInputMapper.updateById(materialInput);
                    if(updateById!=1) return  false;
                }
                flag=true;
                break;
        }
        return  flag;
    }
    @Override
    public boolean delete(String idArray) {
        String[] ids = idArray.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String id : ids) {
            idList.add(Integer.parseInt(id));
        }
        int batchIds = this.materialInputMapper.deleteBatchIds(idList);
        if(batchIds == 0) return false;
        return true;
    }
}
