package com.dissdoc.github.domain.service;

import com.dissdoc.github.domain.model.Account;

/**
 * Created by KMukhov on 21.07.2015.
 */
public interface AccountService {

    Account findByUsername(String username);

    void create(Account account);
}
