/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.dao.rowmapper.CmRowMapper;
import com.sina.sina.models.Cm;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mphj
 */

@Repository
public class CmDao extends AbstractDao {

    public void insert(Cm cm) {
        jdbcTemplate
                .update("insert into `" + getTableName() + "` values(?, ?, ?, ?, ?, ?, ?)",
                        cm.getId(),
                        cm.getName(),
                        cm.getPhone(),
                        cm.getUsername(),
                        cm.getPassword(),
                        cm.getCreatedAt(),
                        cm.isEnabled());
    }

    public Cm findById(int id) {
        return (Cm) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "` where id = ?",
                        new Object[]{id},
                        new CmRowMapper());
    }
    
    @Override
    public String getTableName() {
        return "cm";
    }

}
