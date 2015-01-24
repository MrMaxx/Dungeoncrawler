package de.overwatch.otd.controller;


import de.overwatch.otd.domain.Role;
import de.overwatch.otd.domain.User;
import de.overwatch.otd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.API_PATH_PREFIX+"/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<User> index() {
        return userRepository.findAll();
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public @ResponseBody User show(@PathVariable("id") Integer id) {
        return userRepository.findOne(id);
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

        userRepository.save(user);

        return user;
    }
}
