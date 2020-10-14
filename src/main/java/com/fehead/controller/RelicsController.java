package com.fehead.controller;


import com.fehead.entity.Relics;
import com.fehead.error.BusinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.response.CommonReturnType;
import com.fehead.service.IRelicsService;
import com.fehead.service.impl.RelicsServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fehead
 * @since 2020-10-13
 */
@RestController
@RequestMapping("/fehead/relics")
@Api(tags = "文物信息详情")
public class RelicsController extends BaseController{
    private static final String linuxPicPath = "/opt/museumData/picture";
    private static final String linuxFilePath = "/opt/museumData/file";
    private static final String linuxChemicalPath = "/opt/museumData/Chemical";

    @Autowired
    IRelicsService relicsService;

    @PostMapping("uploadAllData")
    public CommonReturnType uploadAllData(
            String relicsOrgId,
            String relicsId,
            String relicsName,
            int relicsType,
            String relicsBirthPlace,
            String relicsBirthTime,
            String relicsBurnTime,
            String relicsKiln,
            String relicsProvider,
            String relicsStorePlace,
            String relicsDesc,
            MultipartFile relicsPicture,
            MultipartFile relicsFilepath,
            MultipartFile relicsChemical
    ) throws BusinessException, IOException {
        if(
                stringJudge(relicsOrgId)||
                stringJudge(relicsId)||
                stringJudge(relicsName)||
                stringJudge(relicsBirthPlace)||
                stringJudge(relicsBirthTime)||
                stringJudge(relicsBurnTime)||
                stringJudge(relicsKiln)||
                stringJudge(relicsProvider)||
                stringJudge(relicsStorePlace)||
                stringJudge(relicsDesc)
        ){
            throw new BusinessException(EmBusinessError.DATA_INSERT_ERROR,"插入数据字段不能为空");
        }
        if(relicsService.selectRelicsById(relicsId)!=0){ //如果存在就不能重复添加
            throw new BusinessException(EmBusinessError.DATA_INSERT_ERROR,"已存在数据,不能重复添加");
        }
        String PicPath = "E:/museum/picture"; //本地图片路径
        String FilePath = "E:/museum/file"; //本地文件路径
        String ChemicalPath = "E:/museum/chemical"; //本地化学组成文件路径
//        String PicPath = linuxFilePath;
        File dirp = new File(PicPath);
        File dirf = new File(FilePath);
        File dirc = new File(ChemicalPath);
        if (!dirp.exists()) {
            dirp.mkdir();
        }
        if (!dirf.exists()) {
            dirf.mkdir();
        }
        if (!dirc.exists()) {
            dirc.mkdir();
        }
        //生成一个新的文件名fileName
        String picName = relicsId+"_pic" + "." + "jpg";
        String FileName =  relicsId+"_file"+relicsFilepath.getOriginalFilename().substring(relicsFilepath.getOriginalFilename().lastIndexOf("."));
        String ChemicalName = relicsId+"_chem"+relicsChemical.getOriginalFilename().substring(relicsChemical.getOriginalFilename().lastIndexOf("."));
        String d = PicPath + "/" + picName;
        String df = FilePath + "/" + FileName;
        String dc = ChemicalPath + "/" + ChemicalName;
        File file = new File(PicPath, picName);
        File filef = new File(FilePath, FileName);
        File filec = new File(ChemicalPath, ChemicalName);
        try {
            relicsPicture.transferTo(file);
            relicsFilepath.transferTo(filef);
            relicsChemical.transferTo(filec);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.DATA_INSERT_ERROR);
        }
        //封装成对象
        Relics relics=new Relics();
        relics.setRelicsOrgId(relicsOrgId);
        relics.setRelicsId(relicsId);
        relics.setRelicsName(relicsName);
        relics.setRelicsType(relicsType);
        relics.setRelicsBirthPlace(relicsBirthPlace);
        relics.setRelicsBirthTime(relicsBirthTime);
        relics.setRelicsBurnTime(relicsBurnTime);
        relics.setRelicsKiln(relicsKiln);
        relics.setRelicsProvider(relicsProvider);
        relics.setRelicsStorePlace(relicsStorePlace);
        relics.setRelicsDesc(relicsDesc);
        relics.setRelicsPicture(d);
        relics.setRelicsFilepath(df);
        relics.setRelicsChemical(dc);
        int insert = relicsService.insertData(relics);
        return CommonReturnType.creat("插入状态:"+insert+"图片路径:"+d+"  "+"文件路径:"+df+"  "+"化学文件路径:"+dc);
    }

    /**
     * 不为空字符串或null
     * @param str
     * @return
     */
    public boolean stringJudge(String str){
        if(str.length()==0||str==null) return true;
        return false;
    }
}
