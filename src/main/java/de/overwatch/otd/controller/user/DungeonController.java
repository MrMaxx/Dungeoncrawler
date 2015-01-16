package de.overwatch.otd.controller.user;


import de.overwatch.otd.controller.ApiConstants;
import de.overwatch.otd.domain.User;
import de.overwatch.otd.domain.defend.Dungeon;
import de.overwatch.otd.repository.DungeonRepository;
import de.overwatch.otd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Todo: Authorization is really required here :)
 */
@Controller
@RequestMapping(ApiConstants.API_PATH_PREFIX+"/user/{userId}/dungeon")
public class DungeonController {

    @Autowired
    private DungeonRepository dungeonRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Dungeon> index(@PathVariable("userId") Integer userId) {
        return dungeonRepository.findByUserId(userId);
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public @ResponseBody Dungeon show(@PathVariable("id") Integer id,
                                          @PathVariable("userId") Integer userId) {
        return dungeonRepository.findByIdAndUserId(id, userId);
    }

    /**
     * Todo: authorization
     */
    @RequestMapping( method = RequestMethod.POST)
    public @ResponseBody Dungeon create(
            @PathVariable("userId") Integer userId,
            @RequestParam(required = true) Integer dungeonBlueprintId) {

        User user = userRepository.getOne(userId);

        Dungeon dungeon = new Dungeon();

        dungeon.setDungeonBlueprintId(dungeonBlueprintId);
        dungeon.setUser(user);

        dungeonRepository.save(dungeon);

        return dungeon;
    }

    /**
     * Todo: authorization and return http status code 204
     */
    @RequestMapping( method = RequestMethod.DELETE)
    public @ResponseBody void delete(
            @PathVariable("userId") Integer userId,
            @RequestParam(required = true) Integer id) {

        dungeonRepository.delete(id);

        return;
    }

}
