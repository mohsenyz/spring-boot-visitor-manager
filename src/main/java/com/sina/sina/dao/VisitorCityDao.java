/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.dao.rowmapper.VisitorCityRowMapper;
import com.sina.sina.models.VisitorCity;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mphj
 */
@Repository
@Transactional
public class VisitorCityDao extends AbstractDao{

    @Override
    public String getTableName() {
        return "visitor_city";
    }
    
    public void insert(VisitorCity cm) {
        /*jdbcTemplate
                .update("insert into `" + getTableName() + "` values(?, ?, ?)",
                        cm.getId(),
                        cm.getVid(),
                        cm.getCid());*/
        getSession().save(cm);
    }
    
    public VisitorCity findById(int id) {
        return (VisitorCity) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "` where id = ?",
                        new Object[]{id},
                        new VisitorCityRowMapper());
    }
    
    
    public List<VisitorCity> findByVid(int id) {
        return (List<VisitorCity>) jdbcTemplate
                .query(
                        "select * from `" + getTableName() + "` where vid = ?",
                        new Object[]{id},
                        new VisitorCityRowMapper());
    }
}
