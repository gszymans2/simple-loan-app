package com.gszymans.simpleloanapp.services;

import com.gszymans.simpleloanapp.api.v1.model.LoanApplicationDTO;
import com.gszymans.simpleloanapp.api.v1.model.LoanDTO;

public interface LoanService {

    LoanDTO applyForLoan(LoanApplicationDTO loanApplicationDTO);

    LoanDTO extendLoan(Long id);
}
