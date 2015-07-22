package com.dissdoc.github.domain.dao.impl;

import com.dissdoc.github.domain.dao.AccountDao;
import com.dissdoc.github.domain.dao.JdbcDao;
import com.dissdoc.github.domain.model.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by KMukhov on 21.07.2015.
 */
@Repository
public class AccountDaoImpl extends JdbcDao implements AccountDao {

    private SimpleJdbcInsert insertAccountEntry;

    @PostConstruct
    private void init() {
        insertAccountEntry = new SimpleJdbcInsert(getDataSource()).withTableName("account")
                .usingGeneratedKeyColumns("id");
    }

    public Account findByUsername(String username) throws DataAccessException {
        return template().queryForObject("select * from account where username=?",
                new AccountRowMapper(), username);
    }

    public void create(Account account) {
        insertAccountEntry.execute(createAccount(account));
    }

    public static class AccountRowMapper implements RowMapper<Account> {
        public Account mapRow(ResultSet rs, int rownum) throws SQLException {
            return new Account(rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("firstName"),
                    rs.getString("lastName"));
        }
    }

    private MapSqlParameterSource createAccount(Account account) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("username", account.getUsername())
                .addValue("password", account.getPassword())
                .addValue("firstName", account.getFirstName())
                .addValue("lastName", account.getLastName());

        return params;
    }
}
