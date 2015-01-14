package de.overwatch.otd.game;


import de.overwatch.otd.domain.Fight;

public interface GameEngineFactory {

    GameEngine createGameEngine(Fight fight);

}
