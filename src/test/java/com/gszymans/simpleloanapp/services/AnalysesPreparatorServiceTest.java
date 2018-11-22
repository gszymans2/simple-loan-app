package com.gszymans.simpleloanapp.services;

import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariable;
import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariableType;
import com.gszymans.simpleloanapp.api.v1.model.LoanApplicationDTO;
import com.gszymans.simpleloanapp.services.serviceErrors.BadInputParamsException;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static com.gszymans.simpleloanapp.AbstractHelperTest.CREDIT_NET_AMOUNT;
import static com.gszymans.simpleloanapp.AbstractHelperTest.CREDIT_PERIOD_IN_DAYS;
import static com.gszymans.simpleloanapp.AbstractHelperTest.generateLoanApplicationDTO;
import static org.junit.Assert.assertEquals;

public class AnalysesPreparatorServiceTest {

    AnalysesPreparatorService analysesPreparatorService;

    @Before
    public void setUp() throws Exception {

        analysesPreparatorService = new AnalysesPreparatorServiceImpl();
    }

    @Test
    public void createAnalyseInputDataOK_Test() throws Exception{
        LoanApplicationDTO loanApplicationDTO = generateLoanApplicationDTO();
        List<AnalysedInputVariable> analysedInputVariables = analysesPreparatorService.createAnalyseInputData(loanApplicationDTO);
        for(AnalysedInputVariable analysedInputVariable : analysedInputVariables){
            String valuToCompare = getVariableValueToCompare(analysedInputVariable.getAnalysedInputVariableType());
            assertEquals(analysedInputVariable.getAnalyseInputParam(),valuToCompare);
        }
    }

    @Test(expected = BadInputParamsException.class)
    public void createAnalyseInputDataBadInputException_Test() throws Exception{
        LoanApplicationDTO loanApplicationDTO = generateLoanApplicationDTO();
        loanApplicationDTO.setLoanPeriodInDays(null);
        analysesPreparatorService.createAnalyseInputData(loanApplicationDTO);
    }

    private String getVariableValueToCompare(AnalysedInputVariableType analysedInputVariableType){

        switch (analysedInputVariableType){
            case CREDIT_AMOUNT :
                return CREDIT_NET_AMOUNT.toString();
            case CREDIT_PERIOD :
                return CREDIT_PERIOD_IN_DAYS.toString();
            default:
                return "";
        }
    }

}
