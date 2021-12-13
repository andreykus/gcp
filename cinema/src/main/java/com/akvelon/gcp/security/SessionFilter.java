package com.akvelon.gcp.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.servlet.*;
import java.io.IOException;
import java.util.Map;


/**
 * @author Andrey Kustov on 12.12.2021
 */
public class SessionFilter implements Filter {


    private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);

    @Autowired
    UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication a = context.getAuthentication();
        //rewrite context with real user
        if (a != null && a instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken at = (OAuth2AuthenticationToken) a;
            OAuth2User pr = at.getPrincipal();
            Map<String, Object> details = pr.getAttributes();
            logger.debug("email .." + details.get("email"));
            Authentication newAuth = new UsernamePasswordAuthenticationToken(userService.loadUserByUsername(String.valueOf(details.get("email"))), null, null);
            context.setAuthentication(newAuth);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
