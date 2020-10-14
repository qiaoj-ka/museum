package com.fehead.service;

import com.fehead.entity.Relics;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fehead.error.BusinessException;
import com.fehead.model.RelicsModel;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fehead
 * @since 2020-10-13
 */
public interface IRelicsService extends IService<Relics> {
    //根据字段搜索文物详情
    List<RelicsModel> getRelicsInfo(String field, Integer page) throws BusinessException;
}
