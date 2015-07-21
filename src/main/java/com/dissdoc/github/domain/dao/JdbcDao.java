package com.dissdoc.github.domain.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.inject.Inject;
import javax.sql.DataSource;

/**
 * Created by KMukhov on 21.07.2015.
 */
public abstract class JdbcDao extends JdbcDaoSupport {

    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Inject
    private void init(DataSource dataSource) {
        setDataSource(dataSource);

        namedJdbcTemplate = new NamedParameterJdbcTemplate(template());
    }

    public JdbcTemplate template() {
        return getJdbcTemplate();
    }

    public NamedParameterJdbcTemplate namedTemplate() {
        return namedJdbcTemplate;
    }
}
