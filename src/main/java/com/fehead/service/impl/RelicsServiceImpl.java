package com.fehead.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fehead.entity.Relics;
import com.fehead.mapper.RelicsMapper;
import com.fehead.service.IRelicsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired RelicsMapper relicsMapper;

    @Override
    public int insertData(Relics relics) {
        return relicsMapper.insert(relics);
    }

    @Override
    public int selectRelicsById(String relicsId) {
        return relicsMapper.selectCount(new QueryWrapper<Relics>().eq("relics_id",relicsId));
    }
}
