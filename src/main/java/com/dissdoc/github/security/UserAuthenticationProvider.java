package com.dissdoc.github.security;

import com.dissdoc.github.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.Assert;

/**
 * Created by KMukhov on 20.07.2015.
 */
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AccountService accountService;

    private UserDetailsService userDetailsService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String inputUserName = authentication.getName();
        String inputPassword = authentication.getCredentials().toString();
        if ((inputUserName == null || inputUserName.trim().length() == 0))
            throw new BadCredentialsException("User name is empty!");

        if ((inputPassword == null || inputPassword.trim().length() == 0))
            throw new BadCredentialsException("Password is empty!");

        // Retrieve user details

        UserDetails user = userDetailsService.loadUserByUsername(inputUserName);

        Assert.notNull(user, "retrieveUser returned null - a violation of the interface contract");

        // Check password

        String encodedPassword = inputPassword;
        if (encodedPassword.equals(user.getPassword())) {
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
