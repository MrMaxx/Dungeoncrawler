package de.overwatch.otd.controller;


import de.overwatch.otd.domain.defend.DungeonBlueprint;
import de.overwatch.otd.repository.DungeonBlueprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(ApiConstants.API_PATH_PREFIX+"/dungeonBlueprint")
public class DungeonBlueprintController {

    @Autowired
    private DungeonBlueprintRepository dungeonBlueprintRepository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<DungeonBlueprint> index() {
        return dungeonBlueprintRepository.findAllByOrderByIdAsc();
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public @ResponseBody DungeonBlueprint show(@PathVariable("id") Integer id) {
        return dungeonBlueprintRepository.findById(id);
    }

}
