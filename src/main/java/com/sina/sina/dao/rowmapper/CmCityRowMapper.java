/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao.rowmapper;

import com.sina.sina.models.CmCity;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author mphj
 */
public class CmCityRowMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        CmCity cmCity = new CmCity();
        cmCity.setId(rs.getInt("id"));
        cmCity.setCmid(rs.getInt("cmid"));
        cmCity.setCid(rs.getInt("cid"));
        return cmCity;
    }
}
