package com.gszymans.simpleloanapp.services;

import com.gszymans.simpleloanapp.domain.Loan;

public interface LoanExtensionService {
    Loan extendLoan(Loan loan);
}
