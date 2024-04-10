package ru.dchinyaev.aopProjectTraining.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dchinyaev.aopProjectTraining.entity.StatisticsMethodTime;
import ru.dchinyaev.aopProjectTraining.repository.StatisticsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;
    public Double getAvgStatistics(){
        return statisticsRepository.averageMethodTime();
    };

    public void save(StatisticsMethodTime statistics) {
        statisticsRepository.save(statistics);
    }

    public List<StatisticsMethodTime> getAllStatistics(){
        return statisticsRepository.findAll();
    }
}
