/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.dao.rowmapper.CityRowMapper;
import com.sina.sina.dao.rowmapper.CmRowMapper;
import com.sina.sina.models.City;
import com.sina.sina.models.Cm;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mphj
 */

@Repository
public class CmDao extends AbstractDao {
    
    public void insert(Cm cm) {
        /*jdbcTemplate
                .update("insert into `" + getTableName() + "` values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        cm.getId(),
                        cm.getName(),
                        cm.getaFname(),
                        cm.getaLname(),
                        cm.getaBirthday(),
                        cm.getaCode(),
                        cm.getFixedPhone(),
                        cm.getMobile(),
                        cm.getUsername(),
                        cm.getPassword(),
                        cm.getCreatedAt(),
                        cm.isEnabled());*/
        Map<String, Object> map = new HashMap<>();
        map.put("id", cm.getId());
        map.put("name", cm.getName());
        map.put("a_fname", cm.getaFname());
        map.put("a_lname", cm.getaLname());
        map.put("a_birthday", cm.getaBirthday());
        map.put("a_code", cm.getaCode());
        map.put("fixed_phone", cm.getFixedPhone());
        map.put("mobile", cm.getMobile());
        map.put("username", cm.getUsername());
        map.put("password", cm.getPassword());
        map.put("created_at", cm.getCreatedAt());
        map.put("enabled", cm.isEnabled());
        int key = simpleJdbcInsert.executeAndReturnKey(map).intValue();
        cm.setId(key);
    }

    public Cm findById(int id) {
        return (Cm) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "` where id = ?",
                        new Object[]{id},
                        new CmRowMapper());
    }
    
    public List<Cm> findAll(){
        return (List<Cm>) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "`",
                        new CmRowMapper());
    } 
    
    
    @Override
    public String getTableName() {
        return "cm";
    }

}
