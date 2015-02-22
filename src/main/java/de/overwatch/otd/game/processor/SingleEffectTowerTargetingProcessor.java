package de.overwatch.otd.game.processor;


import de.overwatch.otd.domain.defend.TowerBlueprint;
import de.overwatch.otd.game.GameState;
import de.overwatch.otd.game.events.AttackerDied;
import de.overwatch.otd.game.events.TowerEffectsAttacker;
import de.overwatch.otd.game.events.TowerLostTarget;
import de.overwatch.otd.game.events.TowerTargetedAttacker;
import de.overwatch.otd.game.model.Attacker;
import de.overwatch.otd.game.model.Turret;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Todo: i would feel better if i would add some testing to this one :)
 *
 */
public class SingleEffectTowerTargetingProcessor {

    private static final Logger LOGGER = Logger.getLogger(SingleEffectTowerTargetingProcessor.class);

    public static void process(GameState gameState, int tickInMilliseconds){

        Set<Integer> markedForEffectTermination = new HashSet<Integer>();
        /*
            Marking for Effect ended...
            BUT if attacker is targeted in the same tick by the same/another EffectTower...
            Then we just lengthen the current effect
        */
        for(Attacker attacker : gameState.getAttackers()){
            if(!attacker.hasNoEffect() &&
                    attacker.getEffectWearsOffAt() <= tickInMilliseconds ){
                //attacker.setEffectEnded(tickInMilliseconds);
                markedForEffectTermination.add(attacker.getId());
            }
        }

        for(Turret turret : gameState.getTurrets()){


            // if there is no target for turret, search for one
            if( turret.getTowerEffect() == TowerBlueprint.TowerEffect.SINGLE_EFFECT &&
                    (turret.getLastShot()+turret.getTimeToReload()) <= tickInMilliseconds){


                for(Attacker attacker : gameState.getAttackers()){
                    // only add if attacker is in range and there is still no currentTarget...else we could double add
                    LOGGER.debug("NEWLY_IN_RANGE => checking: "+turret.getCoordinate()+" to "+ attacker.getCoordinate()+" <= "+turret.getRange()+" > "+(turret.getCoordinate().getDistanceTo(attacker.getCoordinate())));

                    if( turret.getCoordinate().getDistanceTo(attacker.getCoordinate()) <= turret.getRange() &&
                            (attacker.hasNoEffect() || markedForEffectTermination.contains(attacker.getId()))){
                        LOGGER.debug("NEWLY_IN_RANGE AND NO EFFECT YET => ITS IN RANGE");

                        if(markedForEffectTermination.contains(attacker.getId())){
                            attacker.lengthenEffect(tickInMilliseconds + turret.getEffectWearsOffInMilliseconds());
                            markedForEffectTermination.remove(attacker.getId());
                        }else {
                            attacker.setEffectStarted(tickInMilliseconds, turret.getSlowsDownToPercent(), tickInMilliseconds + turret.getEffectWearsOffInMilliseconds());
                        }

                        TowerEffectsAttacker event = new TowerEffectsAttacker(turret.getId());
                        event.setAttackerId(attacker.getId());
                        event.setTime(tickInMilliseconds);
                        event.setEffectWearsOffAt(tickInMilliseconds+turret.getEffectWearsOffInMilliseconds());

                        gameState.addEvent(tickInMilliseconds, event);

                        turret.setLastShot(tickInMilliseconds);

                        LOGGER.debug("NEW_TARGET => Turret with id="+turret.getId()+" now targets attacker with id="+attacker.getId());
                        break;
                    }
                }

            }

        }

        for(Attacker attacker : gameState.getAttackers()){
            if(markedForEffectTermination.contains(attacker.getId()) ){
                attacker.setEffectEnded(tickInMilliseconds);
            }
        }

    }


}
