package com.dissdoc.github.domain.dao.impl;

import com.dissdoc.github.domain.dao.AccountDao;
import com.dissdoc.github.domain.dao.JdbcDao;
import com.dissdoc.github.domain.model.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by KMukhov on 21.07.2015.
 */
@Repository
public class AccountDaoImpl extends JdbcDao implements AccountDao {

    public Account findByUsername(String username) throws DataAccessException {
        return template().queryForObject("select * from account where username=?",
                new AccountRowMapper(), username);
    }

    public static class AccountRowMapper implements RowMapper<Account> {
        public Account mapRow(ResultSet rs, int rownum) throws SQLException {
            return new Account(rs.getString("username"), rs.getString("password"), rs.getString("firstName"),
                    rs.getString("lastName"));
        }
    }
}
