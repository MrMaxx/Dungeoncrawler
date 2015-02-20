package de.overwatch.otd.game.processor;


import de.overwatch.otd.game.GameState;
import de.overwatch.otd.game.events.AttackerDied;
import de.overwatch.otd.game.events.TowerLostTarget;
import de.overwatch.otd.game.events.TowerTargetedAttacker;
import de.overwatch.otd.game.events.GameEvent;
import de.overwatch.otd.game.model.Attacker;
import de.overwatch.otd.game.model.Turret;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * Todo: i would feel better if i would add some testing to this one :)
 *
 */
public class DefenderTargetingProcessor {

    private static final Logger LOGGER = Logger.getLogger(DefenderTargetingProcessor.class);

    public static void process(GameState gameState, int tickInMilliseconds){

        for(Turret turret : gameState.getTurrets()){
            // is target out of range? then clear target of turret
            if( turret.getCurrentTarget() != null ){
                LOGGER.debug("OUT_OF_RANGE => checking: "+turret.getCoordinate()+" to "+ turret.getCurrentTarget().getCoordinate()+" > "+turret.getRange());
                if(turret.getCoordinate().getDistanceTo(turret.getCurrentTarget().getCoordinate()) > turret.getRange()){
                    Attacker target = turret.getCurrentTarget();

                    target.getBeingTargetedBy().remove(turret);
                    turret.setCurrentTarget(null);

                    TowerLostTarget event = new TowerLostTarget(turret.getId());
                    event.setAttackerId(target.getId());
                    event.setTime(tickInMilliseconds);
                    gameState.getEvents().add(event);

                    LOGGER.debug("OUT_OF_RANGE => Turret with id="+turret.getId()+" got out of range for attacker with id="+target.getId());
                }

            }


            // if there is no target for turret, search for one
            if( turret.getCurrentTarget() == null ){

                for(Attacker attacker : gameState.getAttackers()){
                    // only add if attacker is in range and there is still no currentTarget...else we could double add
                    LOGGER.debug("NEWLY_IN_RANGE => checking: "+turret.getCoordinate()+" to "+ attacker.getCoordinate()+" <= "+turret.getRange()+" > "+(turret.getCoordinate().getDistanceTo(attacker.getCoordinate())));
                    if(turret.getCoordinate().getDistanceTo(attacker.getCoordinate()) <= turret.getRange()
                            && turret.getCurrentTarget() == null){
                        LOGGER.debug("NEWLY_IN_RANGE => ITS IN RANGE");
                        turret.setCurrentTarget(attacker);
                        attacker.getBeingTargetedBy().add(turret);

                        TowerTargetedAttacker event = new TowerTargetedAttacker(turret.getId());
                        event.setAttackerId(attacker.getId());
                        event.setTime(tickInMilliseconds);
                        gameState.getEvents().add(event);

                        LOGGER.debug("NEW_TARGET => Turret with id="+turret.getId()+" now targets attacker with id="+attacker.getId());
                    }
                }

            }

            // if there finally is a target, inflict damage on it...HELL YEAH!!!
            if( turret.getCurrentTarget() != null ){
                LOGGER.debug("CAN_SHOOT_AND_RELOADED => checking Turret with id="+turret.getId()+" last shot at "+turret.getLastShot()+"+"+turret.getTimeToReload()+"<= " +tickInMilliseconds+".");
                // if the Turret has already reloaded
                if( (turret.getLastShot()+turret.getTimeToReload()) <= tickInMilliseconds ){

                    Attacker target = turret.getCurrentTarget();

                    target.inflictDamage(turret.getDamage());
                    turret.setLastShot(tickInMilliseconds);

                    LOGGER.debug("CAN SHOOT => Turret with id="+turret.getId()+" inflicts "+turret.getDamage()+" on attacker with id="+target.getId());

                    // no health...itz dead
                    if(target.isDead()){
                        LOGGER.debug("IS DEAD => attacker with id="+target.getId());
                        AttackerDied attackerDiesEvent = new AttackerDied(target.getId());
                        attackerDiesEvent.setTime(tickInMilliseconds);
                        gameState.getEvents().add(attackerDiesEvent);

                        // if an attacker dies all the turrets targeting it will loose their target
                        for(Turret turretTargeting : target.getBeingTargetedBy()){
                            turretTargeting.setCurrentTarget(null);
                            TowerLostTarget lostTargetEvent = new TowerLostTarget(turretTargeting.getId());
                            lostTargetEvent.setAttackerId(target.getId());
                            lostTargetEvent.setTime(tickInMilliseconds);
                            gameState.getEvents().add(lostTargetEvent);
                        }
                        target.getBeingTargetedBy().clear();
                        gameState.getAttackers().remove(target);

                    }
                }

            }

        }

    }


}
