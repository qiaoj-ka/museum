package com.fehead.service;

import com.fehead.entity.Chemical;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fehead.model.ChemicalModel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fehead
 * @since 2020-10-15
 */
public interface IChemicalService extends IService<Chemical> {

    ChemicalModel getChemicalModelByRelicsId(String id);

    int insertData(Chemical chemical);
}
