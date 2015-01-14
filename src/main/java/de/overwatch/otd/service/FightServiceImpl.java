package de.overwatch.otd.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.overwatch.otd.domain.Fight;
import de.overwatch.otd.game.GameEngine;
import de.overwatch.otd.game.GameEngineFactory;
import de.overwatch.otd.game.events.GameEvent;
import de.overwatch.otd.repository.FightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FightServiceImpl implements FightService{

    @Autowired
    private FightRepository fightRepository;

    @Autowired
    private GameEngineFactory gameEngineFactory;

    @Override
    public void processOutstandingFights() {

        Gson gson = new GsonBuilder().create();

        List<Fight> outstandingFights = fightRepository.findByFightState(Fight.FightState.ISSUED);

        for(Fight fight : outstandingFights){

            try {
                GameEngine engine = gameEngineFactory.createGameEngine(fight);
                List<GameEvent> events = engine.processGame();

                String eventStream = gson.toJson(outstandingFights);

                fight.setEventStream(eventStream);
                fight.setFightState(Fight.FightState.COMPLETED);
            }catch(Exception e){
                fight.setFightState(Fight.FightState.ERROR);
            }

        }

    }
}
