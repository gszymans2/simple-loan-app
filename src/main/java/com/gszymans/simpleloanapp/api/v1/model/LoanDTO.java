package com.gszymans.simpleloanapp.api.v1.model;

import lombok.Data;

import java.time.LocalDate;
@Data
public class LoanDTO {

    private Long id;

    private LocalDate loanDueDate;

    private Double loanTotalGrossAmount;

    private Boolean loanExtended;

    private Integer loanPeriodInDays;

    private Double loanTotalNetAmount;
}
