package com.gszymans.simpleloanapp.domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer loanPeriodInDays;

    private LocalDate loanDueDate;

    private Double loanTotalNetAmount;

    private Double loanTotalGrossAmount;

    private Boolean loanExtended;

}