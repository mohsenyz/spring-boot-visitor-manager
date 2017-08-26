/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.dao.rowmapper.DsRowMapper;
import com.sina.sina.models.Ds;

/**
 *
 * @author mphj
 */
public class DsDao extends AbstractDao{

    @Override
    public String getTableName() {
        return "ds";
    }

    
    public void insert(Ds cm) {
        jdbcTemplate
                .update("insert into `" + getTableName() + "` values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        cm.getId(),
                        cm.getName(),
                        cm.getClerkName(),
                        cm.getCompanyProductsAck(),
                        cm.getAddress(),
                        cm.getBestTime(),
                        cm.getType(),
                        cm.getCompanyNameAckReason(),
                        cm.getUsername(),
                        cm.getPassword(),
                        cm.getCity(),
                        cm.isVerified(),
                        cm.getVerifiedAt(),
                        cm.getCreatedAt(),
                        cm.isEnabled());
    }
    
    public Ds findById(int id) {
        return (Ds) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "` where id = ?",
                        new Object[]{id},
                        new DsRowMapper());
    }
    
    
    public Ds findByUsername(String pass) {
        return (Ds) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "` where username = ?",
                        new Object[]{pass},
                        new DsRowMapper());
    }
    
}
