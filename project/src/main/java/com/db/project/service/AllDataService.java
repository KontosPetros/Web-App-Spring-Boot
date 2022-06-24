package com.db.project.service;

import com.db.project.model.Countries;
import com.db.project.model.Indicators;

import java.util.List;

public interface AllDataService {
    public List<Countries> getAllCountries();

    public List<Indicators> getAllIndicators();

    public List<Integer> getAllYears();

    public List<Double> getGraphData(List<Integer> Countries_Id,List<Integer> Indicators_Id,Integer YearBefore,Integer YearAfter);

    public String getCountryNameById(Integer countryId);

    public String getIndicatorNameById(Integer indicatorId);
}
