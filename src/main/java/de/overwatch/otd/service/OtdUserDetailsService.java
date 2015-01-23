package de.overwatch.otd.service;

import de.overwatch.otd.domain.Role;
import de.overwatch.otd.domain.User;
import de.overwatch.otd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class OtdUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("User %s not exist!", username));
        }
        return new UserRepositoryUserDetails(user);
    }
    private final static class UserRepositoryUserDetails implements UserDetails {

        private static final long serialVersionUID = 1L;

        private User user;

        private UserRepositoryUserDetails(User user) {
            this.user = user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            if( user.getAuthorities() != null && user.getAuthorities().contains(Role.ADMIN) ){
                return AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
            }
            if( user.getAuthorities() != null && user.getAuthorities().contains(Role.USER) ){
                return AuthorityUtils.createAuthorityList("ROLE_USER");
            }
            return Collections.emptySet();
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getUsername();
        }
        @Override
        public boolean isAccountNonExpired() {
            return user.getEnabled();
        }
        @Override
        public boolean isAccountNonLocked() {
            return user.getEnabled();
        }
        @Override
        public boolean isCredentialsNonExpired() {
            return user.getEnabled();
        }
        @Override
        public boolean isEnabled() {
            return user.getEnabled();
        }
    }
}
