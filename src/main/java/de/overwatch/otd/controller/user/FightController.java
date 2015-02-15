package de.overwatch.otd.controller.user;


import de.overwatch.otd.controller.ApiConstants;
import de.overwatch.otd.domain.Fight;
import de.overwatch.otd.domain.attack.AttackForce;
import de.overwatch.otd.domain.defend.Dungeon;
import de.overwatch.otd.repository.AttackForceRepository;
import de.overwatch.otd.repository.DungeonRepository;
import de.overwatch.otd.repository.FightRepository;
import de.overwatch.otd.service.FightService;
import de.overwatch.otd.service.PublicFight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Todo: Authorization is really required here :)
 */
@RestController
@RequestMapping(ApiConstants.API_PATH_PREFIX+"/user/{userId}/fight")
public class FightController {

    @Autowired
    private FightService fightService;

    @Autowired
    private FightRepository fightRepository;



    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<PublicFight> index(@PathVariable("userId") Integer userId) {
        return fightService.getPublicFights(userId);
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public @ResponseBody PublicFight show(@PathVariable("id") Integer id,
                                          @PathVariable("userId") Integer userId) {
        return fightService.getPublicFight(id, userId);
    }

    @RequestMapping(value="{id}/events", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String events(@PathVariable("id") Integer id,
                                          @PathVariable("userId") Integer userId) {
        Fight fight = fightRepository.findByIdAndUserId(id, userId);

        if(fight==null){ return null; }

        if(fight.getFightState() != Fight.FightState.COMPLETED){
            return null;
        }

        return fight.getEvents();
    }

    /**
     * Todo: remove...only temporary for the MVP Prototype
     *
     * Todo: authorization
     * Todo: validation if the attacker belongs to the given attackForce
     * Todo: validation if the attacker is the same as the defender (you should not attack yourself)
     */
    /*
    @RequestMapping( method = RequestMethod.POST)
    public @ResponseBody PublicFight create(
            @PathVariable("userId") Integer userId,
            @RequestParam(required = true) Integer dungeonId,
            @RequestParam(required = true) Integer attackForceId) {

        return fightService.createFight(dungeonId, attackForceId);
    }
     */

    /**
     * Todo: remove...only temporary for the MVP Prototype
     */
    @RequestMapping( method = RequestMethod.POST)
    public @ResponseBody PublicFight create(
            @PathVariable("userId") Integer attackingUserId,
            @RequestParam(value="defendingUserId", required = true) Integer defendingUserId) {

        return fightService.createFightAgainst(attackingUserId, defendingUserId);
    }

}
