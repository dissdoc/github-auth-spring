package com.dissdoc.github.security;

import com.dissdoc.github.model.Account;
import com.dissdoc.github.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KMukhov on 20.07.2015.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = null;

        try {
            account = accountService.findByUsername(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (account == null) {
            throw new UsernameNotFoundException("username=" + s);
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_USER");
        authorities.add(auth);

        return new UserDetailsImpl(account, authorities);
    }

    public static class LoadUserDataAccessException extends DataAccessException {

        public LoadUserDataAccessException(String msg) {
            super(msg);
        }

        public LoadUserDataAccessException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }

}
