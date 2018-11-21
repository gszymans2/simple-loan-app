package com.gszymans.simpleloanapp.repositories;

import com.gszymans.simpleloanapp.domain.LoanParameter;
import com.gszymans.simpleloanapp.domain.LoanParameterType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanParameterRepository extends JpaRepository<LoanParameter, Long> {

    LoanParameter findByParameterType(LoanParameterType parameterType);

}
