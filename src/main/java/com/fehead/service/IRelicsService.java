package com.fehead.service;

import com.fehead.entity.Relics;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fehead
 * @since 2020-10-13
 */
public interface IRelicsService extends IService<Relics> {
    /**
     * 插入数据
     * @param relics
     * @return
     */
    int insertData(Relics relics);

    /**
     * 查询数据是否存在
     * @param relicsId
     * @return
     */
    int selectRelicsById(String relicsId);
}
