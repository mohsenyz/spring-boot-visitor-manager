/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 *
 * @author mphj
 */
public abstract class AbstractDao {

    protected JdbcTemplate jdbcTemplate;
    protected SimpleJdbcInsert simpleJdbcInsert;
    
    
    @Autowired
    protected SessionFactory _sessionFactory;

    protected Session getSession() {
        return _sessionFactory.getCurrentSession();
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(getTableName())
                .usingGeneratedKeyColumns("id");
    }

    public abstract String getTableName();
}
