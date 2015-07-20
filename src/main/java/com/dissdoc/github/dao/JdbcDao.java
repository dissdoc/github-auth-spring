package com.dissdoc.github.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;

/**
 * Created by KMukhov on 20.07.2015.
 */
public class JdbcDao extends JdbcDaoSupport {

    private NamedParameterJdbcTemplate namedJdbcParameter;

    @Autowired
    private void init(DataSource dataSource) {
        setDataSource(dataSource);

        namedJdbcParameter = new NamedParameterJdbcTemplate(template());
    }

    public JdbcTemplate template() {
        return getJdbcTemplate();
    }

    public NamedParameterJdbcTemplate namedTemplate() {
        return namedJdbcParameter;
    }
}
