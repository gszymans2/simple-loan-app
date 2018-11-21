package com.gszymans.simpleloanapp.controllers.v1;

import com.gszymans.simpleloanapp.api.v1.model.LoanApplicationDTO;
import com.gszymans.simpleloanapp.api.v1.model.LoanDTO;
import com.gszymans.simpleloanapp.services.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(LoanController.BASE_URL)
public class LoanController {

    public static final String BASE_URL = "/api/v1";

    public final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/loanApplication")
    @ResponseStatus(HttpStatus.CREATED)
    public LoanDTO applyForLoan(@RequestBody LoanApplicationDTO loanApplicationDTO){
        return loanService.applyForLoan(loanApplicationDTO);
    }

    @PostMapping("/loanExtension/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LoanDTO extendLoan(@PathVariable Long id){
        return loanService.extendLoan(id);
    }

}
