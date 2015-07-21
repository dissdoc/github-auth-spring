package com.dissdoc.github.security;

import com.dissdoc.github.domain.model.Account;
import com.dissdoc.github.domain.service.AccountService;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KMukhov on 21.07.2015.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Inject
    private AccountService accountService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = null;

        try {
            account = accountService.findByUsername(username);
        } catch (Exception ex) {
            throw new LoadUserDataAccessException("username=" + username, ex);
        }

        if (account == null) {
            throw new UsernameNotFoundException("username=" + username);
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
