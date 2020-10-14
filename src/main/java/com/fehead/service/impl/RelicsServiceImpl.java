package com.fehead.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fehead.entity.Relics;
import com.fehead.error.BusinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.mapper.RelicsMapper;
import com.fehead.service.IRelicsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public List<Relics> getRelicsInfo(String field) throws BusinessException {
        List<Relics> relics=new ArrayList<>();
        try {
            LambdaQueryWrapper<Relics> queryWrapper=new QueryWrapper().lambda();
            queryWrapper.like(Relics::getRelicsName,field).or()
                    .like(Relics::getRelicsId,field).or()
                    .like(Relics::getRelicsOrgId,field);
            relics=relicsMapper.selectList(queryWrapper);
        }catch (Exception e){
             throw new BusinessException(EmBusinessError.DATA_SELECT_ERROR);
        }
        if(relics.isEmpty()){
            throw new BusinessException(EmBusinessError.DATA_SELECT_ERROR,"查询结果为空");
        }
        return relics;
    }
}
