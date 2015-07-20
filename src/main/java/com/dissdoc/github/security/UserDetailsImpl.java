package com.dissdoc.github.security;

import com.dissdoc.github.model.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by KMukhov on 20.07.2015.
 */
public class UserDetailsImpl extends User {

    private Account account;

    public UserDetailsImpl(Account account, Collection<? extends GrantedAuthority> authorities) {
        this(account, true, true, true, true, authorities);
    }

    public UserDetailsImpl(Account account, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(account.getUsername(), account.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
