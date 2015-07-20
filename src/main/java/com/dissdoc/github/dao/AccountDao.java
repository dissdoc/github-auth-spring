package com.dissdoc.github.dao;

import com.dissdoc.github.model.Account;

/**
 * Created by KMukhov on 20.07.2015.
 */
public interface AccountDao {

    Account findByUsername(String username);
}
