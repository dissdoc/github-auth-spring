package com.dissdoc.github.domain.service.impl;

import com.dissdoc.github.domain.dao.AccountDao;
import com.dissdoc.github.domain.model.Account;
import com.dissdoc.github.domain.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by KMukhov on 21.07.2015.
 */
@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {

    @Inject
    private AccountDao accountDao;

    public Account findByUsername(String username) {
        return accountDao.findByUsername(username);
    }
}
