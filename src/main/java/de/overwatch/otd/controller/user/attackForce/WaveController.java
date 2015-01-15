package de.overwatch.otd.controller.user.attackForce;


import de.overwatch.otd.controller.ApiConstants;
import de.overwatch.otd.domain.User;
import de.overwatch.otd.domain.attack.AttackForce;
import de.overwatch.otd.domain.attack.Wave;
import de.overwatch.otd.repository.AttackForceRepository;
import de.overwatch.otd.repository.UserRepository;
import de.overwatch.otd.repository.WaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Todo: Authorization is really required here :)
 */
@Controller
@RequestMapping(ApiConstants.API_PATH_PREFIX+"/user/{userId}/attackForce/{attackForceId}/wave")
public class WaveController {

    @Autowired
    private AttackForceRepository attackForceRepository;

    @Autowired
    private WaveRepository waveRepository;


    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Wave> index(@PathVariable("userId") Integer userId,
                                          @PathVariable("attackForceId") Integer attackForceId) {
        return waveRepository.findByAttackForceIdAndUserId(attackForceId, userId);
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public @ResponseBody Wave show(@PathVariable("id") Integer id,
                                   @PathVariable("userId") Integer userId,
                                   @PathVariable("attackForceId") Integer attackForceId) {
        return waveRepository.findByIdAndAttackForceIdAndUserId(id, attackForceId, userId);
    }

    /**
     * Todo: Validation if AttackForce exists and proper errorcodes on failure
     * Todo: Validate if AttackerBlueprint exists and proper errorcodes on failure
     */
    @RequestMapping( method = RequestMethod.POST)
    public @ResponseBody Wave create(
            @PathVariable("userId") Integer userId,
            @PathVariable("attackForceId") Integer attackForceId,
            @RequestParam(required = true) Integer attackerBlueprintId) {

        AttackForce attackForce = attackForceRepository.findByIdAndUserId(attackForceId, userId);

        Wave wave = new Wave();
        wave.setAttackerBlueprintId(attackerBlueprintId);
        wave.setAttackForce(attackForce);

        waveRepository.save(wave);

        return wave;
    }

    /**
     * Todo: authorization and return http status code 204
     * Todo: Validation if AttackForce exists + belongs to user and proper errorcodes on failure
     *
     */
    @RequestMapping( method = RequestMethod.DELETE)
    public @ResponseBody void delete(
            @PathVariable("userId") Integer userId,
            @PathVariable("attackForceId") Integer attackForceId,
            @RequestParam(required = true) Integer id) {

        AttackForce attackForce = attackForceRepository.findByIdAndUserId(attackForceId, userId);

        attackForceRepository.delete(id);

        return;
    }

}
