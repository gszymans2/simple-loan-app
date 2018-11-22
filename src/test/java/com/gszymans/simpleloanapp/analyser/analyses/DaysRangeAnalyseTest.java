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

public class DaysRangeAnalyseTest {

    DaysRangeAnalyse daysRangeAnalyse;

    Map<AnalysedCriteriaParamName, AnalysedCryteriaParam> constantParamMap;
    Map<AnalysedInputVariableType, AnalysedInputVariable> inputParamMap;

    @Before
    public void setUp() throws Exception {

        daysRangeAnalyse = new DaysRangeAnalyse();

        constantParamMap = AbstractAnalysesHelperTest.generateCriteriaMap();

        inputParamMap = AbstractAnalysesHelperTest.generateInputParam();

    }

    @Test
    public void performDaysRangeOK_Test() throws Exception{
        boolean result = daysRangeAnalyse.performAnalyse(constantParamMap,inputParamMap);
        assertEquals(result, true);
    }

    @Test
    public void performDaysRangeRangeFail_Test() throws Exception{
        inputParamMap.replace(AnalysedInputVariableType.CREDIT_PERIOD,new AnalysedInputVariable(AnalysedInputVariableType.CREDIT_PERIOD,"-1"));
        boolean result = daysRangeAnalyse.performAnalyse(constantParamMap,inputParamMap);
        assertEquals(result, false);
    }

    @Test(expected = AnalysePerformingException.class)
    public void performDaysRangeAnalysePerformingExc_Test() throws Exception{
        inputParamMap.replace(AnalysedInputVariableType.CREDIT_PERIOD,new AnalysedInputVariable(AnalysedInputVariableType.CREDIT_PERIOD,"test"));
        daysRangeAnalyse.performAnalyse(constantParamMap,inputParamMap);
    }

}
