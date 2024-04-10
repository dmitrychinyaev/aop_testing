package ru.dchinyaev.aopProjectTraining.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import ru.dchinyaev.aopProjectTraining.entity.StatisticsMethodTime;
import ru.dchinyaev.aopProjectTraining.responce.AvgStatResponse;
import ru.dchinyaev.aopProjectTraining.service.StatisticsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;
    @GetMapping("/all")
    public ResponseEntity<List<StatisticsMethodTime>> allStatistics(){
        return ResponseEntity.ok(statisticsService.getAllStatistics());
    }

    @GetMapping("/avgTrackTime")
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<AvgStatResponse> averageStatistics(){
        AvgStatResponse avgStatResponse = AvgStatResponse.builder()
                .annotation("@TrackTime")
                .averageTime(statisticsService.getAvgStatistics() + " мс")
                .build();
        return ResponseEntity.ok(avgStatResponse);
    }

}
