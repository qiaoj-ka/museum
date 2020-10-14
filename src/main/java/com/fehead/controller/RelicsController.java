package com.fehead.controller;


import com.fehead.entity.Relics;
import com.fehead.error.BusinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.response.CommonReturnType;
import com.fehead.service.IRelicsService;
import com.fehead.model.RelicsModel;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.util.List;

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

    /**
     * 首页搜索，按照字段获取列表
     * @return
     */
    @GetMapping("/get/Relics")
    @ApiOperation("根据字段搜索文物详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "field",value = "样品编号/原编号/文物名称"),
            @ApiImplicitParam(name = "page",value = "页数（默认一页10条数据）")
    })

    public CommonReturnType getRelics(String field,Integer page) throws BusinessException {
        List<RelicsModel> list=relicsService.getRelicsInfo(field,page);
        return CommonReturnType.creat(list);
    }

    /**
     * 插入数据
     * @param relicsOrgId
     * @param relicsId
     * @param relicsName
     * @param relicsType
     * @param relicsBirthPlace
     * @param relicsBirthTime
     * @param relicsBurnTime
     * @param relicsKiln
     * @param relicsProvider
     * @param relicsStorePlace
     * @param relicsDesc
     * @param relicsPicture
     * @param relicsFilepath
     * @param relicsChemical
     * @return
     * @throws BusinessException
     * @throws IOException
     */
    @PostMapping("uploadAllData")
    @ApiOperation("上传文物信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "relicsOrgId",value = "文物原编号"),
            @ApiImplicitParam(name = "relicsId",value = "文物编号"),
            @ApiImplicitParam(name = "relicsName",value = "文物名称"),
            @ApiImplicitParam(name = "relicsType",value = "文物分类"),
            @ApiImplicitParam(name = "relicsBirthPlace",value = "文物出土地点"),
            @ApiImplicitParam(name = "relicsBirthTime",value = "文物出土时间"),
            @ApiImplicitParam(name = "relicsBurnTime",value = "文物烧造时间"),
            @ApiImplicitParam(name = "relicsKiln",value = "文物窑口"),
            @ApiImplicitParam(name = "relicsProvider",value = "文物提供者"),
            @ApiImplicitParam(name = "relicsStorePlace",value = "文物存放单位"),
            @ApiImplicitParam(name = "relicsDesc",value = "文物描述"),
    })
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
            @ApiParam(value ="上传文物图片",required = true) MultipartFile relicsPicture,
            @ApiParam(value ="上传文物3D文件",required = true)MultipartFile relicsFilepath,
            @ApiParam(value ="上传文物化学成分文件",required = true)MultipartFile relicsChemical
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
