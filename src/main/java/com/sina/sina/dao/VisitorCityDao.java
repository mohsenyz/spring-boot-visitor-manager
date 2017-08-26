/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.dao.rowmapper.VisitorCityRowMapper;
import com.sina.sina.models.VisitorCity;

/**
 *
 * @author mphj
 */
public class VisitorCityDao extends AbstractDao{

    @Override
    public String getTableName() {
        return "visitor_city";
    }
    
    public void insert(VisitorCity cm) {
        jdbcTemplate
                .update("insert into `" + getTableName() + "` values(?, ?, ?)",
                        cm.getId(),
                        cm.getVid(),
                        cm.getCid());
    }
    
    public VisitorCity findById(int id) {
        return (VisitorCity) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "` where id = ?",
                        new Object[]{id},
                        new VisitorCityRowMapper());
    }
}
