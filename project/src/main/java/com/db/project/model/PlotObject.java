package com.db.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlotObject {
    private List<Integer> Countries_Id;
    private List<Integer> Indicators_Id;
    private int YearBefore;
    private int YearAfter;
    private String Value;
    private boolean years5;
    private boolean years10;
}

