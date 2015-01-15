package de.overwatch.otd.controller;


import de.overwatch.otd.domain.attack.AttackerBlueprint;
import de.overwatch.otd.domain.defend.TowerBlueprint;
import de.overwatch.otd.repository.AttackerBlueprintRepository;
import de.overwatch.otd.repository.TowerBlueprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
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
