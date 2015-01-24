package de.overwatch.otd.controller;


import de.overwatch.otd.domain.attack.AttackerBlueprint;
import de.overwatch.otd.repository.AttackerBlueprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.API_PATH_PREFIX+"/attackerBlueprint")
public class AttackerBlueprintController {

    @Autowired
    private AttackerBlueprintRepository attackerBlueprintRepository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<AttackerBlueprint> index() {
        return attackerBlueprintRepository.findAll();
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public @ResponseBody AttackerBlueprint show(@PathVariable("id") Integer id) {
        return attackerBlueprintRepository.findOne(id);
    }

}
