package com.gszymans.simpleloanapp.analyser.util;

import com.gszymans.simpleloanapp.domain.AnalysedCriteriaParamName;
import com.gszymans.simpleloanapp.domain.AnalysedCryteriaParam;
import com.gszymans.simpleloanapp.repositories.AnalyserCriteriaRepository;

import java.util.HashMap;
import java.util.Map;

public class AnalysedCriteriaMapGenerator {




    private AnalyserCriteriaRepository analyserCriteriaRepository;

    public AnalysedCriteriaMapGenerator(AnalyserCriteriaRepository analyserCriteriaRepository) {
        this.analyserCriteriaRepository = analyserCriteriaRepository;
    }


    public Map<AnalysedCriteriaParamName, AnalysedCryteriaParam> getParamMap(){
        Map<AnalysedCriteriaParamName, AnalysedCryteriaParam> paramMap = new HashMap<>();
        Iterable<AnalysedCryteriaParam> analyseParams = analyserCriteriaRepository.findAll();
        analyseParams.forEach((element)->{paramMap.put(element.getParamName(),element);});
        return paramMap;
    }
}
