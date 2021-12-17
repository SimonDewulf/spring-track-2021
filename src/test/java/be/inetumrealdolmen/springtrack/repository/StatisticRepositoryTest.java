package be.inetumrealdolmen.springtrack.repository;

import be.inetumrealdolmen.springtrack.dao.Statistic;
import be.inetumrealdolmen.springtrack.dao.StatisticProperty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static be.inetumrealdolmen.springtrack.dao.StatisticProperty.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback
@Transactional
class StatisticRepositoryTest {

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Test
    void checkDefaultStatistics() {
        var statistics = statisticsRepository.findAll();

        assertAll(
                () -> assertNotNull(statistics),
                () -> assertEquals(4, statistics.size()),
                () -> assertTrue(statisticPropertyPresent(statistics, GAMES_WON)),
                () -> assertTrue(statisticPropertyPresent(statistics, GAMES_PLAYED)),
                () -> assertTrue(statisticPropertyPresent(statistics, NUMBER_OF_MOVES)),
                () -> assertTrue(statisticPropertyPresent(statistics, NUMBER_OF_PLAYERS)),
                () -> assertEquals(BigDecimal.ZERO, getValueForStatisticProperty(statistics, GAMES_WON)),
                () -> assertEquals(BigDecimal.ZERO, getValueForStatisticProperty(statistics, GAMES_PLAYED)),
                () -> assertEquals(BigDecimal.ZERO, getValueForStatisticProperty(statistics, NUMBER_OF_MOVES)),
                () -> assertEquals(BigDecimal.ZERO, getValueForStatisticProperty(statistics, NUMBER_OF_PLAYERS))
        );
    }

    @Test
    void checkIncrementForGamesPlayed() {
        statisticsRepository.incrementValue(GAMES_PLAYED);
        var statistics = statisticsRepository.findAll();
        assertAll(
                () -> assertNotNull(statistics),
                () -> assertEquals(4, statistics.size()),
                () -> assertTrue(statisticPropertyPresent(statistics, GAMES_WON)),
                () -> assertTrue(statisticPropertyPresent(statistics, GAMES_PLAYED)),
                () -> assertTrue(statisticPropertyPresent(statistics, NUMBER_OF_MOVES)),
                () -> assertTrue(statisticPropertyPresent(statistics, NUMBER_OF_PLAYERS)),
                () -> assertEquals(BigDecimal.ZERO, getValueForStatisticProperty(statistics, GAMES_WON)),
                () -> assertEquals(BigDecimal.ONE, getValueForStatisticProperty(statistics, GAMES_PLAYED)),
                () -> assertEquals(BigDecimal.ZERO, getValueForStatisticProperty(statistics, NUMBER_OF_MOVES)),
                () -> assertEquals(BigDecimal.ZERO, getValueForStatisticProperty(statistics, NUMBER_OF_PLAYERS))
        );
    }

    @Test
    void checkIncrementForGamesWon() {
        for (int i = 0; i < 20; i++) {
            statisticsRepository.incrementValue(GAMES_PLAYED);
        }
        for (int i = 0; i < 15; i++) {
            statisticsRepository.incrementValue(GAMES_WON);
        }

        for (int i = 0; i < 400; i++) {
            statisticsRepository.incrementValue(NUMBER_OF_MOVES);
        }

        var statistics = statisticsRepository.findAll();
        assertAll(
                () -> assertNotNull(statistics),
                () -> assertEquals(4, statistics.size()),
                () -> assertTrue(statisticPropertyPresent(statistics, GAMES_WON)),
                () -> assertTrue(statisticPropertyPresent(statistics, GAMES_PLAYED)),
                () -> assertTrue(statisticPropertyPresent(statistics, NUMBER_OF_MOVES)),
                () -> assertTrue(statisticPropertyPresent(statistics, NUMBER_OF_PLAYERS)),
                () -> assertEquals(new BigDecimal(15), getValueForStatisticProperty(statistics, GAMES_WON)),
                () -> assertEquals(new BigDecimal(20), getValueForStatisticProperty(statistics, GAMES_PLAYED)),
                () -> assertEquals(new BigDecimal(400), getValueForStatisticProperty(statistics, NUMBER_OF_MOVES)),
                () -> assertEquals(BigDecimal.ZERO, getValueForStatisticProperty(statistics, NUMBER_OF_PLAYERS))
        );
    }

    private boolean statisticPropertyPresent(List<Statistic> statistics, StatisticProperty statisticProperty) {
        return statistics.stream()
                .anyMatch(statistic -> statisticProperty.equals(statistic.getProperty()));
    }

    private BigDecimal getValueForStatisticProperty(List<Statistic> statistics, StatisticProperty statisticProperty) {
        return statistics.stream()
                .filter(s -> statisticProperty.equals(s.getProperty()))
                .findAny()
                .map(Statistic::getValue)
                .orElseGet(null);
    }
}
