package com.fehead.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fehead.entity.Chemical;
import com.fehead.mapper.ChemicalMapper;
import com.fehead.model.ChemicalModel;
import com.fehead.service.IChemicalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fehead
 * @since 2020-10-15
 */
@Service
public class ChemicalServiceImpl extends ServiceImpl<ChemicalMapper, Chemical> implements IChemicalService {

    @Autowired
    private ChemicalMapper chemicalMapper;

    @Override
    public ChemicalModel getChemicalModelByRelicsId(String id) {
        ChemicalModel chemicalModel=new ChemicalModel();
        LambdaQueryWrapper<Chemical> queryWrapper=new QueryWrapper().lambda();
        queryWrapper.eq(Chemical::getRelicsId,id);
        Chemical chemical=chemicalMapper.selectOne(queryWrapper);
        BeanUtils.copyProperties(chemical,chemicalModel);
        return chemicalModel;
    }
}
