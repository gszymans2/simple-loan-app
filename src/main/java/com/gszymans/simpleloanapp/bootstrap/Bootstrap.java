package com.gszymans.simpleloanapp.bootstrap;

import com.gszymans.simpleloanapp.domain.*;
import com.gszymans.simpleloanapp.repositories.AnalyserCriteriaRepository;
import com.gszymans.simpleloanapp.repositories.LoanParameterRepository;
import com.gszymans.simpleloanapp.repositories.LoanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class Bootstrap implements CommandLineRunner {

    private final LoanRepository loanRepository;

    private final LoanParameterRepository loanParameterRepository;

    private final AnalyserCriteriaRepository analyserCriteriaRepository;

    public Bootstrap(LoanRepository loanRepository, LoanParameterRepository loanParameterRepository, AnalyserCriteriaRepository analyserCriteriaRepository) {
        this.loanRepository = loanRepository;
        this.loanParameterRepository = loanParameterRepository;
        this.analyserCriteriaRepository = analyserCriteriaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.loadLoans();
        this.loadParameters();
        this.loadAnalyseParams();
    }

    //Test to delete
    private void loadLoans(){
        Loan loan1 = new Loan();
        loan1.setLoanPeriodInDays(12);
        loan1.setLoanTotalNetAmount(123.0);
        loan1.setLoanTotalGrossAmount(200.0);
        loan1.setLoanDueDate(LocalDate.now().plusDays(20));
        loanRepository.save(loan1);

        Loan loan2 = new Loan();
        loan2.setLoanPeriodInDays(12);
        loan2.setLoanTotalNetAmount(321.0);
        loan2.setLoanTotalGrossAmount(432.0);
        loan2.setLoanDueDate(LocalDate.now().plusDays(20));
        loanRepository.save(loan2);

    }

    private void loadParameters(){
        LoanParameter loanParameter = new LoanParameter();
        loanParameter.setParameterType(LoanParameterType.RATE);
        loanParameter.setValue("0.1");
        loanParameterRepository.save(loanParameter);

        LoanParameter loanParameter2 = new LoanParameter();
        loanParameter2.setParameterType(LoanParameterType.EXTENSION_IN_DAYS);
        loanParameter2.setValue("20");
        loanParameterRepository.save(loanParameter2);

    }

    private void loadAnalyseParams(){

        AnalysedCryteriaParam minDayRange = new AnalysedCryteriaParam();
        minDayRange.setParamName(AnalysedCriteriaParamName.MIN_DAYS_RANGE);
        minDayRange.setParamValue("1");
        analyserCriteriaRepository.save(minDayRange);

        AnalysedCryteriaParam maxDayRange = new AnalysedCryteriaParam();
        maxDayRange.setParamName(AnalysedCriteriaParamName.MAX_DAYS_RANGE);
        maxDayRange.setParamValue("20");
        analyserCriteriaRepository.save(maxDayRange);

        AnalysedCryteriaParam minCredAmount = new AnalysedCryteriaParam();
        minCredAmount.setParamName(AnalysedCriteriaParamName.MIN_CREDIT_AMOUNT);
        minCredAmount.setParamValue("1");
        analyserCriteriaRepository.save(minCredAmount);

        AnalysedCryteriaParam maxCreditAmount = new AnalysedCryteriaParam();
        maxCreditAmount.setParamName(AnalysedCriteriaParamName.MAX_CREDIT_AMOUNT);
        maxCreditAmount.setParamValue("10000");
        analyserCriteriaRepository.save(maxCreditAmount);

        AnalysedCryteriaParam nightTariffStart = new AnalysedCryteriaParam();
        nightTariffStart.setParamName(AnalysedCriteriaParamName.NIGHT_TARIFF_START_HOUR);
        nightTariffStart.setParamValue("00:00:00");
        analyserCriteriaRepository.save(nightTariffStart);

        AnalysedCryteriaParam nightTariffEnd = new AnalysedCryteriaParam();
        nightTariffEnd.setParamName(AnalysedCriteriaParamName.NIGHT_TARRIF_END_HOUR);
        nightTariffEnd.setParamValue("06:00:00");
        analyserCriteriaRepository.save(nightTariffEnd);
    }

}
