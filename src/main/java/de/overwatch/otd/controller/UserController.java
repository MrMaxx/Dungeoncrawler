package de.overwatch.otd.controller;


import de.overwatch.otd.domain.User;
import de.overwatch.otd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(ApiConstants.API_PATH_PREFIX+"/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public @ResponseBody User show(@PathVariable("id") Integer id) {
        return userRepository.findOne(id);
    }

}
