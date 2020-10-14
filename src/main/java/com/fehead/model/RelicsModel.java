package com.fehead.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RelicsModel implements Serializable {
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
     * 文物分类名称
     */
    private String relicsTypeName;

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

    @Override
    public String toString() {
        return "RelicsModel{" +
                "id=" + id +
                ", relicsOrgId='" + relicsOrgId + '\'' +
                ", relicsId='" + relicsId + '\'' +
                ", relicsName='" + relicsName + '\'' +
                ", relicsType=" + relicsType +
                ", relicsTypeName='" + relicsTypeName + '\'' +
                ", relicsBirthPlace='" + relicsBirthPlace + '\'' +
                ", relicsBirthTime='" + relicsBirthTime + '\'' +
                ", relicsBurnTime='" + relicsBurnTime + '\'' +
                ", relicsKiln='" + relicsKiln + '\'' +
                ", relicsProvider='" + relicsProvider + '\'' +
                ", relicsStorePlace='" + relicsStorePlace + '\'' +
                ", relicsPicture='" + relicsPicture + '\'' +
                ", relicsFilepath='" + relicsFilepath + '\'' +
                ", relicsDesc='" + relicsDesc + '\'' +
                ", relicsChemical='" + relicsChemical + '\'' +
                '}';
    }
}
