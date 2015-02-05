package de.overwatch.otd.service;


import de.overwatch.otd.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ActiveUserAccessorImpl implements ActiveUserAccessor {

    @Override
    public UserDetails getActiveUser() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
