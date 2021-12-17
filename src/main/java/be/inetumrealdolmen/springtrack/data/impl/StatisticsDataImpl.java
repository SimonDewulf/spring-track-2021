package be.inetumrealdolmen.springtrack.data.impl;

import be.inetumrealdolmen.springtrack.dao.Statistic;
import be.inetumrealdolmen.springtrack.data.StatisticsData;
import be.inetumrealdolmen.springtrack.repository.StatisticsRepository;
import org.springframework.stereotype.Component;

import java.util.List;

import static be.inetumrealdolmen.springtrack.dao.StatisticProperty.*;

@Component
public class StatisticsDataImpl implements StatisticsData {

    private final StatisticsRepository statisticsRepository;

    public StatisticsDataImpl(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    public void addGame(boolean playerWonGame) {
        statisticsRepository.incrementValue(GAMES_PLAYED);
        if (playerWonGame) {
            statisticsRepository.incrementValue(GAMES_WON);
        }
    }

    @Override
    public void addMove() {
        statisticsRepository.incrementValue(NUMBER_OF_MOVES);
    }

    @Override
    public List<Statistic> findAll() {
        return statisticsRepository.findAll();
    }
}
