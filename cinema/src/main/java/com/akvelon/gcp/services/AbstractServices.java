package com.akvelon.gcp.services;

import com.akvelon.gcp.security.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author Andrey Kustov on 12.12.2021
 * service super
 */
public abstract class AbstractServices {

    /**
     * Current User
     *
     * @return
     */
    public User getCurrentUser() {
        Optional object = Optional.ofNullable(SecurityContextHolder.getContext()).map(SecurityContext::getAuthentication).map(Authentication::getPrincipal);
        User user = null;
        if (object.isPresent() && object.get() instanceof User) {
            user = (User) object.get();
        }
        return user;
    }
}
