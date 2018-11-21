package com.gszymans.simpleloanapp.analyser.analyses;

import com.gszymans.simpleloanapp.analyser.util.AnalysePerformingException;
import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariable;
import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariableType;
import com.gszymans.simpleloanapp.domain.AnalysedCriteriaParamName;
import com.gszymans.simpleloanapp.domain.AnalysedCryteriaParam;

import java.util.Map;

public class DaysRangeAnalyse extends Analyse {

    public static final AnalysedInputVariableType ANALYSED_INPUT_VARIABLE = AnalysedInputVariableType.CREDIT_PERIOD;

    @Override
    public boolean performAnalyse(Map<AnalysedCriteriaParamName, AnalysedCryteriaParam> paramMap, Map<AnalysedInputVariableType, AnalysedInputVariable> inputParamMap) throws AnalysePerformingException {

        try{
            int minDays = Integer.valueOf(paramMap.get(AnalysedCriteriaParamName.MIN_DAYS_RANGE).getParamValue()).intValue();
            int maxDays = Integer.valueOf(paramMap.get(AnalysedCriteriaParamName.MAX_DAYS_RANGE).getParamValue()).intValue();
            int inputDays = Integer.valueOf(inputParamMap.get(ANALYSED_INPUT_VARIABLE).getAnalyseInputParam()).intValue();
            return (inputDays >= minDays && inputDays <= maxDays) ? true: false;
        }catch (RuntimeException exc){
            throw new AnalysePerformingException(exc);
        }
    }
}
