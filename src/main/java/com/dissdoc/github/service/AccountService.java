package com.dissdoc.github.service;

import com.dissdoc.github.model.Account;

/**
 * Created by KMukhov on 20.07.2015.
 */
public interface AccountService {

    Account findByUsername(String username);
}
