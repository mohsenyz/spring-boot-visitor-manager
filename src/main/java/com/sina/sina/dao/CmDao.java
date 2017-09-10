/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.models.Cm;
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
        /*Map<String, Object> map = new HashMap<>();
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
        cm.setId(key);*/
        getSession().save(cm);
    }

    public Cm findById(int id) {
        Criteria crit = getSession().createCriteria(Cm.class);
        crit.add(Restrictions.eq("id", id));
        return (Cm)crit.uniqueResult();
    }
    
    
    public Cm findByUsername(String username) {
        Criteria crit = getSession().createCriteria(Cm.class);
        crit.add(Restrictions.eq("username", username));
        return (Cm)crit.uniqueResult();
    }
    
    public List<Cm> listAll(){
        Criteria crit = getSession().createCriteria(Cm.class);
        return crit.list();
    }
    
    
    public void update(Cm cm){
        getSession().update(cm);
    }
    
    
    @Override
    public String getTableName() {
        return "cm";
    }

}
