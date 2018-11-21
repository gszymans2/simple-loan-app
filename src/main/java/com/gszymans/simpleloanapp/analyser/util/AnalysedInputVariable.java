package com.gszymans.simpleloanapp.analyser.util;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AnalysedInputVariable {

    @NonNull
    private AnalysedInputVariableType analysedInputVariableType;

    @NonNull
    private String analyseInputParam;

}