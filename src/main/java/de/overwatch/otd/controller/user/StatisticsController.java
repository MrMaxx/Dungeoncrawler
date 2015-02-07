package de.overwatch.otd.controller.user;


import de.overwatch.otd.controller.ApiConstants;
import de.overwatch.otd.domain.Role;
import de.overwatch.otd.domain.User;
import de.overwatch.otd.repository.UserRepository;
import de.overwatch.otd.service.ActiveUserAccessor;
import de.overwatch.otd.service.UserStatistic;
import de.overwatch.otd.service.UserStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.API_PATH_PREFIX+"/user/{userId}/statistic")
public class StatisticsController {

    @Autowired
    private UserStatisticService userStatisticService;


    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody UserStatistic show(@PathVariable("userId") Integer userId) {
        return userStatisticService.getUserStatiStatistic(userId);
    }


}
