/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.dao.rowmapper.DsRowMapper;
import com.sina.sina.models.Ds;
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
public class DsDao extends AbstractDao{

    @Override
    public String getTableName() {
        return "ds";
    }

    
    public void insert(Ds cm) {
        /*jdbcTemplate
                .update("insert into `" + getTableName() + "` values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        cm.getId(),
                        cm.getName(),
                        cm.getClerkName(),
                        cm.getCompanyProductsAck(),
                        cm.getAddress(),
                        cm.getBestTime(),
                        cm.getType(),
                        cm.getCompanyNameAckReason(),
                        cm.getUsername(),
                        cm.getPassword(),
                        cm.getCity(),
                        cm.isVerified(),
                        cm.getVerifiedAt(),
                        cm.getCreatedAt(),
                        cm.isEnabled());*/
        /*GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection cnctn) throws SQLException {
                PSetter pr = PSetter.from(cnctn.prepareStatement("insert into `" + getTableName() + "` values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS));
                pr.setInt(1, cm.getId());
                pr.setString(2, cm.getName());
                pr.setString(3, cm.getPhone());
                pr.setString(4, cm.getClerkName());
                pr.setString(5, cm.getCompanyProductsAck());
                pr.setString(6, cm.getAddress());
                pr.setInt(7, cm.getBestTime());
                pr.setInt(8, cm.getType());
                pr.setInt(9, cm.getCompanyNameAckReason());
                pr.setString(10, cm.getUsername());
                pr.setString(11, cm.getPassword());
                pr.setInt(12, cm.getCity());
                pr.setBoolean(13, cm.isVerified());
                pr.setTimestamp(14, cm.getVerifiedAt(), Calendar.getInstance(TimeZone.getDefault()));
                pr.setTimestamp(15, cm.getCreatedAt(), Calendar.getInstance(TimeZone.getDefault()));
                pr.setBoolean(16, cm.isVerified());
                return pr.build();
            }
        }, generatedKeyHolder);
        cm.setId(generatedKeyHolder.getKey().intValue());*/
        getSession().save(cm);
    }

    public void update(Ds ds) {
        getSession().update(ds);
    }
    
    public Ds findById(int id) {
        Criteria crit = getSession().createCriteria(Ds.class);
        crit.add(Restrictions.eq("id", id));
        return (Ds)crit.uniqueResult();
    }
    
    
    public List<Ds> listAll(){
        Criteria crit = getSession().createCriteria(Ds.class);
        return crit.list();
    }
    
    public Ds findByUsername(String pass) {
        Criteria crit = getSession().createCriteria(Ds.class);
        crit.add(Restrictions.eq("username", pass));
        return (Ds)crit.uniqueResult();
    }

    public List<Ds> findUnAccepted() {
        Criteria crit = getSession().createCriteria(Ds.class);
        crit.add(Restrictions.eq("verified", false));
        return crit.list();
    }

    public List<Ds> findUnAcceptedByCity(int city) {
        Criteria crit = getSession().createCriteria(Ds.class);
        crit.add(Restrictions.eq("verified", false));
        crit.add(Restrictions.eq("city", city));
        return crit.list();
    }
    
    
    public List<Ds> findUnverifiedByCm(int cmId){
        return (List<Ds>) jdbcTemplate
                .query(
                        "select * from `" + getTableName() + "` where verified = 0 and city in(select cid from cm_city where cmid = ?)",
                        new Object[]{cmId},
                        new DsRowMapper());
    }
}
