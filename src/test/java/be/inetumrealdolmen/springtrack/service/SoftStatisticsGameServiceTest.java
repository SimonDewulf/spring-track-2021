package be.inetumrealdolmen.springtrack.service;

import be.inetumrealdolmen.springtrack.dao.StatisticProperty;
import be.inetumrealdolmen.springtrack.repository.GameRepository;
import be.inetumrealdolmen.springtrack.repository.StatisticsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@ActiveProfiles("softStatistics")
public class SoftStatisticsGameServiceTest {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameRepository gameRepository;

    @MockBean
    private StatisticsRepository statisticsRepository;

    @Test
    void gameShouldContinueIfStatisticsCannotBeUpdated() {
        doThrow(new RuntimeException()).when(statisticsRepository).incrementValue(StatisticProperty.NUMBER_OF_MOVES);

        var game = gameService.createGame();

        assertAll(
                () -> assertThrows(RuntimeException.class, () -> gameService.move(game.getId())),
                () -> assertTrue(gameRepository.findById(game.getId()).isPresent()),
                () -> assertEquals(1, gameRepository.findById(game.getId()).get().getMoves())
        );
    }
}
