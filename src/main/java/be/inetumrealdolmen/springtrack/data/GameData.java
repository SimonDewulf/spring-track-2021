package be.inetumrealdolmen.springtrack.data;

import be.inetumrealdolmen.springtrack.dao.Game;

public interface GameData {

    Game findGameById(String gameId);

    Game saveGame(Game game);
}
