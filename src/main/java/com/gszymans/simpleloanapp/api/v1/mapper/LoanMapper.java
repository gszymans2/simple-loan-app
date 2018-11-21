package com.gszymans.simpleloanapp.api.v1.mapper;

import com.gszymans.simpleloanapp.api.v1.model.LoanApplicationDTO;
import com.gszymans.simpleloanapp.api.v1.model.LoanDTO;
import com.gszymans.simpleloanapp.domain.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoanMapper {

    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    LoanDTO loanToLoanDTO(Loan loan);

    @Mappings({@Mapping(target="loanPeriodInDays", source = "loanApplicationDTO.loanPeriodInDays"),
            @Mapping(target="loanTotalNetAmount", source = "loanApplicationDTO.loanTotalNetAmount")})
    Loan loanApplicationDTOToLoan(LoanApplicationDTO loanApplicationDTO);

}