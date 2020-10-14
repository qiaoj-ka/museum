package com.fehead.controller;


import com.fehead.entity.Relics;
import com.fehead.error.BusinessException;
import com.fehead.response.CommonReturnType;
import com.fehead.service.IRelicsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @Autowired
    private IRelicsService relicsService;
    /**
     * 首页搜索，按照字段获取列表
     * @return
     */
    @GetMapping("/get/Relics")
    @ApiOperation("根据字段搜索文物详情")
    @ApiImplicitParam(name = "field",value = "样品编号/原编号/文物名称")
    public CommonReturnType getRelics(String field) throws BusinessException {
        List<Relics> list=relicsService.getRelicsInfo(field);
        return CommonReturnType.creat(list);
    }

}
