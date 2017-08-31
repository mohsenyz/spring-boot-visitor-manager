/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao.rowmapper;

import com.sina.sina.models.Dr;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author mphj
 */
public class DrRowMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Dr dr = new Dr();
        dr.setId(rs.getInt("id"));
        dr.setName(rs.getString("name"));
        dr.setExpert(rs.getString("expert"));
        dr.setBestVisitTime1(rs.getInt("best_visit_time1"));
        dr.setFixedPhone(rs.getString("fixed_phone"));
        dr.setMobile(rs.getString("mobile"));
        dr.setMkdb(rs.getString("mkdb"));
        dr.setAddress(rs.getString("address"));
        dr.setEmail(rs.getString("email"));
        dr.setPezeshk(rs.getString("pezeshk"));
        dr.setCompanyProductsAck(rs.getString("company_products_ack"));
        dr.setCompanyProductsPop(rs.getString("company_products_pop"));
        dr.setSuggestion(rs.getString("suggestion"));
        dr.setPlace(rs.getString("place"));
        dr.setId(rs.getInt("city"));
        return dr;
    }
}
