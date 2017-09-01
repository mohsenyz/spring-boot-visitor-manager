/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.dao.rowmapper.VisitorRowMapper;
import com.sina.sina.models.Order;
import com.sina.sina.models.Visitor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mphj
 */
@Repository
@Transactional
public class VisitorDao extends AbstractDao {

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
        /*GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection cnctn) throws SQLException {
                PreparedStatement ps = cnctn.prepareStatement("insert into `" + getTableName() + "` values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, cm.getId());
                ps.setString(2, cm.getFname());
                ps.setString(3, cm.getLname());
                ps.setString(4, cm.getBirthday());
                ps.setString(5, cm.getCode());
                ps.setString(6, cm.getFixedPhone());
                ps.setString(7, cm.getMobile());
                ps.setString(8, cm.getAck());
                ps.setString(9, cm.getGrade());
                ps.setString(10, cm.getUsername());
                ps.setString(11, cm.getPassword());
                ps.setString(12, cm.getAddress());
                ps.setString(13, cm.getGradeExp());
                ps.setString(14, cm.getWorkExp());
                ps.setString(15, cm.getDesc());
                ps.setInt(16, cm.getType());
                ps.setTimestamp(17, cm.getCreatedAt(), Calendar.getInstance(TimeZone.getDefault()));
                ps.setBoolean(18, cm.isEnabled());
                return ps;
            }
        }, holder);*/

        //Map<String, Object> map = new HashMap<>();

        /*MapSqlParameterSource map = new MapSqlParameterSource();
        
        System.out.println("\n\n\n\n\n" + simpleJdbcInsert.getTableName());
        map.addValue("id", cm.getId());
        map.addValue("fname", cm.getFname());
        map.addValue("lname", cm.getLname());
        map.addValue("birthday", cm.getBirthday());
        map.addValue("code", cm.getCode());
        map.addValue("fixed_phone", cm.getFixedPhone());
        map.addValue("mobile", cm.getMobile());
        map.addValue("ack", cm.getAck());
        map.addValue("grade", cm.getGrade());
        map.addValue("username", cm.getUsername());
        map.addValue("password", cm.getPassword());
        map.addValue("address", cm.getAddress());
        map.addValue("grade_exp", cm.getGradeExp());
        map.addValue("work_exp", cm.getWorkExp());
        map.addValue("desc", cm.getDesc());
        map.addValue("type", cm.getType());
        map.addValue("created_at", cm.getCreatedAt());
        map.addValue("enabled", cm.isEnabled());
        int key = simpleJdbcInsert.executeAndReturnKey(map).intValue();
        cm.setId(key);*/
        //cm.setId(holder.getKey().intValue());
        getSession().save(cm);
    }

    public Visitor findById(int id) {
        Criteria crit = getSession().createCriteria(Visitor.class);
        crit.add(Restrictions.eq("id", id));
        return (Visitor)crit.uniqueResult();
    }

    public List<Visitor> listAll() {
        Criteria crit = getSession().createCriteria(Visitor.class);
        return crit.list();
    }

    public List<Visitor> findByCm(int cm) {
        return (List<Visitor>) jdbcTemplate
                .query(
                        "select `visitor`.* from `" + getTableName() + "`, `visitor_cm` where visitor.id = visitor_cm.vid and visitor_cm.cid = ?",
                        new Object[]{cm},
                        new VisitorRowMapper());
    }

}
