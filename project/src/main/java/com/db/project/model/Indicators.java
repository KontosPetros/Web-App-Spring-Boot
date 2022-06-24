package com.db.project.model;

import javax.persistence.*;

@Entity
@Table(name = "indicators")
public class Indicators {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Indicator_Id")
    private int Indicator_Id;
    @Column(name = "INDICATOR_CODE")
    private String Indicator_Code;
    @Column(name = "INDICATOR_NAME")
    private String Indicator_Name;
    @Column(name = "SOURCE_NOTE")
    private String Source_Note;
    @Column(name = "SOURCE_ORGANIZATION")
    private String Source_Organization;

    public Indicators() {
    }

    public int getIndicator_Id() {
        return Indicator_Id;
    }

    public void setIndicator_Id(int indicator_Id) {
        Indicator_Id = indicator_Id;
    }

    public String getIndicator_Code() {
        return Indicator_Code;
    }

    public void setIndicator_Code(String indicator_Code) {
        Indicator_Code = indicator_Code;
    }

    public String getIndicator_Name() {
        return Indicator_Name;
    }

    public void setIndicator_Name(String indicator_Name) {
        Indicator_Name = indicator_Name;
    }

    public String getSource_Note() {
        return Source_Note;
    }

    public void setSource_Note(String source_Note) {
        Source_Note = source_Note;
    }

    public String getSource_Organization() {
        return Source_Organization;
    }

    public void setSource_Organization(String source_Organization) {
        Source_Organization = source_Organization;
    }
}
