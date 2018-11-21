package com.gszymans.simpleloanapp.services;

import com.gszymans.simpleloanapp.domain.Loan;
import com.gszymans.simpleloanapp.domain.LoanParameterType;
import com.gszymans.simpleloanapp.repositories.LoanParameterRepository;
import com.gszymans.simpleloanapp.repositories.LoanRepository;
import org.springframework.stereotype.Service;

@Service
public class LoanExtensionServiceImpl implements LoanExtensionService {

    private final LoanParameterRepository loanParameterRepository;

    private final LoanRepository loanRepository;

    public LoanExtensionServiceImpl(LoanParameterRepository loanParameterRepository, LoanRepository loanRepository) {
        this.loanParameterRepository = loanParameterRepository;
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan extendLoan(Loan loan) {
        int loanExtension = Integer.valueOf(loanParameterRepository.findByParameterType(LoanParameterType.EXTENSION_IN_DAYS).getValue()).intValue();
        loan.setLoanPeriodInDays(loan.getLoanPeriodInDays()+loanExtension);
        loan.setLoanDueDate(loan.getLoanDueDate().plusDays(loanExtension));
        loan.setLoanExtended(true);
        loanRepository.save(loan);
        return loan;
    }
}
