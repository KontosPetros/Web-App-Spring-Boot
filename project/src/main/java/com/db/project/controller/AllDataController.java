package com.db.project.controller;

import com.db.project.model.Countries;
import com.db.project.model.Indicators;
import com.db.project.model.PlotObject;
import com.db.project.service.AllDataService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;


@Controller
public class AllDataController {
    @Autowired
    private AllDataService allDataService;

    @RequestMapping("/index")
    @ResponseBody
    public String indexPage(){
        return  "index";
    }

    @RequestMapping("/newGraph/graph")
    public ModelAndView graphPage(ModelMap model){
        List<Countries> countries = allDataService.getAllCountries();
        List<Indicators> indicators = allDataService.getAllIndicators();
        List<Integer> year = allDataService.getAllYears();
        List<Integer> countries_Id = new ArrayList<Integer>();
        List<Integer> Indicators_Id = new ArrayList<Integer>();
        PlotObject tData = new PlotObject(countries_Id,Indicators_Id,0,0,"",false,false);
        model.addAttribute("obj",tData);
        model.addAttribute("countries",countries);
        model.addAttribute("indicators",indicators);
        model.addAttribute("Year",year);
        return new ModelAndView("graph", model);
    }

    @PostMapping("/graphs/{pageName}")
    public ModelAndView Chart(@ModelAttribute("obj") PlotObject data,ModelMap model,@PathVariable String pageName){
        int countryLen = data.getCountries_Id().size();
        int indicatorLen = data.getIndicators_Id().size();

        JSONObject dataValues = new JSONObject();
        JSONObject countryNames = new JSONObject();
        JSONObject indicatorNames = new JSONObject();

        int dataCounter = 0;
        List<String> years = new ArrayList<>();
        for(int indicatorsCounter = 0;  indicatorsCounter<indicatorLen;   indicatorsCounter++) {
            for (int countriesCounter = 0; countriesCounter < countryLen; countriesCounter++) {
                List<Integer> country = Arrays.asList(data.getCountries_Id().get(countriesCounter));
                List<Integer> indicator = Arrays.asList(data.getIndicators_Id().get(indicatorsCounter));
                List<Double> graphData = allDataService.getGraphData(country, indicator, data.getYearBefore(), data.getYearAfter());
                if(data.isYears5()){
                    graphData = findAverage(graphData,data.getYearAfter()-data.getYearBefore(),5);
                    years = findYearsAverage(years,data.getYearBefore(),data.getYearAfter(),5);
                }
                else if(data.isYears10()){
                    graphData = findAverage(graphData,data.getYearAfter()-data.getYearBefore(),10);
                    years = findYearsAverage(years,data.getYearBefore(),data.getYearAfter(),10);
                }
                else{
                    years = findYearsAverage(years,data.getYearBefore(),data.getYearAfter(),1);
                }

                dataValues.put(dataCounter + "", graphData.toArray());
                countryNames.put(countriesCounter + "", allDataService.getCountryNameById(data.getCountries_Id().get(countriesCounter)));
                indicatorNames.put(indicatorsCounter+ "",allDataService.getIndicatorNameById(data.getIndicators_Id().get(indicatorsCounter)));

                dataCounter+=1;
            }
        }

        Iterator x = dataValues.keys();
        JSONArray dataValuesArray = new JSONArray();

        Iterator y = countryNames.keys();
        JSONArray countryNamesArray = new JSONArray();

        Iterator z = indicatorNames.keys();
        JSONArray indicatorNamesArray = new JSONArray();

        while (x.hasNext()){
            String key = (String) x.next();
            dataValuesArray.put(dataValues.get(key));
        }

        while (y.hasNext()){
            String key = (String) y.next();
            countryNamesArray.put(countryNames.get(key));
        }

        while (z.hasNext()){
            String key = (String) z.next();
            indicatorNamesArray.put(indicatorNames.get(key));
        }


        model.addAttribute("dataValues",dataValuesArray);
        model.addAttribute("countryNames",countryNamesArray);
        model.addAttribute("indicatorNames",indicatorNamesArray);

        model.addAttribute("countryLen",countryLen);
        model.addAttribute("indicatorLen",indicatorLen);
        model.addAttribute("yearBefore",data.getYearBefore());
        model.addAttribute("yearAfter",data.getYearAfter());
        model.addAttribute("years", years);

        return new ModelAndView(pageName, model);
    }

    public List<String> findYearsAverage(List<String> years,int yearBefore,int yearAfter,int avgNum){
        int count = 0;
        for (int i =  yearBefore; i <= yearAfter; i=i+avgNum) {
            if(avgNum>1) {
                count = i + avgNum;
                if (count <= yearAfter) {
                    years.add(i + " - " + count);
                } else {
                    int res = yearAfter - i;
                    years.add(i + " - " + (i + res));
                }
            }
            else{
                years.add(String.valueOf(i));
            }
        }
        return years;
    }
    public List<Double> findAverage(List<Double> graphData,int YearCount,int avgNum){
        int count = 0;
        int trueCount = 0;
        double avg = 0;
        List<Double> test = new ArrayList<Double>();
        for(int i=0;i<YearCount;i++){
            if (count==avgNum){
                if(avg>0){
                    test.add(avg/trueCount);
                }
                count = 0;
                avg = 0;
                trueCount = 0;
            }
            if(graphData.get(i)!=0){
                avg = avg + graphData.get(i);
                trueCount = trueCount +1;
            }

            count = count + 1;
        }
        if(avg>0){
            test.add(avg/count);
        }

        return test;
    }

}
