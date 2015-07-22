package com.dissdoc.github.domain.dao;

import com.dissdoc.github.domain.model.Account;

/**
 * Created by KMukhov on 21.07.2015.
 */
public interface AccountDao {

    Account findByUsername(String username);

    void create(Account account);
}
