/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.dao.rowmapper.VisitorRowMapper;
import com.sina.sina.models.Visitor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mphj
 */
@Repository
public class VisitorDao extends AbstractDao{

    @Override
    public String getTableName() {
        return "visitor";
    }
    
    public void insert(Visitor cm) {
        jdbcTemplate
                .update("insert into `" + getTableName() + "` values(?, ?, ?, ?, ?, ?, ?, ?)",
                        cm.getId(),
                        cm.getFname(),
                        cm.getLname(),
                        cm.getUsername(),
                        cm.getPassword(),
                        cm.getType(),
                        cm.getCreatedAt(),
                        cm.isEnabled());
    }
    
    public Visitor findById(int id) {
        return (Visitor) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "` where id = ?",
                        new Object[]{id},
                        new VisitorRowMapper());
    }
    
    
}
