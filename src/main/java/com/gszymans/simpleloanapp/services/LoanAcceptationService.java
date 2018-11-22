package com.gszymans.simpleloanapp.services;

import com.gszymans.simpleloanapp.domain.Loan;

public interface LoanAcceptationService {

    Loan acceptLoan(Loan loan);
}
