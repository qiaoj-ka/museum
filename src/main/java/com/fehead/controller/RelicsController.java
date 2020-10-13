package com.fehead.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

}
