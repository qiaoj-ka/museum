package com.fehead.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fehead.entity.Relics;
import com.fehead.error.BusinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.mapper.RelicsMapper;
import com.fehead.model.RelicsModel;
import com.fehead.service.IRelicsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fehead
 * @since 2020-10-13
 */
@Service
public class RelicsServiceImpl extends ServiceImpl<RelicsMapper, Relics> implements IRelicsService {

    @Autowired
    private RelicsMapper relicsMapper;
    @Override
    public List<RelicsModel> getRelicsInfo(String field,Integer page) throws BusinessException {
        List<RelicsModel> relics=new ArrayList<>();
        try {
            Page<RelicsModel> rpage=new Page<>(page,2);
            IPage<RelicsModel> iPage=relicsMapper.getRelicsInfo(rpage);
            relics=iPage.getRecords();
        }catch (Exception e){
             throw new BusinessException(EmBusinessError.DATA_SELECT_ERROR);
        }
        if(relics.isEmpty()){
            throw new BusinessException(EmBusinessError.DATA_SELECT_ERROR,"查询结果为空");
        }
        return relics;
    }
}
