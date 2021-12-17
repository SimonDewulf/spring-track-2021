package be.inetumrealdolmen.springtrack.data;

import be.inetumrealdolmen.springtrack.dao.Statistic;

import java.util.List;

public interface StatisticsData {
    void addGame(boolean playerWonGame);

    void addMove();

    List<Statistic> findAll();
}
