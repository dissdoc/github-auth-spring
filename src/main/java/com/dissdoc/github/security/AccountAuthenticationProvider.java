package com.dissdoc.github.security;

import com.dissdoc.github.domain.service.AccountService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.inject.Inject;

/**
 * Created by KMukhov on 21.07.2015.
 */
public class AccountAuthenticationProvider implements AuthenticationProvider {

    @Inject
    private AccountService accountService;

    private UserDetailsService userDetailsService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String inputUserName = authentication.getName();
        String inputPassword = authentication.getCredentials().toString();

        if ((inputUserName == null || inputUserName.trim().length() == 0))
            throw new BadCredentialsException("User name is empty!");

        if ((inputPassword == null || inputPassword.trim().length() == 0))
            throw new BadCredentialsException("Password is empty!");

        UserDetails user = userDetailsService.loadUserByUsername(inputUserName);

        if (inputPassword.equals(user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        } else {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
