package com.gszymans.simpleloanapp.services;

import com.gszymans.simpleloanapp.analyser.AnalyseMode;
import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariable;
import com.gszymans.simpleloanapp.api.v1.model.LoanApplicationDTO;
import com.gszymans.simpleloanapp.api.v1.model.LoanDTO;
import com.gszymans.simpleloanapp.domain.Loan;
import com.gszymans.simpleloanapp.domain.LoanParameter;
import com.gszymans.simpleloanapp.domain.LoanParameterType;
import com.gszymans.simpleloanapp.repositories.LoanParameterRepository;
import com.gszymans.simpleloanapp.repositories.LoanRepository;
import com.gszymans.simpleloanapp.services.serviceErrors.NoSuchResourceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.gszymans.simpleloanapp.AbstractHelperTest.EXTENSION_IN_DAYS;
import static com.gszymans.simpleloanapp.AbstractHelperTest.GENERATED_LOAN_ID;
import static com.gszymans.simpleloanapp.AbstractHelperTest.generateLoan;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;

public class LoanExtensionServiceImplTest {

    LoanExtensionService loanExtensionService;

    @Mock
    LoanRepository loanRepository;

    @Mock
    LoanParameterRepository loanParameterRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        loanExtensionService = new LoanExtensionServiceImpl(
                loanParameterRepository,
                loanRepository
        );

    }

    @Test
    public void extendLoanAccepted_Test() throws Exception{
        LoanParameter loanParameter = new LoanParameter();
        loanParameter.setParameterType(LoanParameterType.EXTENSION_IN_DAYS);
        loanParameter.setValue(new Integer(EXTENSION_IN_DAYS).toString());
        when(loanParameterRepository.findByParameterType(LoanParameterType.EXTENSION_IN_DAYS)).thenReturn(loanParameter);
        Loan testLoan = generateLoan();
        when(loanRepository.save(any(Loan.class))).thenReturn(testLoan);
        int period = testLoan.getLoanPeriodInDays();
        testLoan = loanExtensionService.extendLoan(testLoan);
        assertEquals(testLoan.getLoanPeriodInDays().intValue(), period + EXTENSION_IN_DAYS);
    }


    @Test(expected = NoSuchResourceException.class)
    public void extendLoanNoResource_Test() throws Exception{
        when(loanParameterRepository.findByParameterType(LoanParameterType.EXTENSION_IN_DAYS)).thenThrow(NoSuchResourceException.class);
        loanExtensionService.extendLoan(generateLoan());
    }

}