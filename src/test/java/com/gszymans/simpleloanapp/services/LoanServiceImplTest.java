package com.gszymans.simpleloanapp.services;

import com.gszymans.simpleloanapp.analyser.AnalyseMode;
import com.gszymans.simpleloanapp.analyser.CriteriaAnalyser;
import com.gszymans.simpleloanapp.analyser.util.AnalysePerformingException;
import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariable;
import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariableType;
import com.gszymans.simpleloanapp.api.v1.mapper.LoanMapper;
import com.gszymans.simpleloanapp.api.v1.model.LoanApplicationDTO;
import com.gszymans.simpleloanapp.api.v1.model.LoanDTO;
import com.gszymans.simpleloanapp.domain.Loan;
import com.gszymans.simpleloanapp.repositories.LoanRepository;
import com.gszymans.simpleloanapp.services.serviceErrors.LoanRejectedException;
import com.gszymans.simpleloanapp.services.serviceErrors.NoSuchResourceException;
import lombok.NonNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.gszymans.simpleloanapp.AbstractHelperTest.*;
import static com.gszymans.simpleloanapp.AbstractHelperTest.generateLoan;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LoanServiceImplTest {

    LoanService loanService;

    @Mock
    LoanRepository loanRepository;

    LoanMapper loanMapper = LoanMapper.INSTANCE;
    @Mock
    AnalysesPreparatorService analysesPreparatorService;
    @Mock
    LoanExtensionService loanExtensionService;
    @Mock
    CriteriaAnalyser criteriaAnalyser;
    @Mock
    LoanAcceptationService loanAcceptationService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        loanService = new LoanServiceImpl(
                loanRepository,
                loanMapper,
                analysesPreparatorService,
                loanExtensionService,
                criteriaAnalyser,
                loanAcceptationService);
    }

    @Test
    public void applyForLoanAccepted_Test() throws Exception{
        List<AnalysedInputVariable> listOfParamToAnalyse = new ArrayList<>();
        when(analysesPreparatorService.createAnalyseInputData(any(LoanApplicationDTO.class))).thenReturn(listOfParamToAnalyse);
        when(criteriaAnalyser.performAnalyses(AnalyseMode.FULL_ANALYSE,listOfParamToAnalyse)).thenReturn(true);
        when(loanAcceptationService.acceptLoan(any(Loan.class))).thenReturn(generateLoan());
        LoanDTO loanDTO = loanService.applyForLoan(new LoanApplicationDTO());
        assertEquals(loanDTO.getId(), GENERATED_LOAN_ID);
    }

    @Test(expected = LoanRejectedException.class)
    public void applyForLoanRejected_Test() throws Exception{
        LoanDTO loanDTO = generateLoanDTO();
        List<AnalysedInputVariable> listOfParamToAnalyse = new ArrayList<>();
        when(analysesPreparatorService.createAnalyseInputData(any(LoanApplicationDTO.class))).thenReturn(listOfParamToAnalyse);
        when(criteriaAnalyser.performAnalyses(AnalyseMode.FULL_ANALYSE,listOfParamToAnalyse)).thenReturn(false);
        loanService.applyForLoan(new LoanApplicationDTO());
    }

    @Test(expected = AnalysePerformingException.class)
    public void applyForLoanAnalyseFail_Test() throws Exception{
        LoanDTO loanDTO = generateLoanDTO();
        List<AnalysedInputVariable> listOfParamToAnalyse = new ArrayList<>();
        when(analysesPreparatorService.createAnalyseInputData(any(LoanApplicationDTO.class))).thenReturn(listOfParamToAnalyse);
        when(criteriaAnalyser.performAnalyses(AnalyseMode.FULL_ANALYSE,listOfParamToAnalyse)).thenThrow(AnalysePerformingException.class);
        loanService.applyForLoan(new LoanApplicationDTO());
    }

    @Test
    public void extendLoanAccepted_Test() throws Exception{
        when(loanRepository.findById(any(Long.class))).thenReturn(Optional.of(generateLoan()));
        when(loanExtensionService.extendLoan(any(Loan.class))).thenReturn(generateLoan());
        LoanDTO loanDTO = loanService.extendLoan(GENERATED_LOAN_ID);
        assertEquals(loanDTO.getId(), GENERATED_LOAN_ID);
    }

    @Test(expected = NoSuchResourceException.class)
    public void extendLoanNoResource_Test() throws Exception{
        when(loanRepository.findById(any(Long.class))).thenThrow(NoSuchResourceException.class);
        LoanDTO loanDTO = loanService.extendLoan(GENERATED_LOAN_ID);
    }
}
