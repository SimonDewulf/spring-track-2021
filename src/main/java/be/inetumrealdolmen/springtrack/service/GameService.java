package be.inetumrealdolmen.springtrack.service;


import be.inetumrealdolmen.springtrack.dao.Game;

public interface GameService {

    Game move(String gameId);

    Game createGame();
}
