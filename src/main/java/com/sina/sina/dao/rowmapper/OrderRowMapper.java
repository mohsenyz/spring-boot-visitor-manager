/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao.rowmapper;

import com.sina.sina.models.Order;
import com.utils.dao.DaoHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author mphj
 */
public class OrderRowMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setVid(rs.getInt("vid"));
        order.setCmid(rs.getInt("cmid"));
        order.setDsid(DaoHelper.getInteger(rs, "dsid"));
        order.setDrid(DaoHelper.getInteger(rs, "drid"));
        order.setDsVisitedName(rs.getString("ds_visited_name"));
        order.setDsVisitedJob(rs.getInt("ds_visited_job"));
        order.setDsVisitedPhone(rs.getString("ds_visited_phone"));
        order.setDsIdea(rs.getString("ds_idea"));
        order.setDsPopCm(rs.getString("ds_pop_cm"));
        order.setDsRival(rs.getString("ds_rival"));
        order.setDsIndexDr(rs.getString("ds_index_dr"));
        order.setDrVisitPlace(rs.getInt("dr_visit_place"));
        order.setDrVisitPlaceName(rs.getString("dr_visit_place_name"));
        order.setDrSuggestion(rs.getString("dr_suggestion"));
        order.setCreatedAt(DaoHelper.getTimestamp(rs, "created_at"));
        order.setCreatedAtAp(rs.getInt("created_at_ap"));
        order.setNextSession(DaoHelper.getTimestamp(rs, "next_session"));
        order.setPrevSessionId(DaoHelper.getInteger(rs, "prev_session_id"));
        order.setContent(rs.getString("content"));
        order.setResult(rs.getString("result"));
        order.setDesc(rs.getString("description"));
        order.setGivenDocument(rs.getString("given_document"));
        order.setNeededDocument(rs.getString("needed_document"));
        order.setForwardToVid(DaoHelper.getInteger(rs, "forward_to_vid"));
        order.setFromId(DaoHelper.getInteger(rs, "from_id"));
        order.setSubmited(DaoHelper.getBoolean(rs, "submited"));
        order.setSubmitTime(DaoHelper.getTimestamp(rs, "submit_time"));
        order.setViewedAt(DaoHelper.getTimestamp(rs, "viewed_at"));
        order.setUrgency(rs.getInt("urgency"));
        return order;
    }
}
