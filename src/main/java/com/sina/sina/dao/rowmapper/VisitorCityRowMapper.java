/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao.rowmapper;

import com.sina.sina.models.VisitorCity;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author mphj
 */
public class VisitorCityRowMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        VisitorCity visitorCity = new VisitorCity();
        visitorCity.setId(rs.getInt("id"));
        visitorCity.setVid(rs.getInt("vid"));
        visitorCity.setCid(rs.getInt("cid"));
        return visitorCity;
    }
}
