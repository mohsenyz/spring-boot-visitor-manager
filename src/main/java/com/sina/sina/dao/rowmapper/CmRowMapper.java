/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao.rowmapper;

import com.sina.sina.models.Cm;
import com.utils.dao.DaoHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author mphj
 */
public class CmRowMapper implements RowMapper<Object>{

    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cm cm = new Cm();
        cm.setId(rs.getInt("id"));
        cm.setName(rs.getString("name"));
        cm.setaFname(rs.getString("a_fname"));
        cm.setaLname(rs.getString("a_lname"));
        cm.setaBirthday(rs.getString("a_birthday"));
        cm.setaCode(rs.getString("a_code"));
        cm.setFixedPhone(rs.getString("fixed_phone"));
        cm.setMobile(rs.getString("mobile"));
        cm.setUsername(rs.getString("username"));
        cm.setPassword(rs.getString("password"));
        cm.setCreatedAt(DaoHelper.getTimestamp(rs, "created_at"));
        cm.setEnabled(rs.getBoolean("enabled"));
        return cm;
    }
}
