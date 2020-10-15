package com.fehead.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author fehead
 * @since 2020-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Chemical implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer relicsId;

    @TableField("Na2O")
    private String Na2O;

    @TableField("MgO")
    private String MgO;

    @TableField("Al2O3")
    private String Al2O3;

    @TableField("SiO2")
    private String SiO2;

    @TableField("P2O5")
    private String p2o5;

    @TableField("SO3")
    private String so3;

    @TableField("Cl")
    private String Cl;

    @TableField("K2O")
    private String k2o;

    @TableField("CaO")
    private String CaO;

    @TableField("TiO2")
    private String TiO2;

    @TableField("Cr2O3")
    private String Cr2O3;

    @TableField("MnO")
    private String MnO;

    @TableField("Fe2O3")
    private String Fe2O3;

    @TableField("CoO")
    private String CoO;

    @TableField("NiO")
    private String NiO;

    @TableField("CuO")
    private String CuO;

    @TableField("ZnO")
    private String ZnO;

    @TableField("Rb2O")
    private String Rb2O;

    @TableField("SrO")
    private String SrO;

    @TableField("SnO2")
    private String SnO2;

    @TableField("Sb2O3")
    private String Sb2O3;

    @TableField("PbO")
    private String PbO;

    @TableField("BaO")
    private String BaO;

    @TableField("Ti")
    private String Ti;

    @TableField("Cr")
    private String Cr;

    @TableField("Mn")
    private String Mn;

    @TableField("Co")
    private String Co;

    @TableField("Ni")
    private String Ni;

    @TableField("Cu")
    private String Cu;

    @TableField("Zn")
    private String Zn;

    @TableField("Rb")
    private String Rb;

    @TableField("Sr")
    private String Sr;

    @TableField("Sn")
    private String Sn;

    @TableField("Sb")
    private String Sb;

    @TableField("Pb")
    private String Pb;

    @TableField("Ba")
    private String Ba;


}
