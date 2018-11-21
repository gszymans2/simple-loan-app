package com.gszymans.simpleloanapp.api.v1.model;

import lombok.Data;

@Data
public class LoanApplicationDTO {

    private Integer loanPeriodInDays;

    private Double loanTotalNetAmount;

}