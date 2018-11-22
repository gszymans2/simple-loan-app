package com.gszymans.simpleloanapp.analyser.analyses;

import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariable;
import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariableType;
import com.gszymans.simpleloanapp.domain.AnalysedCriteriaParamName;
import com.gszymans.simpleloanapp.domain.AnalysedCryteriaParam;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractAnalysesHelperTest {

    public static Map<AnalysedCriteriaParamName, AnalysedCryteriaParam> generateCriteriaMap() throws Exception{
        Map<AnalysedCriteriaParamName, AnalysedCryteriaParam> criteriaMap = new HashMap<>();

        AnalysedCryteriaParam minDayRange = new AnalysedCryteriaParam();
        minDayRange.setParamName(AnalysedCriteriaParamName.MIN_DAYS_RANGE);
        minDayRange.setParamValue("1");
        criteriaMap.put(AnalysedCriteriaParamName.MIN_DAYS_RANGE, minDayRange);

        AnalysedCryteriaParam maxDayRange = new AnalysedCryteriaParam();
        maxDayRange.setParamName(AnalysedCriteriaParamName.MAX_DAYS_RANGE);
        maxDayRange.setParamValue("20");
        criteriaMap.put(AnalysedCriteriaParamName.MAX_DAYS_RANGE, maxDayRange);

        AnalysedCryteriaParam minCredAmount = new AnalysedCryteriaParam();
        minCredAmount.setParamName(AnalysedCriteriaParamName.MIN_CREDIT_AMOUNT);
        minCredAmount.setParamValue("1");
        criteriaMap.put(AnalysedCriteriaParamName.MIN_CREDIT_AMOUNT, minCredAmount);

        AnalysedCryteriaParam maxCreditAmount = new AnalysedCryteriaParam();
        maxCreditAmount.setParamName(AnalysedCriteriaParamName.MAX_CREDIT_AMOUNT);
        maxCreditAmount.setParamValue("10000");
        criteriaMap.put(AnalysedCriteriaParamName.MAX_CREDIT_AMOUNT, maxCreditAmount);

        AnalysedCryteriaParam nightTariffStart = new AnalysedCryteriaParam();
        nightTariffStart.setParamName(AnalysedCriteriaParamName.NIGHT_TARIFF_START_HOUR);
        nightTariffStart.setParamValue("00:00:00");
        criteriaMap.put(AnalysedCriteriaParamName.NIGHT_TARIFF_START_HOUR, nightTariffStart);

        AnalysedCryteriaParam nightTariffEnd = new AnalysedCryteriaParam();
        nightTariffEnd.setParamName(AnalysedCriteriaParamName.NIGHT_TARRIF_END_HOUR);
        nightTariffEnd.setParamValue("06:00:00");
        criteriaMap.put(AnalysedCriteriaParamName.NIGHT_TARRIF_END_HOUR, nightTariffEnd);

        return criteriaMap;
    }

    public static Map<AnalysedInputVariableType, AnalysedInputVariable> generateInputParam(){
        Map<AnalysedInputVariableType, AnalysedInputVariable> inputParamMap = new HashMap<>();
        AnalysedInputVariable analysedInputVariable1 = new AnalysedInputVariable(AnalysedInputVariableType.CREDIT_AMOUNT,"100");
        AnalysedInputVariable analysedInputVariable2 = new AnalysedInputVariable(AnalysedInputVariableType.CREDIT_PERIOD,"15");
        AnalysedInputVariable analysedInputVariable3 = new AnalysedInputVariable(AnalysedInputVariableType.CURRENT_TIME, LocalTime.of(10,00).toString());
        inputParamMap.put(AnalysedInputVariableType.CREDIT_AMOUNT,analysedInputVariable1);
        inputParamMap.put(AnalysedInputVariableType.CREDIT_PERIOD,analysedInputVariable2);
        inputParamMap.put(AnalysedInputVariableType.CURRENT_TIME, analysedInputVariable3);
        return inputParamMap;
    }

}
