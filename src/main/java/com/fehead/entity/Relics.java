package com.fehead.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author fehead
 * @since 2020-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Relics implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 样品原编号
     */
    private String relicsOrgId;

    /**
     * 样品编号
     */
    private String relicsId;

    /**
     * 文物名称
     */
    private String relicsName;

    /**
     * 文物分类
     */
    private Integer relicsType;

    /**
     * 出土地点
     */
    private String relicsBirthPlace;

    /**
     * 出土时间
     */
    private String relicsBirthTime;

    /**
     * 烧造时间
     */
    private String relicsBurnTime;

    /**
     * 窑口
     */
    private String relicsKiln;

    /**
     * 文物提供者
     */
    private String relicsProvider;

    /**
     * 文物存放单位
     */
    private String relicsStorePlace;

    /**
     * 文物图片路径
     */
    private String relicsPicture;

    /**
     * 文物3D文件路径
     */
    private String relicsFilepath;

    /**
     * 文物描述
     */
    private String relicsDesc;

    /**
     * 文物化学组成文件路径
     */
    private String relicsChemical;


}
