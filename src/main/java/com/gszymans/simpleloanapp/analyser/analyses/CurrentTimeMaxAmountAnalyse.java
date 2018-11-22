package com.gszymans.simpleloanapp.analyser.analyses;


import com.gszymans.simpleloanapp.analyser.util.AnalysePerformingException;
import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariable;
import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariableType;
import com.gszymans.simpleloanapp.domain.AnalysedCriteriaParamName;
import com.gszymans.simpleloanapp.domain.AnalysedCryteriaParam;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class CurrentTimeMaxAmountAnalyse extends Analyse {

    public static final AnalysedInputVariableType ANALYSED_INPUT_VARIABLE = AnalysedInputVariableType.CREDIT_AMOUNT;
    public static final AnalysedInputVariableType ANALYSED_PARAM_TIME_NAME = AnalysedInputVariableType.CURRENT_TIME;

    @Override
    public boolean performAnalyse(Map<AnalysedCriteriaParamName, AnalysedCryteriaParam> paramMap, Map<AnalysedInputVariableType, AnalysedInputVariable> inputParamMap) throws AnalysePerformingException {
        try{
            LocalTime nightTariffStartTime = LocalTime.parse(paramMap.get(AnalysedCriteriaParamName.NIGHT_TARIFF_START_HOUR).getParamValue(),
                    DateTimeFormatter.ISO_LOCAL_TIME);
            LocalTime nightTariffEndTime = LocalTime.parse(paramMap.get(AnalysedCriteriaParamName.NIGHT_TARRIF_END_HOUR).getParamValue(),
                    DateTimeFormatter.ISO_LOCAL_TIME);
            LocalTime timeToCheck = LocalTime.parse(inputParamMap.get(ANALYSED_PARAM_TIME_NAME).getAnalyseInputParam(),DateTimeFormatter.ISO_LOCAL_TIME);
            boolean isNightTariff = (timeToCheck.isAfter(nightTariffStartTime) && (timeToCheck.isBefore(nightTariffEndTime)))?true:false;
            if ( isNightTariff ){
                double maxAmount = Double.parseDouble(paramMap.get(AnalysedCriteriaParamName.MAX_CREDIT_AMOUNT).getParamValue());
                double amountToCheck = Double.parseDouble(inputParamMap.get(ANALYSED_INPUT_VARIABLE).getAnalyseInputParam());
                return maxAmount==amountToCheck?false:true;
            }else{
                return true;
            }
        }catch (RuntimeException exc){
            throw new AnalysePerformingException(exc);
        }
    }
}
