package com.gszymans.simpleloanapp.analyser;

import com.gszymans.simpleloanapp.analyser.analyses.Analyse;
import com.gszymans.simpleloanapp.analyser.util.*;

import com.gszymans.simpleloanapp.domain.AnalysedCriteriaParamName;
import com.gszymans.simpleloanapp.domain.AnalysedCryteriaParam;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class CriteriaAnalyser {

    @Autowired
    AnalysedCriteriaMapGenerator analysedCriteriaMapGenerator;

    @Autowired
    AnalysedInputParamMapGenerator analysedInputParamMapGenerator;

    @Autowired
    private List<Analyse> listOfAnalyses;

    public boolean performAnalyses(AnalyseMode analyseMode, List<AnalysedInputVariable> analysedInputParams) throws AnalysePerformingException {

        Map<AnalysedCriteriaParamName, AnalysedCryteriaParam> constantParamMap = analysedCriteriaMapGenerator.getParamMap();
        Map<AnalysedInputVariableType, AnalysedInputVariable> inputParamMap = analysedInputParamMapGenerator.generateParamMap(analysedInputParams);

        switch(analyseMode){
            case FULL_ANALYSE:
                inputParamMap = completeInputParameters(inputParamMap);
                return performFullAnalyse(constantParamMap,inputParamMap);
            default:
               throw new AnalyseNotSupportedException("Analyse mode not supported");
        }
    }

    private boolean performFullAnalyse(Map<AnalysedCriteriaParamName, AnalysedCryteriaParam> constantParamMap, Map<AnalysedInputVariableType, AnalysedInputVariable> inputParamMap) throws AnalysePerformingException {

        for (Analyse analyse : listOfAnalyses){
            if ( analyse.performAnalyse(constantParamMap, inputParamMap)){
                continue;
            }else{
                return false;
            }
        }
        return true;

    }

    private Map<AnalysedInputVariableType, AnalysedInputVariable> completeInputParameters(Map<AnalysedInputVariableType, AnalysedInputVariable> inputParamMap){
        inputParamMap.put(AnalysedInputVariableType.CURRENT_TIME, new AnalysedInputVariable(AnalysedInputVariableType.CURRENT_TIME, LocalTime.now().toString()));
        return inputParamMap;
    }

}