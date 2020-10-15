package com.fehead.controller;


import com.fehead.utility.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fehead
 * @since 2020-10-15
 */
@CrossOrigin
@RestController
@RequestMapping("/fehead/chemical")
@Api(tags = "文物化学成分")
public class ChemicalController {

    @Autowired
    private ExcelUtil excelUtil;
    /**
     *
     * @param response
     */
    @GetMapping("/ExcelDownload/chemical/model")
    @ApiOperation("下载文物元素上传模板")
    public void excelDownloadChemicalModel(HttpServletResponse response) throws IOException {
        //表头数据
        String[] header={"Na2O", "MgO", "Al2O3", "SiO2", "P2O5","SO3", "Cl" ,"K2O",
                "CaO","TiO2", "Cr2O3" ,"MnO","Fe2O3" ,"CoO" ,"NiO" ,"CuO" ,"ZnO",
                "Rb2O","SrO","SnO2","Sb2O3","PbO","BaO","Ti","Cr",
                "Mn", "Co", "Ni", "Cu", "Zn", "Rb", "Sr", "Sn", "Sb", "Pb", "Ba"};
        String fileName="化学元素上传模板";
        //fileName= URLEncoder.encode(fileName,"UTF-8");
        excelUtil.exportExcel(response,header,new ArrayList<>(),fileName,fileName,15);
    }

}
