package de.overwatch.otd.controller;


import de.overwatch.otd.domain.defend.TowerBlueprint;
import de.overwatch.otd.repository.TowerBlueprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.API_PATH_PREFIX+"/towerBlueprint")
public class TowerBlueprintController {

    @Autowired
    private TowerBlueprintRepository towerBlueprintRepository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<TowerBlueprint> index() {
        return towerBlueprintRepository.findAll();
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public @ResponseBody TowerBlueprint show(@PathVariable("id") Integer id) {
        return towerBlueprintRepository.findOne(id);
    }

}
