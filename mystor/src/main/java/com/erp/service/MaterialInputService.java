package com.erp.service;

import com.erp.entity.MaterialInput;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.form.MaterialInputSearchForm;
import com.erp.util.ImportResult;
import com.erp.util.MaterialInputExportModel;
import com.erp.util.PageObject;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-12-25
 */
@Service
public interface MaterialInputService extends IService<MaterialInput> {
    public ImportResult excelImport(InputStream inputStream);
    public PageObject materialInputList(PageObject pageObject, MaterialInputSearchForm materialInputSearchForm);
    public List<MaterialInputExportModel> getExportList();
    public boolean verify(Integer status,String idArray);
    public boolean delete(String idArray);
}
