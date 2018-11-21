package com.gszymans.simpleloanapp.analyser.analyses;


import com.gszymans.simpleloanapp.analyser.util.AnalysePerformingException;
import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariable;
import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariableType;
import com.gszymans.simpleloanapp.domain.AnalysedCriteriaParamName;
import com.gszymans.simpleloanapp.domain.AnalysedCryteriaParam;

import java.util.Map;

public abstract class Analyse {

    public abstract boolean performAnalyse(Map<AnalysedCriteriaParamName, AnalysedCryteriaParam> paramMap, Map<AnalysedInputVariableType, AnalysedInputVariable> inputParamMap) throws AnalysePerformingException;
}
