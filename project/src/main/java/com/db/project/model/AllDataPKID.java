package com.db.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AllDataPKID implements Serializable {
    private Integer Country_Id;
    private Integer Indicator_Id;
    private Integer Year;
}
