/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.dao.rowmapper.DocsRowMapper;
import com.sina.sina.models.Docs;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mphj
 */
@Repository
public class DocsDao extends AbstractDao{

    @Override
    public String getTableName() {
        return "docs";
    }

    
    
    public void insert(Docs cm) {
        jdbcTemplate
                .update("insert into `" + getTableName() + "` values(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        cm.getId(),
                        cm.getName(),
                        cm.getDesc(),
                        cm.getType(),
                        cm.getOid(),
                        cm.getVid(),
                        cm.getCid(),
                        cm.getDid(),
                        cm.getRid());
    }
    
    public Docs findById(int id) {
        return (Docs) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "` where id = ?",
                        new Object[]{id},
                        new DocsRowMapper());
    }
    
    
    public Docs findByType(int id) {
        return (Docs) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "` where type = ?",
                        new Object[]{id},
                        new DocsRowMapper());
    }
    
}
