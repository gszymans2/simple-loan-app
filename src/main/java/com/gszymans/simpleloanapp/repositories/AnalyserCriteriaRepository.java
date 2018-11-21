package com.gszymans.simpleloanapp.repositories;

import com.gszymans.simpleloanapp.domain.AnalysedCriteriaParamName;
import com.gszymans.simpleloanapp.domain.AnalysedCryteriaParam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalyserCriteriaRepository extends JpaRepository<AnalysedCryteriaParam, Long> {

    AnalysedCryteriaParam findByParamName(AnalysedCriteriaParamName paramName);

}
