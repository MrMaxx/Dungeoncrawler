package de.overwatch.otd.game;

import de.overwatch.otd.domain.Fight;
import de.overwatch.otd.repository.AttackerBlueprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The GameEngineFactory is responsible to load all the bits and bytes from the Database,
 * that are needed to compute the result of a {link Fight}
 */
@Service
public class GameEngineFactoryImpl implements GameEngineFactory {

    @Autowired
    private AttackerBlueprintRepository attackerBlueprintRepository;

    @Override
    public GameEngine createGameEngine(Fight fight) {



        return null;
    }

}
