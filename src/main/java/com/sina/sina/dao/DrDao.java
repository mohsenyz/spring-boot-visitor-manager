/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.dao.rowmapper.DrRowMapper;
import com.sina.sina.models.Dr;

/**
 *
 * @author mphj
 */
public class DrDao extends AbstractDao{

    @Override
    public String getTableName() {
        return "dr";
    }
    
    public void insert(Dr cm) {
        jdbcTemplate
                .update("insert into `" + getTableName() + "` values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        cm.getId(),
                        cm.getName(),
                        cm.getExpert(),
                        cm.getBestVisitTime1(),
                        cm.getFixedPhone(),
                        cm.getMobile(),
                        cm.getMkdb(),
                        cm.getPlace(),
                        cm.getAddress(),
                        cm.getEmail(),
                        cm.getPezeshk(),
                        cm.getCompanyProductsAck(),
                        cm.getCompanyProductsPop(),
                        cm.getSuggestion());
    }
    
    public Dr findById(int id) {
        return (Dr) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "` where id = ?",
                        new Object[]{id},
                        new DrRowMapper());
    }
    
}
