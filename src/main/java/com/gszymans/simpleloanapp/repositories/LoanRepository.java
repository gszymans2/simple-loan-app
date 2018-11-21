package com.gszymans.simpleloanapp.repositories;

import com.gszymans.simpleloanapp.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
