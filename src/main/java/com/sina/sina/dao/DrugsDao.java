/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.dao.rowmapper.DrugsRowMapper;
import com.sina.sina.models.Drugs;

/**
 *
 * @author mphj
 */
public class DrugsDao extends AbstractDao{

    @Override
    public String getTableName() {
        return "drugs";
    }
    
    
    public void insert(Drugs cm) {
        jdbcTemplate
                .update("insert into `" + getTableName() + "` values(?, ?)",
                        cm.getId(),
                        cm.getName());
    }
    
    public Drugs findById(int id) {
        return (Drugs) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "` where id = ?",
                        new Object[]{id},
                        new DrugsRowMapper());
    }
}
