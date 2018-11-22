package com.gszymans.simpleloanapp.controllers.v1;

import com.gszymans.simpleloanapp.analyser.util.AnalysePerformingException;
import com.gszymans.simpleloanapp.api.v1.model.LoanApplicationDTO;
import com.gszymans.simpleloanapp.api.v1.model.LoanDTO;
import com.gszymans.simpleloanapp.controllers.RestResponseEntityExceptionHandler;
import com.gszymans.simpleloanapp.services.LoanService;
import com.gszymans.simpleloanapp.services.serviceErrors.BadInputParamsException;
import com.gszymans.simpleloanapp.services.serviceErrors.LoanRejectedException;
import com.gszymans.simpleloanapp.services.serviceErrors.NoSuchResourceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static com.gszymans.simpleloanapp.AbstractHelperTest.*;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoanControllerTest {

    @Mock
    LoanService loanService;

    @InjectMocks
    LoanController loanController;

    MockMvc mockMvc;

    LoanApplicationDTO loanApplicationDTO;

    public static String LOAN_APPLICATION_ENDPOINT = new StringBuilder(LoanController.BASE_URL).append("/loanApplication").toString();
    public static String LOAN_EXTENSION_ENDPOINT =
            new StringBuilder(LoanController.BASE_URL)
                    .append("/loanExtension/")
                    .append(GENERATED_LOAN_ID)
                    .toString();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        loanApplicationDTO = generateLoanApplicationDTO();
        mockMvc = MockMvcBuilders.standaloneSetup(loanController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void applyForLoanAccepted_Test() throws Exception{
        LoanDTO loanDTO = generateLoanDTO();
        given(loanService.applyForLoan(loanApplicationDTO)).willReturn(loanDTO);
        mockMvc.perform(post(LOAN_APPLICATION_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loanApplicationDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(loanDTO.getId().intValue())));
    }

    @Test
    public void applyForLoanRejection_Test() throws Exception{
        when(loanService.applyForLoan(loanApplicationDTO)).thenThrow(LoanRejectedException.class);
        mockMvc.perform(post(LOAN_APPLICATION_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loanApplicationDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void applyForLoanAnalysesException_Test() throws Exception{
        when(loanService.applyForLoan(loanApplicationDTO)).thenThrow(AnalysePerformingException.class);
        mockMvc.perform(post(LOAN_APPLICATION_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loanApplicationDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void applyForLoanBadInput_Test() throws Exception{
        when(loanService.applyForLoan(loanApplicationDTO)).thenThrow(BadInputParamsException.class);
        mockMvc.perform(post(LOAN_APPLICATION_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loanApplicationDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void applyForLoanNoResource_Test() throws Exception{
        when(loanService.applyForLoan(loanApplicationDTO)).thenThrow(NoSuchResourceException.class);
        mockMvc.perform(post(LOAN_APPLICATION_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loanApplicationDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void extendLoanAccepted_Test() throws Exception{
        LoanDTO loanDTO = generateLoanDTO();
        given(loanService.extendLoan(any(Long.class))).willReturn(loanDTO);
        mockMvc.perform(post(LOAN_EXTENSION_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void extendLoanNoResource_Test() throws Exception{
        when(loanService.extendLoan(any(Long.class))).thenThrow(NoSuchResourceException.class);
        mockMvc.perform(post(LOAN_EXTENSION_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
