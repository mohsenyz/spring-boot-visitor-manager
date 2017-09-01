/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao.rowmapper;

import com.sina.sina.models.Docs;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author mphj
 */
public class DocsRowMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Docs docs = new Docs();
        docs.setId(rs.getInt("id"));
        docs.setName(rs.getString("name"));
        docs.setDesc(rs.getString("description"));
        docs.setType(rs.getInt("type"));
        docs.setOid(rs.getInt("oid"));
        docs.setVid(rs.getInt("vid"));
        docs.setCid(rs.getInt("cid"));
        docs.setDid(rs.getInt("did"));
        docs.setRid(rs.getInt("rid"));
        return docs;
    }
}
