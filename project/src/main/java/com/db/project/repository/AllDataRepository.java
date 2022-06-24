package com.db.project.repository;
import com.db.project.model.AllData;
import com.db.project.model.AllDataPKID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllDataRepository extends JpaRepository<AllData, AllDataPKID> {

    //c.Table_Name,i.Indicator_Name,a.Year,
    @Query(value = "SELECT a.Value\n"+
            " FROM AllData as a\n" +
            "INNER JOIN countries as c ON a.Country_Id=c.Country_Id\n" +
            "INNER JOIN Indicators as i ON a.Indicator_Id=i.Indicator_Id\n" +
            "WHERE a.Country_Id in (:Countries_Id) AND a.Indicator_Id in (:Indicators_Id)\n" +
            "AND (a.Year BETWEEN :YearBefore AND :YearAfter)",nativeQuery = true)
    public List<Double> getGraphData(List<Integer> Countries_Id,List<Integer> Indicators_Id,Integer YearBefore,Integer YearAfter);

    @Query(value = "SELECT Year FROM AllData WHERE Country_Id=1 AND Indicator_Id=1",nativeQuery = true)
    public List<Integer> getAllYears();

    @Query(value = "SELECT DISTINCT c.Table_Name FROM AllData as a INNER JOIN countries as c ON a.Country_Id=c.Country_Id WHERE a.Country_Id = :countryId",nativeQuery = true)
    public String getCountryNameById(Integer countryId);

    @Query(value = "SELECT DISTINCT i.INDICATOR_NAME FROM AllData as a INNER JOIN indicators as i ON a.Indicator_Id=i.Indicator_Id WHERE a.Indicator_Id = :indicatorId",nativeQuery = true)
    public String getIndicatorNameById(Integer indicatorId);
}
