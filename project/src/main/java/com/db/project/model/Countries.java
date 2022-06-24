package com.db.project.model;

import javax.persistence.*;

@Entity(name = "countries")
@Table(name = "countries")
public class Countries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Country_Id")
    private int Country_Id;
    @Column(name = "Country_Code")
    private String Country_Code;
    @Column(name = "Region")
    private String Region;
    @Column(name = "Income_Group")
    private String Income_Group;
    @Column(name = "Table_Name")
    private String Table_Name;

    public Countries() {
    }

    public int getCountry_Id() {
        return Country_Id;
    }

    public void setCountry_Id(int country_Id) {
        Country_Id = country_Id;
    }

    public String getCountry_Code() {
        return Country_Code;
    }

    public void setCountry_Code(String country_Code) {
        Country_Code = country_Code;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getIncome_Group() {
        return Income_Group;
    }

    public void setIncome_Group(String income_Group) {
        Income_Group = income_Group;
    }

    public String getTable_Name() {
        return Table_Name;
    }

    public void setTable_Name(String table_Name) {
        Table_Name = table_Name;
    }
}
