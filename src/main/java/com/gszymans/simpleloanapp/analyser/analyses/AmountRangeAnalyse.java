package com.gszymans.simpleloanapp.analyser.analyses;

import com.gszymans.simpleloanapp.analyser.util.AnalysePerformingException;
import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariable;
import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariableType;
import com.gszymans.simpleloanapp.domain.AnalysedCriteriaParamName;
import com.gszymans.simpleloanapp.domain.AnalysedCryteriaParam;

import java.util.Map;

public class AmountRangeAnalyse extends Analyse{

    public static final AnalysedInputVariableType ANALYSED_INPUT_VARIABLE = AnalysedInputVariableType.CREDIT_AMOUNT;

    @Override
    public boolean performAnalyse(Map<AnalysedCriteriaParamName, AnalysedCryteriaParam> paramMap, Map<AnalysedInputVariableType, AnalysedInputVariable> inputParamMap) throws AnalysePerformingException {
        try{
            double minAmount = Double.parseDouble(paramMap.get(AnalysedCriteriaParamName.MIN_CREDIT_AMOUNT).getParamValue());
            double maxAmount = Double.parseDouble(paramMap.get(AnalysedCriteriaParamName.MAX_CREDIT_AMOUNT).getParamValue());
            double amountToCheck = Double.parseDouble(inputParamMap.get(ANALYSED_INPUT_VARIABLE).getAnalyseInputParam());
            return (amountToCheck >= minAmount && amountToCheck <= maxAmount) ? true: false;
        }catch (RuntimeException exc){
            throw new AnalysePerformingException(exc);
        }
    }

}
