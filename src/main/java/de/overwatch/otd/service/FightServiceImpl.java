package de.overwatch.otd.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.overwatch.otd.domain.Fight;
import de.overwatch.otd.domain.attack.AttackForce;
import de.overwatch.otd.domain.defend.Dungeon;
import de.overwatch.otd.domain.defend.TowerBlueprint;
import de.overwatch.otd.game.GameEngineFactory;
import de.overwatch.otd.game.GameState;
import de.overwatch.otd.game.processor.*;
import de.overwatch.otd.repository.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FightServiceImpl implements FightService{

    private static final Logger LOGGER = Logger.getLogger(FightServiceImpl.class);

    private final static int WINNING_SCORE = 100;

    @Autowired
    private FightRepository fightRepository;

    @Autowired
    private AttackerBlueprintRepository attackerBlueprintRepository;
    @Autowired
    private TowerBlueprintRepository towerBlueprintRepository;

    @Autowired
    private DungeonRepository dungeonRepository;
    @Autowired
    private AttackForceRepository attackForceRepository;

    @Autowired
    private GameEngineFactory gameEngineFactory;

    private Map<Integer, TowerBlueprint> getTowerBlueprintIdToTowerBlueprintMap(){

        return null;
    }

    @Override
    public PublicFight createFightAgainst(Integer attackingUserId, Integer defendingUserId) {
        List<Dungeon> dungeons = dungeonRepository.findByUserId(defendingUserId);
        List<AttackForce> attackForces = attackForceRepository.findByUserId(attackingUserId);

        // users can have only one Dungeon for now
        Dungeon dungeon = dungeons.get(0);
        AttackForce attackForce = attackForces.get(0);

        Fight fight = new Fight();
        fight.setDungeon(dungeon);
        fight.setAttackForce(attackForce);
        fight.setFightState(Fight.FightState.ISSUED);
        fight.setCreated(new Date());

        fight.setAttackerId(attackingUserId);
        fight.setDefenderId(defendingUserId);

        fight.setDungeonBlueprintId(dungeon.getDungeonBlueprintId());
        fight.setAttackForcePatternId(attackForce.getAttackForcePatternId());

        fightRepository.save(fight);

        return new PublicFight(fight);
    }

    @Override
    public PublicFight createFight(Integer dungeonId, Integer attackForceId) {
        Dungeon dungeon = dungeonRepository.findOne(dungeonId);
        AttackForce attackForce = attackForceRepository.findOne(attackForceId);
        Fight fight = new Fight();
        fight.setDungeon(dungeon);
        fight.setAttackForce(attackForce);
        fight.setFightState(Fight.FightState.ISSUED);
        fight.setCreated(new Date());

        fight.setDungeonBlueprintId(dungeon.getDungeonBlueprintId());
        fight.setAttackForcePatternId(attackForce.getAttackForcePatternId());

        // Todo: uhhhh...lazy loading...prefetch please...later
        fight.setAttackerId(attackForce.getUser().getId());
        fight.setDefenderId(dungeon.getUser().getId());

        fightRepository.save(fight);

        return new PublicFight(fight);
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

                if(gameState.getAttackerScore() > 0){
                    fight.setOutcome(Fight.Outcome.ATTACKER_WON);
                    fight.getAttackForce().getUser().setScore(fight.getAttackForce().getUser().getScore()+WINNING_SCORE);
                }else{
                    fight.setOutcome(Fight.Outcome.DEFENDER_WON);
                    fight.getDungeon().getUser().setScore(fight.getDungeon().getUser().getScore()+WINNING_SCORE);
                }

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
