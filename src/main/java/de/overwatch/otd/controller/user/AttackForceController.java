package de.overwatch.otd.controller.user;


import de.overwatch.otd.controller.ApiConstants;
import de.overwatch.otd.domain.Role;
import de.overwatch.otd.domain.User;
import de.overwatch.otd.domain.attack.AttackForce;
import de.overwatch.otd.repository.AttackForceRepository;
import de.overwatch.otd.repository.UserRepository;
import de.overwatch.otd.repository.WaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

/**
 * Todo: Authorization is really required here :)
 */
@Controller
@RequestMapping(ApiConstants.API_PATH_PREFIX+"/user/{userId}/attackForce")
public class AttackForceController {

    @Autowired
    private AttackForceRepository attackForceRepository;

    @Autowired
    private WaveRepository waveRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<AttackForce> index(@PathVariable("userId") Integer userId) {
        return attackForceRepository.findByUserId(userId);
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public @ResponseBody AttackForce show(@PathVariable("id") Integer id,
                                          @PathVariable("userId") Integer userId) {
        return attackForceRepository.findByIdAndUserId(id, userId);
    }

    @RequestMapping( method = RequestMethod.POST)
    public @ResponseBody AttackForce create(
            @PathVariable("userId") Integer userId,
            @RequestParam(required = true) Integer attackForcePatternId) {

        User user = userRepository.getOne(userId);

        AttackForce attackForce = new AttackForce();
        attackForce.setAttackForcePatternId(attackForcePatternId);
        attackForce.setUser(user);

        attackForceRepository.save(attackForce);

        return attackForce;
    }

    /**
     * Todo: authorization and return http status code 204
     */
    @RequestMapping( method = RequestMethod.DELETE)
    public @ResponseBody void delete(
            @PathVariable("userId") Integer userId,
            @RequestParam(required = true) Integer id) {

        //AttackForce attackForce = attackForceRepository.findByIdAndUserId(id, userId);

        attackForceRepository.delete(id);

        return;
    }

}
