package com.gszymans.simpleloanapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gszymans.simpleloanapp.api.v1.model.LoanApplicationDTO;
import com.gszymans.simpleloanapp.api.v1.model.LoanDTO;
import com.gszymans.simpleloanapp.domain.Loan;

import java.time.LocalDate;

public abstract class AbstractHelperTest {

    public static final Long GENERATED_LOAN_ID = 1L;

    public static final int EXTENSION_IN_DAYS = 20;

    public static final String RATE = "0.1";

    public static final Double CREDIT_NET_AMOUNT = 80.0;

    public static final Integer CREDIT_PERIOD_IN_DAYS = 123;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static LoanDTO generateLoanDTO(){
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setId(GENERATED_LOAN_ID);
        loanDTO.setLoanDueDate(LocalDate.now());
        loanDTO.setLoanExtended(true);
        loanDTO.setLoanPeriodInDays(CREDIT_PERIOD_IN_DAYS);
        loanDTO.setLoanTotalGrossAmount(100.0);
        loanDTO.setLoanTotalNetAmount(CREDIT_NET_AMOUNT);
        return loanDTO;
    }

    public static Loan generateLoan(){
        Loan loanDTO = new Loan();
        loanDTO.setId(GENERATED_LOAN_ID);
        loanDTO.setLoanDueDate(LocalDate.now());
        loanDTO.setLoanExtended(true);
        loanDTO.setLoanPeriodInDays(CREDIT_PERIOD_IN_DAYS);
        loanDTO.setLoanTotalGrossAmount(100.0);
        loanDTO.setLoanTotalNetAmount(CREDIT_NET_AMOUNT);
        return loanDTO;
    }

    public static LoanApplicationDTO generateLoanApplicationDTO(){
        LoanApplicationDTO ret = new LoanApplicationDTO();
        ret.setLoanTotalNetAmount(CREDIT_NET_AMOUNT);
        ret.setLoanPeriodInDays(CREDIT_PERIOD_IN_DAYS);
        return ret;
    }
}
