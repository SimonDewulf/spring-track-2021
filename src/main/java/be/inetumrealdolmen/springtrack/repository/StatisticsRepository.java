package be.inetumrealdolmen.springtrack.repository;

import be.inetumrealdolmen.springtrack.dao.Statistic;
import be.inetumrealdolmen.springtrack.dao.StatisticProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface StatisticsRepository extends JpaRepository<Statistic, String> {


    @Query("UPDATE Statistic stat SET stat.value = stat.value + 1 WHERE stat.property = :property")
    @Modifying
    @Transactional
    void incrementValue(StatisticProperty property);
}
