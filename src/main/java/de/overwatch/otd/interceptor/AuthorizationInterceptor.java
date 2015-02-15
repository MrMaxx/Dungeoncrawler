package de.overwatch.otd.interceptor;


import de.overwatch.otd.domain.User;
import de.overwatch.otd.repository.UserRepository;
import de.overwatch.otd.service.ActiveUserAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor{

    @Autowired
    private ActiveUserAccessor activeUserAccessor;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getContextPath().contains("api/v1/user/")){
            UserDetails details = activeUserAccessor.getActiveUser();

            Map<String, String> uriParameters = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            if(uriParameters.containsKey("userId")){
                Integer userId = Integer.getInteger(uriParameters.get("userId"));
                User user = userRepository.findOne(userId);
                if(user.getUsername().equals(details.getUsername())){
                    throw new IllegalStateException("UserData is private to a User and can only be edited by him/herself.");
                }
            }else{
                // then we have users index page, which is ok to be freely accessible
            }

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {}
}
