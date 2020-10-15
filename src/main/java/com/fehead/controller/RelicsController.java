package com.fehead.controller;


import com.fehead.entity.Chemical;
import com.fehead.entity.Relics;
import com.fehead.error.BusinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.model.ChemicalModel;
import com.fehead.model.RelicsModelData;
import com.fehead.response.CommonReturnType;
import com.fehead.service.IChemicalService;
import com.fehead.service.IRelicsService;
import com.fehead.model.RelicsModel;
import io.swagger.annotations.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
@CrossOrigin
@RestController
@RequestMapping("/fehead/relics")
@Api(tags = "文物信息详情")
public class RelicsController extends BaseController{
    public static final String serverPath="http://47.92.196.104:8090";
    private static final String linuxPicPath = "/opt/relicsPictures";
    private static final String linuxFilePath = "/opt/relicsFile";
    private static final String linuxChemicalPath = "/opt/relicsFile";

    @Autowired
    IRelicsService relicsService;

    @Autowired
    IChemicalService chemicalService;

    @Resource
    RedisTemplate<String,RelicsModelData> redisTemplate;

    /**
     * 首页搜索，按照字段获取列表
     * @return
     */
    @GetMapping("/get/Relics")
    @ApiOperation("根据字段搜索文物详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "field",value = "样品编号/原编号/文物名称"),
            @ApiImplicitParam(name = "page",value = "页数（默认一页5条数据）",dataType = "int")
    })
    public CommonReturnType getRelics(String field,Integer page) throws BusinessException {
        List<RelicsModel> list=relicsService.getRelicsInfo(field,page);
        return CommonReturnType.creat(list);
    }
    @GetMapping("/getRelicsById")
    @ApiOperation("根据id查找相应文物信息")
    @ApiImplicitParam(name = "id",value = "这里的id指列表中返回的索引id，不是文物id",dataType = "int")
    public CommonReturnType selectRelicsInfo(Integer id) throws BusinessException {
        RelicsModelData relicsModelData=redisTemplate.opsForValue().get("relics"+id);
        if(relicsModelData!=null){
            return CommonReturnType.creat(relicsModelData);
        }
        relicsModelData=new RelicsModelData();
        RelicsModel relicsModel=getRelicsModel(id);
        ChemicalModel chemicalModel=getChemicalModelByRelicsId(relicsModel.getRelicsId());
        relicsModelData.setRelicsModel(relicsModel);
        relicsModelData.setChemicalModel(chemicalModel);
        redisTemplate.opsForValue().set("relics"+id,relicsModelData);
        return CommonReturnType.creat(relicsModelData);
    }

    /**
     * 通过文物唯一id获取其对应的化学成分
     * @param id
     * @return
     */
    public ChemicalModel getChemicalModelByRelicsId(String id) throws BusinessException {
        //先从redis中查找
        ChemicalModel chemicalModel;
        try {
            chemicalModel=chemicalService.getChemicalModelByRelicsId(id);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.DATA_SELECT_ERROR,"文物化学元素查询失败");
        }
        if(chemicalModel==null){
            throw new BusinessException(EmBusinessError.DATA_SELECT_ERROR,"该文物未上传其化学元素");
        }
        return chemicalModel;
    }

    /**
     * 获取文物详细数据
     * @param id
     * @return
     */
    public RelicsModel getRelicsModel(Integer id) throws BusinessException {
        RelicsModel relicsModel;
        try {
            relicsModel=relicsService.getRelicsById(id);
        }catch (Exception e){
            throw new BusinessException(EmBusinessError.DATA_SELECT_ERROR,"获取文物详细信息失败");
        }
        if(relicsModel==null){
            throw new BusinessException(EmBusinessError.DATA_SELECT_ERROR,"没有找到该文物信息");
        }
        return relicsModel;
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
            @ApiImplicitParam(name = "relicsType",value = "文物分类",dataType = "int"),
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
//        String PicPath = "E:/museum/picture"; //本地图片路径
//        String FilePath = "E:/museum/file"; //本地文件路径
//        String ChemicalPath = "E:/museum/chemical"; //本地化学组成文件路径
        String PicPath = linuxPicPath;
        String FilePath = linuxFilePath;
        String ChemicalPath = linuxChemicalPath;
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
        String d = serverPath + "/" + picName;
        String df = serverPath + "/" + FileName;
        String dc = serverPath + "/" + ChemicalName;
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
        //封装成完整化学成分对象
        Chemical chemical = analyzeExcel(filec);
        chemical.setRelicsId(relicsId);
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
        int insertData = chemicalService.insertData(chemical);
        return CommonReturnType.creat("插入状态:"+insert+" 插入化学表: "+insertData+" 图片路径:"+d+"  "+"文件路径:"+df+"  "+"化学文件路径:"+dc);
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

    /**
     * 解析excel封装为Chemical对象
     * @param file
     * @return
     * @throws IOException
     */
    public Chemical analyzeExcel(File file) throws IOException {
        Chemical chemical=new Chemical();
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
        //3、得到Excel工作表对象
        HSSFSheet sheetAt = workbook.getSheetAt(0);
        //4、循环读取表格数据
        for (Row row : sheetAt) {
            //首行（即表头）不读取
            if (row.getRowNum() == 0) {
                continue;
            }
            //读取当前行中单元格数据，索引从0开始
            for (int i = 0; i <= 35; i++) {
                row.getCell(i).setCellType(CellType.STRING);
                String value = row.getCell(i).getStringCellValue();
               if(i==0) chemical.setNa2O(value);
               if(i==1) chemical.setMgO(value);
               if(i==2) chemical.setAl2O3(value);
               if(i==3) chemical.setSiO2(value);
               if(i==4) chemical.setP2o5(value);
               if(i==5) chemical.setSo3(value);
               if(i==6) chemical.setCl(value);
               if(i==7) chemical.setK2o(value);
               if(i==8) chemical.setCaO(value);
               if(i==9) chemical.setTiO2(value);
               if(i==10) chemical.setCr2O3(value);
               if(i==11) chemical.setMnO(value);
               if(i==12) chemical.setFe2O3(value);
               if(i==13) chemical.setCoO(value);
               if(i==14) chemical.setNiO(value);
               if(i==15) chemical.setCuO(value);
               if(i==16) chemical.setZnO(value);
               if(i==17) chemical.setRb2O(value);
               if(i==18) chemical.setSrO(value);
               if(i==19) chemical.setSnO2(value);
               if(i==20) chemical.setSb2O3(value);
               if(i==21) chemical.setPbO(value);
               if(i==22) chemical.setBaO(value);
               if(i==23) chemical.setTi(value);
               if(i==24) chemical.setCr(value);
               if(i==25) chemical.setMn(value);
               if(i==26) chemical.setCo(value);
               if(i==27) chemical.setNi(value);
               if(i==28) chemical.setCu(value);
               if(i==29) chemical.setZn(value);
               if(i==30) chemical.setRb(value);
               if(i==31) chemical.setSr(value);
               if(i==32) chemical.setSn(value);
               if(i==33) chemical.setSb(value);
               if(i==34) chemical.setPb(value);
               if(i==35) chemical.setBa(value);
            }
        }
        return chemical;
    }
}
