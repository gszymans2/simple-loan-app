package com.gszymans.simpleloanapp.controllers.v1;

import com.gszymans.simpleloanapp.api.v1.model.LoanApplicationDTO;
import com.gszymans.simpleloanapp.api.v1.model.LoanDTO;
import com.gszymans.simpleloanapp.controllers.RestResponseEntityExceptionHandler;
import com.gszymans.simpleloanapp.services.LoanService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoanControllerTest {

    @Mock
    LoanService loanService;

    @InjectMocks
    LoanController loanController;

    MockMvc mockMvc;

    public static String applyEndpointEnd = "/loanApplication";
    public static String extendEndpointEnd = "/loanExtension/";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(loanController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void applyForLoan_Test() throws Exception{
//        LoanDTO loanDTO = generateLoanDTO();
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/loanApplication/");
//        when(loanController.applyForLoan(any(LoanApplicationDTO.class))).thenReturn(loanDTO);
//
//        mockMvc.perform(requestBuilder).andExpect(status().isOk());

//        mockMvc.perform(post(LoanController.BASE_URL+applyEndpointEnd)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());

    }


    private LoanDTO generateLoanDTO(){
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setId(1L);
        loanDTO.setLoanDueDate(LocalDate.now());
        loanDTO.setLoanExtended(true);
        loanDTO.setLoanPeriodInDays(123);
        loanDTO.setLoanTotalGrossAmount(100.0);
        loanDTO.setLoanTotalNetAmount(80.0);
        return loanDTO;
    }
}
