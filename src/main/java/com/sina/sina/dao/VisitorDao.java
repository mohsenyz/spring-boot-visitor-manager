/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.dao.rowmapper.VisitorRowMapper;
import com.sina.sina.models.Visitor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        /*jdbcTemplate
                .update("insert into `" + getTableName() + "` values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        cm.getId(),
                        cm.getFname(),
                        cm.getLname(),
                        cm.getBirthday(),
                        cm.getCode(),
                        cm.getFixedPhone(),
                        cm.getMobile(),
                        cm.getAck(),
                        cm.getGrade(),
                        cm.getUsername(),
                        cm.getPassword(),
                        cm.getAddress(),
                        cm.getGradeExp(),
                        cm.getWorkExp(),
                        cm.getDesc(),
                        cm.getType(),
                        cm.getCreatedAt(),
                        cm.isEnabled());*/
        Map<String, Object> map = new HashMap<>();
        map.put("id", cm.getId());
        map.put("fname", cm.getFname());
        map.put("lname", cm.getLname());
        map.put("birthday", cm.getBirthday());
        map.put("code", cm.getCode());
        map.put("fixed_phone", cm.getFixedPhone());
        map.put("mobile", cm.getMobile());
        map.put("ack", cm.getAck());
        map.put("grade", cm.getGrade());
        map.put("username", cm.getUsername());
        map.put("password", cm.getPassword());
        map.put("address", cm.getAddress());
        map.put("grade_exp", cm.getGradeExp());
        map.put("work_exp", cm.getWorkExp());
        map.put("desc", cm.getDesc());
        map.put("type", cm.getType());
        map.put("created_at", cm.getCreatedAt());
        map.put("enabled", cm.isEnabled());
        int key = simpleJdbcInsert.executeAndReturnKey(map).intValue();
        cm.setId(key);
    }
    
    public Visitor findById(int id) {
        return (Visitor) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "` where id = ?",
                        new Object[]{id},
                        new VisitorRowMapper());
    }
    
    
    public List<Visitor> findAll() {
        return (List<Visitor>) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "`",
                        new VisitorRowMapper());
    }
    
    
    public List<Visitor> findByCm(int cm){
        return (List<Visitor>) jdbcTemplate
                .query(
                        "select `visitor`.* from `" + getTableName() + "`, `visitor_cm` where visitor.id = visitor_cm.vid and visitor_cm.cid = ?",
                        new Object[]{cm},
                        new VisitorRowMapper());
    }
    
    
}
