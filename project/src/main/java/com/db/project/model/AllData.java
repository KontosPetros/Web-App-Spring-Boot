package com.db.project.model;

import javax.persistence.*;

@Entity
@IdClass(AllDataPKID.class)
@Table(name = "alldata")
public class AllData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Country_Id")
    private int Country_Id;
    @Id
    @Column(name = "Indicator_Id")
    private int Indicator_Id;
    @Id
    @Column(name = "Year")
    private int Year;
    @Column(name = "Value")
    private double Value;

    public AllData() {
    }

    public AllData(int country_Id, int indicator_Id, int year, double value) {
        Country_Id = country_Id;
        Indicator_Id = indicator_Id;
        Year = year;
        Value = value;
    }

    public int getCountry_Id() {
        return Country_Id;
    }

    public void setCountry_Id(int country_Id) {
        Country_Id = country_Id;
    }

    public int getIndicator_Id() {
        return Indicator_Id;
    }

    public void setIndicator_Id(int indicator_Id) {
        Indicator_Id = indicator_Id;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        Value = value;
    }
}
