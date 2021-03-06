/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.dao.rowmapper.CmCityRowMapper;
import com.sina.sina.models.CmCity;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mphj
 */
@Repository
@Transactional
public class CmCityDao extends AbstractDao{

    @Override
    public String getTableName() {
        return "cm_city";
    }
    
    public void insert(CmCity cm) {
        /*jdbcTemplate
                .update("insert into `" + getTableName() + "` values(?, ?, ?)",
                        cm.getId(),
                        cm.getCmid(),
                        cm.getCid());*/
        getSession().save(cm);
    }
    
    public CmCity findById(int id) {
        return (CmCity) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "` where id = ?",
                        new Object[]{id},
                        new CmCityRowMapper());
    }
    
    
    public List<CmCity> findByCmid(int id) {
        return (List<CmCity>) jdbcTemplate
                .query(
                        "select * from `" + getTableName() + "` where cmid = ?",
                        new Object[]{id},
                        new CmCityRowMapper());
    }
    
}
