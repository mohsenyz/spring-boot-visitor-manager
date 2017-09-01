/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao.rowmapper;

import com.sina.sina.models.R;
import com.utils.dao.DaoHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author mphj
 */
public class RRowMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        R r = new R();
        r.setId(rs.getInt("id"));
        r.setCmid(rs.getInt("cmid"));
        r.setCreatedAt(DaoHelper.getTimestamp(rs, "created_at"));
        r.setStatus(rs.getInt("status"));
        r.setDesc(rs.getString("description"));
        r.setUrgency(rs.getInt("urgency"));
        return r;
    }
}
