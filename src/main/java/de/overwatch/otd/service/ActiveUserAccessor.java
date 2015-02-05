package de.overwatch.otd.service;


import org.springframework.security.core.userdetails.UserDetails;

public interface ActiveUserAccessor {

    UserDetails getActiveUser();
}
