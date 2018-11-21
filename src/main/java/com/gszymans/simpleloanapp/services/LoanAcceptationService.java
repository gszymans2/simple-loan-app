package com.gszymans.simpleloanapp.services;

import com.gszymans.simpleloanapp.domain.Loan;

public interface LoanAcceptationService {

    public Loan acceptLoan(Loan loan);
}
