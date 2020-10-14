package com.fehead.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fehead.entity.Relics;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fehead.model.RelicsModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fehead
 * @since 2020-10-13
 */

public interface RelicsMapper extends BaseMapper<Relics> {
    IPage<RelicsModel> getRelicsInfo(Page<RelicsModel> rpage);
}
