package ru.dchinyaev.aopProjectTraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.dchinyaev.aopProjectTraining.entity.StatisticsMethodTime;
@Repository
public interface StatisticsRepository extends JpaRepository<StatisticsMethodTime,Long> {
    @Query(value = "SELECT AVG(execution_time) FROM statistic_aop_test", nativeQuery = true)
    double averageMethodTime();
}
