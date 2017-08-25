/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao.rowmapper;

import com.sina.sina.models.Visitor;
import com.utils.dao.DaoHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author mphj
 */
public class VisitorRowMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Visitor visitor = new Visitor();
        visitor.setId(rs.getInt("id"));
        visitor.setFname(rs.getString("fname"));
        visitor.setLname(rs.getString("lname"));
        visitor.setUsername(rs.getString("username"));
        visitor.setPassword(rs.getString("password"));
        visitor.setType(rs.getInt("type"));
        visitor.setCreatedAt(DaoHelper.getTimestamp(rs, "created_at"));
        visitor.setEnabled(rs.getBoolean("enabled"));
        return visitor;
    }
}
