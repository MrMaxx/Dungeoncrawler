package de.overwatch.otd.game;


import de.overwatch.otd.domain.Fight;

public interface GameStateFactory {

    GameState createGameState(Fight fight);

}
