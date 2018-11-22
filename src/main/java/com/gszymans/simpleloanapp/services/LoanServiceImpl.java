package com.gszymans.simpleloanapp.services;

import com.gszymans.simpleloanapp.analyser.AnalyseMode;
import com.gszymans.simpleloanapp.analyser.CriteriaAnalyser;
import com.gszymans.simpleloanapp.analyser.util.AnalysePerformingException;
import com.gszymans.simpleloanapp.analyser.util.AnalysedInputVariable;
import com.gszymans.simpleloanapp.api.v1.mapper.LoanMapper;
import com.gszymans.simpleloanapp.api.v1.model.LoanApplicationDTO;
import com.gszymans.simpleloanapp.api.v1.model.LoanDTO;
import com.gszymans.simpleloanapp.domain.Loan;
import com.gszymans.simpleloanapp.repositories.LoanRepository;
import com.gszymans.simpleloanapp.services.serviceErrors.LoanRejectedException;
import com.gszymans.simpleloanapp.services.serviceErrors.NoSuchResourceException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    private final LoanMapper loanMapper;

    private final AnalysesPreparatorService analysesPreparatorService;

    private final LoanExtensionService loanExtensionService;

    private final CriteriaAnalyser criteriaAnalyser;

    private final LoanAcceptationService loanAcceptationService;

    //private final AnalysesPreparatorServiceImpl analysesPreparatorServiceImpl;

    public LoanServiceImpl(LoanRepository loanRepository, LoanMapper loanMapper, AnalysesPreparatorService analysesPreparatorService, LoanExtensionService loanExtensionService, CriteriaAnalyser criteriaAnalyser, LoanAcceptationService loanAcceptationService) {
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
        this.analysesPreparatorService = analysesPreparatorService;
        this.loanExtensionService = loanExtensionService;
        this.criteriaAnalyser = criteriaAnalyser;
        this.loanAcceptationService = loanAcceptationService;
    }


    @Override
    public LoanDTO applyForLoan(LoanApplicationDTO loanApplicationDTO){

        List<AnalysedInputVariable> listOfParamToAnalyse = analysesPreparatorService.createAnalyseInputData(loanApplicationDTO);

        try {
            boolean analyseResult = criteriaAnalyser.performAnalyses(AnalyseMode.FULL_ANALYSE, listOfParamToAnalyse );
            if( analyseResult ){
                Loan loan = loanAcceptationService.acceptLoan(loanMapper.loanApplicationDTOToLoan(loanApplicationDTO));
                return loanMapper.loanToLoanDTO(loan);
            }else{
                throw new LoanRejectedException();
            }
        } catch (AnalysePerformingException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public LoanDTO extendLoan(Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow(NoSuchResourceException::new);
        loan = loanExtensionService.extendLoan(loan);
        return loanMapper.loanToLoanDTO(loan);
    }

}
