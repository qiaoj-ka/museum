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
    /**
     * 通过模糊查询，查找对应的文物信息列表
     * @param rpage
     * @param field
     * @return
     */
    IPage<RelicsModel> getRelicsInfo(Page<RelicsModel> rpage,String field);

    /**
     * 通过id查找某一个具体文物的详细信息
     * @return
     */
    RelicsModel getRelicsById(Integer id);
}
