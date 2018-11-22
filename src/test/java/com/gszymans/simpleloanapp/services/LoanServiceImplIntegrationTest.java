package com.gszymans.simpleloanapp.services;


import com.gszymans.simpleloanapp.analyser.CriteriaAnalyser;
import com.gszymans.simpleloanapp.api.v1.mapper.LoanMapper;
import com.gszymans.simpleloanapp.api.v1.model.LoanApplicationDTO;
import com.gszymans.simpleloanapp.api.v1.model.LoanDTO;
import com.gszymans.simpleloanapp.domain.Loan;
import com.gszymans.simpleloanapp.domain.LoanParameter;
import com.gszymans.simpleloanapp.domain.LoanParameterType;
import com.gszymans.simpleloanapp.repositories.AnalyserCriteriaRepository;
import com.gszymans.simpleloanapp.repositories.LoanParameterRepository;
import com.gszymans.simpleloanapp.repositories.LoanRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ComponentScan("com.gszymans.simpleloanapp")
@RunWith(SpringRunner.class)
@DataJpaTest
public class LoanServiceImplIntegrationTest {

    @Autowired
    LoanRepository loanRepository;
    @Autowired
    LoanParameterRepository loanParameterRepository;
    @Autowired
    AnalyserCriteriaRepository analyserCriteriaRepository;
    @Autowired
    LoanMapper loanMapper;
    @Autowired
    AnalysesPreparatorService analysesPreparatorService;
    @Autowired
    LoanExtensionService loanExtensionService;
    @Autowired
    CriteriaAnalyser criteriaAnalyser;
    @Autowired
    LoanAcceptationService loanAcceptationService;

    LoanService loanService;

    public static final Long testedEntityId = 1L;

    @Before
    public void setUp() throws Exception {

        loanService = new LoanServiceImpl(
                loanRepository,
                loanMapper,
                analysesPreparatorService,
                loanExtensionService,
                criteriaAnalyser,
                loanAcceptationService);
    }

    @Test
    public void applyForLoanCreditAccepted_Test() throws Exception{
        LoanApplicationDTO loanApplicationDTO = generateLoanApplicationDTO();
        LoanDTO loanDTO = loanService.applyForLoan(loanApplicationDTO);
        Loan loan = loanRepository.findById(loanDTO.getId()).orElse(new Loan());
        assertEquals(loanDTO.getLoanDueDate(),loan.getLoanDueDate());
        assertEquals(loanDTO.getLoanExtended(),loan.getLoanExtended());
        assertEquals(loanDTO.getLoanPeriodInDays(),loan.getLoanPeriodInDays());
        assertEquals(loanDTO.getLoanTotalGrossAmount(),loan.getLoanTotalGrossAmount());
        assertEquals(loanDTO.getLoanTotalNetAmount(),loan.getLoanTotalNetAmount());
    }

    @Test
    public void extendLoan_Test() throws Exception{

        //Possible empty repository
        LoanApplicationDTO loanApplicationDTO = generateLoanApplicationDTO();
        loanService.applyForLoan(loanApplicationDTO);

        LocalDate dateBeforeExtension = loanRepository.findById(testedEntityId).orElse(new Loan()).getLoanDueDate();
        assertNotNull(dateBeforeExtension);
        LoanParameter extension = loanParameterRepository.findByParameterType(LoanParameterType.EXTENSION_IN_DAYS);
        assertNotNull(extension);
        int extensionValue = Integer.valueOf(extension.getValue()).intValue();

        LoanDTO loanDTO = loanService.extendLoan(testedEntityId);

        assertEquals(loanDTO.getLoanDueDate(),dateBeforeExtension.plusDays(extensionValue));
    }

    private LoanApplicationDTO generateLoanApplicationDTO(){
        LoanApplicationDTO loanApplicationDTO = new LoanApplicationDTO();
        loanApplicationDTO.setLoanPeriodInDays(12);
        loanApplicationDTO.setLoanTotalNetAmount(45.0);
        return loanApplicationDTO;
    }

}
