package de.overwatch.otd.controller;


import de.overwatch.otd.domain.defend.DungeonBlueprint;
import de.overwatch.otd.repository.DungeonBlueprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.API_PATH_PREFIX+"/dungeonBlueprint")
public class DungeonBlueprintController {

    @Autowired
    private DungeonBlueprintRepository dungeonBlueprintRepository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<DungeonBlueprint> index() {
        return dungeonBlueprintRepository.findAllDistinctByOrderByIdAsc();
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public @ResponseBody DungeonBlueprint show(@PathVariable("id") Integer id) {
        return dungeonBlueprintRepository.findById(id);
    }

}
