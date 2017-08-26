/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.dao.rowmapper.OrderDrugsRowMapper;
import com.sina.sina.models.OrderDrugs;

/**
 *
 * @author mphj
 */
public class OrderDrugsDao extends AbstractDao{

    @Override
    public String getTableName() {
        return "order_drugs";
    }
    
    
    public void insert(OrderDrugs cm) {
        jdbcTemplate
                .update("insert into `" + getTableName() + "` values(?, ?, ?, ?, ?, ?, ?, ?)",
                        cm.getId(),
                        cm.getOid(),
                        cm.getRid(),
                        cm.getDrugId(),
                        cm.getDrugName(),
                        cm.getVisitDesc(),
                        cm.getCount(),
                        cm.getType());
    }
    
    public OrderDrugs findById(int id) {
        return (OrderDrugs) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "` where id = ?",
                        new Object[]{id},
                        new OrderDrugsRowMapper());
    }
    
}
