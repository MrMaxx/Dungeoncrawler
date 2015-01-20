package de.overwatch.otd.service;


import com.google.common.collect.Collections2;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.overwatch.otd.domain.Fight;
import de.overwatch.otd.domain.attack.AttackerBlueprint;
import de.overwatch.otd.domain.defend.TowerBlueprint;
import de.overwatch.otd.game.GameEngine;
import de.overwatch.otd.game.GameEngineFactory;
import de.overwatch.otd.game.GameState;
import de.overwatch.otd.game.events.GameEvent;
import de.overwatch.otd.game.processor.*;
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
                GameState gameState = gameEngineFactory.createGameEngine(fight);

                int milliSeconds = 0;
                /* a Fight has to be finished in 10 Minutes */
                while( milliSeconds < 600000 ){

                    // 1. lets see if some Turrets are spawning
                    DefenderSpawnProcessor.process(gameState, milliSeconds);
                    // 2. lets see if some Attackers are spawning
                    AttackerSpawnProcessor.process(gameState, milliSeconds);
                    // 3. calculate the movements of the Attackers
                    MoveToProcessor.process(gameState, milliSeconds);
                    // 4. Turrets need to inflict damage...that is what they were build for
                    DefenderTargetingProcessor.process(gameState, milliSeconds);
                    // 5. Sometimes Attackers run through the whole Dungeon ands succeed
                    AttackerSucceededProcessor.process(gameState, milliSeconds);

                    milliSeconds++;
                }

                String eventStream = gson.toJson(gameState.getEvents());

                fight.setOutcome(
                        gameState.getAttackerScore() > 0?
                                Fight.Outcome.ATTACKER_WON: Fight.Outcome.DEFENDER_WON
                );

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
