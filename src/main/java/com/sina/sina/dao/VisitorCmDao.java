/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.dao.rowmapper.VisitorCmRowMapper;
import com.sina.sina.models.VisitorCm;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mphj
 */
@Repository
@Transactional
public class VisitorCmDao extends AbstractDao {

    @Override
    public String getTableName() {
        return "visitor_cm";
    }
    
    public void insert(VisitorCm cm) {
        /*jdbcTemplate
                .update("insert into `" + getTableName() + "` values(?, ?, ?)",
                        cm.getId(),
                        cm.getVid(),
                        cm.getCid());*/
        getSession().save(cm);
    }

    public VisitorCm findById(int id) {
        return (VisitorCm) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "` where id = ?",
                        new Object[]{id},
                        new VisitorCmRowMapper());
    }
}
