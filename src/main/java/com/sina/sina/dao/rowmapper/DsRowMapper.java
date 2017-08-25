/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao.rowmapper;

import com.sina.sina.models.Ds;
import com.utils.dao.DaoHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author mphj
 */
public class DsRowMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ds ds = new Ds();
        ds.setId(rs.getInt("id"));
        ds.setName(rs.getString("name"));
        ds.setPhone(rs.getString("phone"));
        ds.setClerkName(rs.getString("clerk_name"));
        ds.setCompanyProductsAck(rs.getString("company_products_ack"));
        ds.setBestTime(rs.getInt("best_time"));
        ds.setType(rs.getInt("type"));
        ds.setCompanyNameAckReason(rs.getInt("company_name_ack_reason"));
        ds.setUsername(rs.getString("username"));
        ds.setPassword(rs.getString("password"));
        ds.setCity(rs.getInt("city"));
        ds.setVerified(rs.getBoolean("verified"));
        ds.setVerifiedAt(DaoHelper.getTimestamp(rs, "verified_at"));
        ds.setCreatedAt(DaoHelper.getTimestamp(rs, "created_at"));
        ds.setEnabled(rs.getBoolean("enabled"));
        return ds;
    }
}
