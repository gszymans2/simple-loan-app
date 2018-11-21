package com.gszymans.simpleloanapp.services;

import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariable;
import com.gszymans.simpleloanapp.api.v1.model.LoanApplicationDTO;

import java.util.List;

public interface AnalysesPreparatorService {

    public List<AnalysedInputVariable> createAnalyseInputData(LoanApplicationDTO loanApplicationDTO);

}
