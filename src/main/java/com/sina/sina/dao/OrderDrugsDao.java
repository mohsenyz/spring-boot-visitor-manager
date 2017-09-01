/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.dao.rowmapper.OrderDrugsRowMapper;
import com.sina.sina.models.OrderDrugs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mphj
 */
@Repository
@Transactional
public class OrderDrugsDao extends AbstractDao{

    @Override
    public String getTableName() {
        return "order_drugs";
    }
    
    
    public int insert(OrderDrugs cm) {
        /*jdbcTemplate
                .update("insert into `" + getTableName() + "` values(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        cm.getId(),
                        cm.getOid(),
                        cm.getRid(),
                        cm.getDrugId(),
                        cm.getDrugName(),
                        cm.getVisitDesc(),
                        cm.getCount(),
                        cm.getType(),
                        cm.getReason());*/
        /*simpleJdbcInsert.setTableName(getTableName());
        Map<String, Object> map = new HashMap<>();
        map.put("id", cm.getId());
        map.put("oid", cm.getOid());
        map.put("rid", cm.getRid());
        map.put("drug_id", cm.getDrugId());
        map.put("drug_name", cm.getDrugName());
        map.put("visit_desc", cm.getVisitDesc());
        map.put("count", cm.getCount());
        map.put("type", cm.getType());
        map.put("reason", cm.getReason());
        return simpleJdbcInsert.executeAndReturnKey(map).intValue();*/
        return (Integer)getSession().save(cm);
    }
    
    public OrderDrugs findById(int id) {
        return (OrderDrugs) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "` where id = ?",
                        new Object[]{id},
                        new OrderDrugsRowMapper());
    }
    
}
