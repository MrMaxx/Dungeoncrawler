package de.overwatch.otd.controller;


import de.overwatch.otd.domain.Role;
import de.overwatch.otd.domain.User;
import de.overwatch.otd.domain.attack.AttackForce;
import de.overwatch.otd.domain.defend.Dungeon;
import de.overwatch.otd.repository.AttackForceRepository;
import de.overwatch.otd.repository.DungeonRepository;
import de.overwatch.otd.repository.UserRepository;
import de.overwatch.otd.service.ActiveUserAccessor;
import de.overwatch.otd.service.ranking.PagedUserList;
import de.overwatch.otd.service.ranking.UserRankingService;
import de.overwatch.otd.service.ranking.UserWithRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.API_PATH_PREFIX+"/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActiveUserAccessor activeUserAccessor;

    @Autowired
    private DungeonRepository dungeonRepository;

    @Autowired
    private AttackForceRepository attackForceRepository;

    @Autowired
    private UserRankingService userRankingService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody PagedUserList index(
            @RequestParam Integer pagesize,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer userId) {

        if(page == null){ page = 1; }
        if(pagesize == null){ pagesize = 30; }


        if(userId != null){
            return userRankingService.getUserRanking(userId, pagesize);
        }

        return userRankingService.getUserPage(page, pagesize);
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public @ResponseBody User show(@PathVariable("id") Integer id) {
        return userRepository.findOne(id);
    }

    @RequestMapping(value="me", method = RequestMethod.GET)
    public @ResponseBody UserWithRank me() {
        UserDetails userDetails = activeUserAccessor.getActiveUser();
        User user = userRepository.findByUsername(userDetails.getUsername());
        return userRankingService.getUserWithRank(user.getId());
    }

    /**
     * Todo: Add Validation for Emails and Hashing of Passwords (after Spring Security is configured)
     */
    @RequestMapping( method = RequestMethod.POST)
    public @ResponseBody User create(
            @RequestParam(required = true) String email,
            @RequestParam(required = true) String username,
            @RequestParam(required = true) String password) {

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setEnabled(true);
        user.setPassword(password);

        HashSet<Role> roles = new HashSet<Role>();
        roles.add(Role.USER);

        user.setAuthorities(roles);

        User persistedUser = userRepository.save(user);

        // every User gets one Dungeon for now
        Dungeon dungeon = new Dungeon();
        dungeon.setDungeonBlueprintId(1);
        dungeon.setUser(persistedUser);
        dungeonRepository.save(dungeon);

        AttackForce attackForce = new AttackForce();
        attackForce.setUser(user);
        attackForce.setAttackForcePatternId(1);
        attackForceRepository.save(attackForce);

        userRankingService.addUser(persistedUser, true);

        return user;
    }
}
