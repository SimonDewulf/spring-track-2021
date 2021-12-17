package be.inetumrealdolmen.springtrack.service;

import be.inetumrealdolmen.springtrack.dao.StatisticProperty;
import be.inetumrealdolmen.springtrack.repository.GameRepository;
import be.inetumrealdolmen.springtrack.repository.StatisticsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
public class GameServiceTest {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameRepository gameRepository;

    @MockBean
    private StatisticsRepository statisticsRepository;

    @Test
    void gameShouldNotContinueIfStatisticsCannotBeUpdated() {
        doThrow(new RuntimeException()).when(statisticsRepository).incrementValue(StatisticProperty.NUMBER_OF_MOVES);

        var game = gameService.createGame();

        assertAll(
                () -> assertThrows(RuntimeException.class, () -> gameService.move(game.getId())),
                () -> assertTrue(gameRepository.findById(game.getId()).isPresent()),
                () -> assertEquals(0, gameRepository.findById(game.getId()).get().getMoves())
        );
    }
}
