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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Todo: Authorization is really required here :)
 */
@Controller
@RequestMapping(ApiConstants.API_PATH_PREFIX+"/user/{userId}/fight")
public class FightController {

    @Autowired
    private FightService fightService;

    @Autowired
    private FightRepository fightRepository;
    @Autowired
    private DungeonRepository dungeonRepository;
    @Autowired
    private AttackForceRepository attackForceRepository;


    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<PublicFight> index(@PathVariable("userId") Integer userId) {
        return fightService.getPublicFights(userId);
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public @ResponseBody PublicFight show(@PathVariable("id") Integer id,
                                          @PathVariable("userId") Integer userId) {
        return fightService.getPublicFight(id, userId);
    }

    @RequestMapping(value="{id}/yevents", method = RequestMethod.GET)
    public @ResponseBody String events(@PathVariable("id") Integer id,
                                          @PathVariable("userId") Integer userId) {
        Fight fight = fightRepository.findByIdAndUserId(id, userId);

        if(fight.getFightState() != Fight.FightState.COMPLETED){
            return null;
        }

        return fight.getEvents();
    }

    /**
     * Todo: authorization
     * Todo: validation if the attacker belongs to the given attackForce
     * Todo: validation if the attacker is the same as the defender (you should not attack yourself)
     */
    @RequestMapping( method = RequestMethod.POST)
    public @ResponseBody PublicFight create(
            @PathVariable("userId") Integer userId,
            @RequestParam(required = true) Integer dungeonId,
            @RequestParam(required = true) Integer attackForceId) {

        Dungeon dungeon = dungeonRepository.findOne(dungeonId);
        AttackForce attackForce = attackForceRepository.findOne(attackForceId);
        Fight fight = new Fight();
        fight.setDungeon(dungeon);
        fight.setAttackForce(attackForce);
        fight.setFightState(Fight.FightState.ISSUED);
        fight.setCreated(new Date());

        fightRepository.save(fight);

        return new PublicFight(fight);
    }

}
