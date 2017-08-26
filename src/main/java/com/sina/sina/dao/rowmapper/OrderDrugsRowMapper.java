/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao.rowmapper;

import com.sina.sina.models.OrderDrugs;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author mphj
 */
public class OrderDrugsRowMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderDrugs orderDrugs = new OrderDrugs();
        orderDrugs.setId(rs.getInt("id"));
        orderDrugs.setOid(rs.getInt("oid"));
        orderDrugs.setRid(rs.getInt("rid"));
        orderDrugs.setDrugId(rs.getInt("drug_id"));
        orderDrugs.setDrugName(rs.getString("drug_name"));
        orderDrugs.setVisitDesc(rs.getString("visit_desc"));
        orderDrugs.setCount(rs.getInt("count"));
        orderDrugs.setType(rs.getInt("type"));
        orderDrugs.setReason(rs.getString("reason"));
        return orderDrugs;
    }
}
