package com.gszymans.simpleloanapp.services;

import com.gszymans.simpleloanapp.domain.Loan;
import com.gszymans.simpleloanapp.domain.LoanParameter;
import com.gszymans.simpleloanapp.domain.LoanParameterType;
import com.gszymans.simpleloanapp.repositories.LoanParameterRepository;
import com.gszymans.simpleloanapp.repositories.LoanRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static com.gszymans.simpleloanapp.AbstractHelperTest.RATE;
import static com.gszymans.simpleloanapp.AbstractHelperTest.generateLoan;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LoanAcceptationServiceImplTest {

    LoanAcceptationService loanAcceptationService;

    @Mock
    LoanRepository loanRepository;

    @Mock
    LoanParameterRepository loanParameterRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        loanAcceptationService = new LoanAcceptationServiceImpl(
                loanParameterRepository,
                loanRepository
        );
    }

    @Test
    public void acceptLoanTest()throws Exception{
        LoanParameter loanParameter = new LoanParameter();
        loanParameter.setParameterType(LoanParameterType.RATE);
        loanParameter.setValue(RATE);
        when(loanParameterRepository.findByParameterType(LoanParameterType.RATE)).thenReturn(loanParameter);
        Loan testLoan = generateLoan();
        when(loanRepository.save(any(Loan.class))).thenReturn(testLoan);
        loanAcceptationService.acceptLoan(testLoan);
        assertEquals(Optional.of(testLoan.getLoanTotalGrossAmount()), Optional.of(testLoan.getLoanTotalNetAmount() * (Double.parseDouble(RATE) + 1)));
    }
}
