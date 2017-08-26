/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.dao.rowmapper.RRowMapper;
import com.sina.sina.models.R;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mphj
 */
@Repository
public class RDao extends AbstractDao{

    @Override
    public String getTableName() {
        return "r";
    }
    
    
    public void insert(R cm) {
        jdbcTemplate
                .update("insert into `" + getTableName() + "` values(?, ?, ?, ?, ?, ?)",
                        cm.getId(),
                        cm.getCmid(),
                        cm.getCreatedAt(),
                        cm.getStatus(),
                        cm.getDesc(),
                        cm.getUrgency());
    }
    
    public R findById(int id) {
        return (R) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "` where id = ?",
                        new Object[]{id},
                        new RRowMapper());
    }
    
}
