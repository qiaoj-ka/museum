package com.fehead.entity;

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
public class TypeDesc implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer relicsTypeId;

    private String relicsTypeName;


}
