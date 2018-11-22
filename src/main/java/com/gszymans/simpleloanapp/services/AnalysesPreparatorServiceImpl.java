package com.gszymans.simpleloanapp.services;

import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariable;
import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariableType;
import com.gszymans.simpleloanapp.api.v1.model.LoanApplicationDTO;
import com.gszymans.simpleloanapp.services.serviceErrors.BadInputParamsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalysesPreparatorServiceImpl implements AnalysesPreparatorService{

    @Override
    public List<AnalysedInputVariable> createAnalyseInputData(LoanApplicationDTO loanApplicationDTO) {
        List<AnalysedInputVariable> analysedInputVariables = new ArrayList<>();
        if( loanApplicationDTO.getLoanTotalNetAmount() == null || loanApplicationDTO.getLoanPeriodInDays()==null )
            throw new BadInputParamsException("Wrong Params");
        analysedInputVariables.add(new AnalysedInputVariable(AnalysedInputVariableType.CREDIT_AMOUNT, loanApplicationDTO.getLoanTotalNetAmount().toString()));
        analysedInputVariables.add(new AnalysedInputVariable(AnalysedInputVariableType.CREDIT_PERIOD, loanApplicationDTO.getLoanPeriodInDays().toString()));
        return analysedInputVariables;
    }
}
