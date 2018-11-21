package com.gszymans.simpleloanapp.services;

import com.gszymans.simpleloanapp.api.v1.model.LoanApplicationDTO;
import com.gszymans.simpleloanapp.api.v1.model.LoanDTO;

public interface LoanService {

    public LoanDTO applyForLoan(LoanApplicationDTO loanApplicationDTO);

    public LoanDTO extendLoan(Long id);
}
