package com.dissdoc.github.service.impl;

import com.dissdoc.github.dao.AccountDao;
import com.dissdoc.github.model.Account;
import com.dissdoc.github.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by KMukhov on 20.07.2015.
 */
@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    public Account findByUsername(String username) {
        return accountDao.findByUsername(username);
    }
}
