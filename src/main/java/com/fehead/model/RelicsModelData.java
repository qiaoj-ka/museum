package com.fehead.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RelicsModelData implements Serializable {

    private static final long serialVersionUID = 1L;

    private RelicsModel relicsModel;

    private ChemicalModel chemicalModel;

}
