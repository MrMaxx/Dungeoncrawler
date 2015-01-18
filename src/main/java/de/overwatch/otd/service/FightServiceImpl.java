package de.overwatch.otd.service;


import com.google.common.collect.Collections2;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.overwatch.otd.domain.Fight;
import de.overwatch.otd.domain.attack.AttackerBlueprint;
import de.overwatch.otd.domain.defend.TowerBlueprint;
import de.overwatch.otd.game.GameEngine;
import de.overwatch.otd.game.GameEngineFactory;
import de.overwatch.otd.game.events.GameEvent;
import de.overwatch.otd.repository.AttackerBlueprintRepository;
import de.overwatch.otd.repository.FightRepository;
import de.overwatch.otd.repository.TowerBlueprintRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FightServiceImpl implements FightService{

    private static final Logger LOGGER = Logger.getLogger(FightServiceImpl.class);

    @Autowired
    private FightRepository fightRepository;

    @Autowired
    private AttackerBlueprintRepository attackerBlueprintRepository;
    @Autowired
    private TowerBlueprintRepository towerBlueprintRepository;

    @Autowired
    private GameEngineFactory gameEngineFactory;

    private Map<Integer, TowerBlueprint> getTowerBlueprintIdToTowerBlueprintMap(){

        return null;
    }

    @Override
    public void processOutstandingFights() {

        Gson gson = new GsonBuilder().create();

        List<Fight> outstandingFights = fightRepository.findByFightState(Fight.FightState.ISSUED);

        for(Fight fight : outstandingFights){

            try {
                GameEngine engine = gameEngineFactory.createGameEngine(fight);
                List<GameEvent> events = engine.processGame();

                String eventStream = gson.toJson(events);

                fight.setEvents(eventStream);
                fight.setFightState(Fight.FightState.COMPLETED);
            }catch(Exception e){
                LOGGER.error("Error", e);
                fight.setFightState(Fight.FightState.ERROR);
            }

        }

    }

    @Override
    public PublicFight getPublicFight(Integer id, Integer userId) {
        Fight fight = fightRepository.findByIdAndUserId(id, userId);
        if(fight==null){return null;}
        return new PublicFight(fight);
    }

    @Override
    public List<PublicFight> getPublicFights(Integer userId) {

        List<PublicFight> result = new LinkedList<PublicFight>();
        List<Fight> fights = fightRepository.findByUserId(userId);
        for(Fight fight : fights){
            result.add(new PublicFight(fight));
        }

        return result;
    }
}
