package de.overwatch.otd.controller.user.tower;


import de.overwatch.otd.controller.ApiConstants;
import de.overwatch.otd.domain.attack.AttackForce;
import de.overwatch.otd.domain.attack.Wave;
import de.overwatch.otd.domain.defend.Dungeon;
import de.overwatch.otd.domain.defend.Tower;
import de.overwatch.otd.repository.AttackForceRepository;
import de.overwatch.otd.repository.DungeonRepository;
import de.overwatch.otd.repository.TowerRepository;
import de.overwatch.otd.repository.WaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Todo: Authorization is really required here :)
 */
@Controller
@RequestMapping(ApiConstants.API_PATH_PREFIX+"/user/{userId}/dungeon/{dungeonId}/tower")
public class TowerController {

    @Autowired
    private DungeonRepository dungeonRepository;

    @Autowired
    private TowerRepository towerRepository;


    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Tower> index(@PathVariable("userId") Integer userId,
                                          @PathVariable("dungeonId") Integer dungeonId) {
        return towerRepository.findByDungeonIdAndUserId(dungeonId, userId);
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public @ResponseBody Tower show(@PathVariable("id") Integer id,
                                   @PathVariable("userId") Integer userId,
                                   @PathVariable("dungeonId") Integer dungeonId) {
        return towerRepository.findByIdAndDungeonIdAndUserId(id, dungeonId, userId);
    }

    /**
     * Todo: Validation if AttackForce exists and proper errorcodes on failure
     * Todo: Validate if AttackerBlueprint exists and proper errorcodes on failure
     */
    @RequestMapping( method = RequestMethod.POST)
    public @ResponseBody Tower create(
            @PathVariable("userId") Integer userId,
            @PathVariable("dungeonId") Integer dungeonId,
            @RequestParam(required = true) Integer towerBlueprintId,
            @RequestParam(required = true) Integer constructionSiteId) {

        Dungeon dungeon = dungeonRepository.findByIdAndUserId(dungeonId, userId);

        Tower tower = new Tower();
        tower.setConstructionSiteId(constructionSiteId);
        tower.setTowerBlueprintId(towerBlueprintId);
        tower.setDungeon(dungeon);

        towerRepository.save(tower);

        return tower;
    }

    /**
     * Todo: authorization and return http status code 204
     * Todo: Validation if AttackForce exists + belongs to user and proper errorcodes on failure
     *
     */
    @RequestMapping( method = RequestMethod.DELETE)
    public @ResponseBody void delete(
            @PathVariable("userId") Integer userId,
            @PathVariable("dungeonId") Integer dungeonId,
            @RequestParam(required = true) Integer id) {

        towerRepository.delete(id);

        return;
    }

}
