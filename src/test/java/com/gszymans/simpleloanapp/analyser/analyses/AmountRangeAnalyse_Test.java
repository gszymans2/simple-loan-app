package com.gszymans.simpleloanapp.analyser.analyses;

import com.gszymans.simpleloanapp.analyser.util.AnalysePerformingException;
import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariable;
import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariableType;
import com.gszymans.simpleloanapp.domain.AnalysedCriteriaParamName;
import com.gszymans.simpleloanapp.domain.AnalysedCryteriaParam;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;
import static org.junit.Assert.assertEquals;

public class AmountRangeAnalyse_Test {

    AmountRangeAnalyse amountRangeAnalyse;

    Map<AnalysedCriteriaParamName, AnalysedCryteriaParam> constantParamMap;
    Map<AnalysedInputVariableType, AnalysedInputVariable> inputParamMap;

    @Before
    public void setUp() throws Exception {

        amountRangeAnalyse = new AmountRangeAnalyse();

        constantParamMap = AbstractAnalysesHelperTest.generateCriteriaMap();

        inputParamMap = AbstractAnalysesHelperTest.generateInputParam();

    }

    @Test
    public void performAmountRangeOK_Test() throws Exception{
        boolean result = amountRangeAnalyse.performAnalyse(constantParamMap,inputParamMap);
        assertEquals(result, true);
    }

    @Test
    public void performAmountRangeFail_Test() throws Exception{
        inputParamMap.replace(AnalysedInputVariableType.CREDIT_AMOUNT,new AnalysedInputVariable(AnalysedInputVariableType.CREDIT_AMOUNT,"-1"));
        boolean result = amountRangeAnalyse.performAnalyse(constantParamMap,inputParamMap);
        assertEquals(result, false);
    }

    @Test(expected = AnalysePerformingException.class)
    public void performAmountRangeAnalysePerformingExc_Test() throws Exception{
        inputParamMap.replace(AnalysedInputVariableType.CREDIT_AMOUNT,new AnalysedInputVariable(AnalysedInputVariableType.CREDIT_AMOUNT,"test"));
        amountRangeAnalyse.performAnalyse(constantParamMap,inputParamMap);
    }
}
