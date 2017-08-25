/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao.rowmapper;

import com.sina.sina.models.VisitorCm;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author mphj
 */
public class VisitorCmRowMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        VisitorCm visitorCm = new VisitorCm();
        visitorCm.setId(rs.getInt("id"));
        visitorCm.setVid(rs.getInt("vid"));
        visitorCm.setCid(rs.getInt("cid"));
        return visitorCm;
    }
}
