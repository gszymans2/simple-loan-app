package com.gszymans.simpleloanapp.analyser.analyses;

import com.gszymans.simpleloanapp.analyser.util.AnalysePerformingException;
import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariable;
import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariableType;
import com.gszymans.simpleloanapp.domain.AnalysedCriteriaParamName;
import com.gszymans.simpleloanapp.domain.AnalysedCryteriaParam;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalTime;
import java.util.Map;
import static org.junit.Assert.assertEquals;

public class CurrentTimeMaxAmountAnalyseTest {

    CurrentTimeMaxAmountAnalyse currentTimeMaxAmountAnalyse;

    Map<AnalysedCriteriaParamName, AnalysedCryteriaParam> constantParamMap;
    Map<AnalysedInputVariableType, AnalysedInputVariable> inputParamMap;

    @Before
    public void setUp() throws Exception {

        currentTimeMaxAmountAnalyse = new CurrentTimeMaxAmountAnalyse();

        constantParamMap = AbstractAnalysesHelperTest.generateCriteriaMap();

        inputParamMap = AbstractAnalysesHelperTest.generateInputParam();

    }

    @Test
    public void performNightTariffAnalyseNotNightTariff_Test() throws Exception{
        boolean result = currentTimeMaxAmountAnalyse.performAnalyse(constantParamMap,inputParamMap);
        assertEquals(result, true);
    }

    @Test
    public void performNightTariffAnalyseNightTariffOK_Test() throws Exception{
        inputParamMap.replace(AnalysedInputVariableType.CURRENT_TIME,new AnalysedInputVariable(AnalysedInputVariableType.CURRENT_TIME, LocalTime.of(3,0).toString()));
        boolean result = currentTimeMaxAmountAnalyse.performAnalyse(constantParamMap,inputParamMap);
        assertEquals(result, true);
    }

    @Test
    public void performNightTariffAnalyseNightTariffFail_Test() throws Exception{
        inputParamMap.replace(AnalysedInputVariableType.CURRENT_TIME,new AnalysedInputVariable(AnalysedInputVariableType.CURRENT_TIME, LocalTime.of(3,0).toString()));
        inputParamMap.replace(AnalysedInputVariableType.CREDIT_AMOUNT,new AnalysedInputVariable(AnalysedInputVariableType.CREDIT_AMOUNT,constantParamMap.get(AnalysedCriteriaParamName.MAX_CREDIT_AMOUNT).getParamValue()));
        boolean result = currentTimeMaxAmountAnalyse.performAnalyse(constantParamMap,inputParamMap);
        assertEquals(result, false);
    }

    @Test(expected = AnalysePerformingException.class)
    public void performNightTariffAnalysePerformingExc_Test() throws Exception{
        inputParamMap.replace(AnalysedInputVariableType.CURRENT_TIME,new AnalysedInputVariable(AnalysedInputVariableType.CURRENT_TIME,"test"));
        currentTimeMaxAmountAnalyse.performAnalyse(constantParamMap,inputParamMap);
    }



}
