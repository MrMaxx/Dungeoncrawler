package de.overwatch.otd.controller;


import de.overwatch.otd.domain.attack.AttackForcePattern;
import de.overwatch.otd.repository.AttackForcePatternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.API_PATH_PREFIX+"/attackForcePattern")
public class AttackForcePatternController {

    @Autowired
    private AttackForcePatternRepository attackForcePatternRepository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<AttackForcePattern> index() {
        return attackForcePatternRepository.findAllByOrderByIdAsc();
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public @ResponseBody AttackForcePattern show(@PathVariable("id") Integer id) {
        return attackForcePatternRepository.findById(id);
    }

}
