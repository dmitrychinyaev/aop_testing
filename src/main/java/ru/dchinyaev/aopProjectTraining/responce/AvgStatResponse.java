package ru.dchinyaev.aopProjectTraining.responce;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Builder
@Setter
@Getter
public class AvgStatResponse {
    private final String annotation;
    private final String averageTime;
}
