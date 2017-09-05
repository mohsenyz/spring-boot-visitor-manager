/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.dao.rowmapper.OrderRowMapper;
import com.sina.sina.models.Order;
import com.utils.list.ArrayUtils;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class OrderDao extends AbstractDao {

    @Override
    public String getTableName() {
        return "order_list";
    }

    public void insert(Order cm) {
        /*jdbcTemplate
                .update("insert into `" + getTableName() + "` values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        cm.getId(),
                        cm.getVid(),
                        cm.getCmid(),
                        cm.getDsid(),
                        cm.getDsVisitedName(),
                        cm.getDsVisitedJob(),
                        cm.getDsVisitedPhone(),
                        cm.getDsIdea(),
                        cm.getDsPopCm(),
                        cm.getDsRival(),
                        cm.getDsIndexDr(),
                        cm.getDrVisitPlace(),
                        cm.getDrVisitPlaceName(),
                        cm.getDrSuggestion(),
                        cm.getCreatedAt(),
                        cm.getCreatedAtAp(),
                        cm.getNextSession(),
                        cm.getPrevSessionId(),
                        cm.getContent(),
                        cm.getResult(),
                        cm.getDesc(),
                        cm.getGivenDocument(),
                        cm.getNeededDocument(),
                        cm.getForwardToVid(),
                        cm.getFromId(),
                        cm.getSubmited(),
                        cm.getSubmitTime(),
                        cm.getViewedAt(),
                        cm.getUrgency());*/
        getSession().save(cm);
    }

    public Order findById(int id) {
        return (Order) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "` where id = ?",
                        new Object[]{id},
                        new OrderRowMapper());
    }

    public List<Order> findByVid(int vid) {
        Criteria crit = getSession().createCriteria(Order.class);
        crit.add(Restrictions.eq("vid", vid));
        return crit.list();
    }

    public List<Order> findForwarded(int vid) {
        Criteria crit = getSession().createCriteria(Order.class);
        crit.add(Restrictions.eq("vid", vid));
        crit.add(Restrictions.isNotNull("forward_to_vid"));
        return crit.list();
    }

    public List<Order> findReceivedRequest(int vid) {
        Criteria crit = getSession().createCriteria(Order.class);
        crit.add(Restrictions.eq("forward_to_vid", vid));
        return crit.list();
    }

    public List<Order> findNextVisit(int vid) {
        Criteria crit = getSession().createCriteria(Order.class);
        crit.add(Restrictions.eq("vid", vid));
        crit.add(Restrictions.isNotNull("next_session"));
        return crit.list();
    }

    public List<Order> findByCm(int cm) {
        return jdbcTemplate.
                query(
                        "select `order`.* from`" + getTableName() + "`, `visitor_cm` where order.vid = visitor_cm.vid and visitor_cm.cid = ?",
                        new Object[]{cm},
                        new OrderRowMapper());
    }

    public List<Order> findByCmByVisitorByTime(int cm, String visitors, Timestamp from, Timestamp to) {
        return jdbcTemplate.
                query(
                        "select `order`.* from`" + getTableName() + "`, `visitor_cm` where order.vid = visitor_cm.vid and visitor_cm.cid = ? and vid in(?) and created_at between ? and ?",
                        new Object[]{cm, ArrayUtils.toIntArray(visitors.split(",")), from, to},
                        new OrderRowMapper());
    }

    public List<Order> receivedByCm(int cm) {
        return jdbcTemplate.
                query(
                        "select `order`.* from`" + getTableName() + "` where order.cmid = ?",
                        new Object[]{cm},
                        new OrderRowMapper());
    }

    public List<Order> findFinishedByCm(int cm) {
        return jdbcTemplate.
                query(
                        "select `order`.* from`" + getTableName() + "`, `visitor_cm` where order.vid = visitor_cm.vid and visitor_cm.cid = ? and submited = 1",
                        new Object[]{cm},
                        new OrderRowMapper());
    }

    public List<Order> findSeenByDs(int ds) {
        return jdbcTemplate.
                query(
                        "select `order`.* from`" + getTableName() + "` where dsid = ? and vid is null and viewed_at is not null",
                        new Object[]{ds},
                        new OrderRowMapper());
    }

    public List<Order> findFinishedByDs(int ds) {
        return jdbcTemplate.
                query(
                        "select `order`.* from`" + getTableName() + "` where dsid = ? and vid is null and submited  = 1",
                        new Object[]{ds},
                        new OrderRowMapper());
    }

    public List<Order> findAllDsOrder() {
        return jdbcTemplate.
                query(
                        "select `order`.* from`" + getTableName() + "` where vid is null",
                        new OrderRowMapper());
    }

    public List<Order> findAll() {
        return jdbcTemplate.
                query(
                        "select `order`.* from`" + getTableName() + "`",
                        new OrderRowMapper());
    }
}
