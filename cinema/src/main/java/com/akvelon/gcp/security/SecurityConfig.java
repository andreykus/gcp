package com.akvelon.gcp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;



/**
 * @author Andrey Kustov on 12.12.2021
 */
@Configurable
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SessionFilter authenticationFilter() {
        return new SessionFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .antMatcher("/**")
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                .userInfoEndpoint()

                .oidcUserService(customOAuth2UserService)

        ;

        // Add our custom Token filter
        http.addFilterAfter(authenticationFilter(), FilterSecurityInterceptor.class);
    }

}
