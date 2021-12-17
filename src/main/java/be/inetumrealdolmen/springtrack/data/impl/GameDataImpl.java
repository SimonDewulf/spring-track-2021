package be.inetumrealdolmen.springtrack.data.impl;

import be.inetumrealdolmen.springtrack.dao.Game;
import be.inetumrealdolmen.springtrack.data.GameData;
import be.inetumrealdolmen.springtrack.exception.MissingGameException;
import be.inetumrealdolmen.springtrack.repository.GameRepository;
import org.springframework.stereotype.Component;

@Component
public class GameDataImpl implements GameData {

    private final GameRepository gameRepository;

    public GameDataImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game findGameById(String gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(MissingGameException::new);
    }

    @Override
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }
}
