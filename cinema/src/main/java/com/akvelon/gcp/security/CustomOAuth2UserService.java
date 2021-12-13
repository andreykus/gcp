package com.akvelon.gcp.security;

import com.akvelon.gcp.services.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;


/**
 * @author Andrey Kustov on 12.12.2021
 */
@Service
public class CustomOAuth2UserService extends OidcUserService {

    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    UserService userService;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        CustomOAuth2User userInfo = new  CustomOAuth2User(oidcUser);
        updateUser(userInfo);
        return oidcUser;
    }

    private void updateUser(CustomOAuth2User  userInfo) {
        UserDetails ud =  userService.loadUserByUsername(userInfo.getEmail());
    }

}