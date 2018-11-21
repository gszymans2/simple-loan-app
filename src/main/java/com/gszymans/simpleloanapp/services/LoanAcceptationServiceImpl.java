package com.gszymans.simpleloanapp.services;

import com.gszymans.simpleloanapp.domain.Loan;
import com.gszymans.simpleloanapp.domain.LoanParameter;
import com.gszymans.simpleloanapp.domain.LoanParameterType;
import com.gszymans.simpleloanapp.repositories.LoanParameterRepository;
import com.gszymans.simpleloanapp.repositories.LoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoanAcceptationServiceImpl implements LoanAcceptationService {

    private final LoanParameterRepository loanParameterRepository;

    private final LoanRepository loanRepository;

    public LoanAcceptationServiceImpl(LoanParameterRepository loanParameterRepository, LoanRepository loanRepository) {
        this.loanParameterRepository = loanParameterRepository;
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan acceptLoan(Loan loan) {
        loan = calculateGrossLoanValue(loan);
        loan = calculateDueDate(loan);
        loan.setLoanExtended(false);
        loanRepository.save(loan);
        return loan;
    }

    private Loan calculateGrossLoanValue(Loan loan){
        LoanParameter loanRateParameter = loanParameterRepository.findByParameterType(LoanParameterType.RATE);
        Double loanTotalGrossValue = loan.getLoanTotalNetAmount() + loan.getLoanTotalNetAmount()*Double.parseDouble(loanRateParameter.getValue());
        loan.setLoanTotalGrossAmount(loanTotalGrossValue);
        return loan;
    }

    private Loan calculateDueDate(Loan loan){
        loan.setLoanDueDate(LocalDate.now().plusDays(loan.getLoanPeriodInDays()));
        return loan;
    }
}