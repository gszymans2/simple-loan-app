package com.gszymans.simpleloanapp.analyser.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalysedInputParamMapGenerator {

    public Map<AnalysedInputVariableType, AnalysedInputVariable> generateParamMap(List<AnalysedInputVariable> analysedInputVariables){
        return analysedInputVariables.stream().collect(Collectors.toMap((element)->element.getAnalysedInputVariableType(), element->element));
    }
}
