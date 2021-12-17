package be.inetumrealdolmen.springtrack.service.impl;

import be.inetumrealdolmen.springtrack.dao.Game;
import be.inetumrealdolmen.springtrack.data.GameData;
import be.inetumrealdolmen.springtrack.data.StatisticsData;
import be.inetumrealdolmen.springtrack.service.GameService;

import java.util.UUID;

import static be.inetumrealdolmen.springtrack.dao.GameState.IN_PROGRESS;

public abstract class GameServiceImpl implements GameService {

    private final GameData gameDataAccess;
    private final StatisticsData statisticsData;

    public GameServiceImpl(GameData gameDataAccess, StatisticsData statisticsData) {
        this.gameDataAccess = gameDataAccess;
        this.statisticsData = statisticsData;
    }

    @Override
    public Game move(String gameId) {
        var game = gameDataAccess.findGameById(gameId);
        game.move();
        game = gameDataAccess.saveGame(game);
        modifyStatistics(game);
        return game;
    }

    private void modifyStatistics(Game game) {
        statisticsData.addMove();
        if (game.isFinished()) {
            statisticsData.addGame(game.isWon());
        }
    }

    @Override
    public Game createGame() {
        var game = Game.builder()
                .id(UUID.randomUUID().toString())
                .state(IN_PROGRESS)
                .moves(0)
                .build();

        return gameDataAccess.saveGame(game);
    }
}
