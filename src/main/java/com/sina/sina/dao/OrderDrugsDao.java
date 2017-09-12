/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.dao.rowmapper.OrderDrugsRowMapper;
import com.sina.sina.models.Order;
import com.sina.sina.models.OrderDrugs;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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
        Criteria crit = getSession().createCriteria(OrderDrugs.class);
        crit.add(Restrictions.eq("id", id));
        return (OrderDrugs) crit.uniqueResult();
    }
    
    
    public List<OrderDrugs> findByOrder(int orderId) {
        Criteria crit = getSession().createCriteria(OrderDrugs.class);
        crit.add(Restrictions.eq("oid", orderId));
        return (List<OrderDrugs>) crit.list();
    }
    
}
