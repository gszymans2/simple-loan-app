package com.gszymans.simpleloanapp.analyser.config;

import com.gszymans.simpleloanapp.analyser.CriteriaAnalyser;
import com.gszymans.simpleloanapp.analyser.analyses.AmountRangeAnalyse;
import com.gszymans.simpleloanapp.analyser.analyses.Analyse;
import com.gszymans.simpleloanapp.analyser.analyses.CurrentTimeMaxAmountAnalyse;
import com.gszymans.simpleloanapp.analyser.analyses.DaysRangeAnalyse;
import com.gszymans.simpleloanapp.analyser.util.AnalysedCriteriaMapGenerator;
import com.gszymans.simpleloanapp.analyser.util.AnalysedInputParamMapGenerator;
import com.gszymans.simpleloanapp.repositories.AnalyserCriteriaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AnalyserConfiguration {

    @Bean
    public AmountRangeAnalyse amountRangeAnalyse(){
        return new AmountRangeAnalyse();
    }

    @Bean
    public CurrentTimeMaxAmountAnalyse currentTimeMaxAmountAnalyse(){
        return new CurrentTimeMaxAmountAnalyse();
    }

    @Bean
    public DaysRangeAnalyse daysRangeAnalyse(){
        return new DaysRangeAnalyse();
    }

    @Bean
    public CriteriaAnalyser getAnalyzer(){
        return new CriteriaAnalyser();
    }

    @Bean
    public List<Analyse> getFullAnalyseList(){
        return Arrays.asList(new AmountRangeAnalyse(), new CurrentTimeMaxAmountAnalyse(), new DaysRangeAnalyse());
    }

    @Bean
    public AnalysedCriteriaMapGenerator analyserParamMapGenerator(AnalyserCriteriaRepository analyserCriteriaRepository){
        return new AnalysedCriteriaMapGenerator(analyserCriteriaRepository);
    }

    @Bean
    public AnalysedInputParamMapGenerator ParamToAnalyseMapGenerator(){
        return new AnalysedInputParamMapGenerator();
    }

}