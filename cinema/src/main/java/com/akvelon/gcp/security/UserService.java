package com.akvelon.gcp.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author Andrey Kustov on 12.12.2021
 * service for users
 */
@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User existUser = userMapper.getUserByUserName(username);

        if (existUser == null) {
            User newUser = new User();
            newUser.setGoogle(username);
            newUser.setRole(Role.QUEST);
            userMapper.addUser(newUser);
        }

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication newAuth = new UsernamePasswordAuthenticationToken( existUser  , null, null);
        context.setAuthentication(newAuth);
        logger.info("USERRRRR" + existUser.getId());
        return existUser;
    }
}
