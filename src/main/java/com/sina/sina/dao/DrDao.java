/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sina.sina.dao;

import com.sina.sina.dao.rowmapper.DrRowMapper;
import com.sina.sina.models.Dr;
import com.utils.dao.PSetter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mphj
 */
@Repository
public class DrDao extends AbstractDao{

    @Override
    public String getTableName() {
        return "dr";
    }
    
    public void insert(Dr cm) {
        /*jdbcTemplate
                .update("insert into `" + getTableName() + "` values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        cm.getId(),
                        cm.getName(),
                        cm.getExpert(),
                        cm.getBestVisitTime1(),
                        cm.getFixedPhone(),
                        cm.getMobile(),
                        cm.getMkdb(),
                        cm.getPlace(),
                        cm.getAddress(),
                        cm.getEmail(),
                        cm.getPezeshk(),
                        cm.getCompanyProductsAck(),
                        cm.getCompanyProductsPop(),
                        cm.getSuggestion());*/
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate
                .update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection cnctn) throws SQLException {
                PSetter pr = PSetter.from(cnctn.prepareStatement("insert into `" + getTableName() + "` values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS));
                pr.setInt(1, cm.getId());
                pr.setString(2, cm.getName());
                pr.setString(3, cm.getExpert());
                pr.setInt(4, cm.getBestVisitTime1());
                pr.setString(5, cm.getFixedPhone());
                pr.setString(6, cm.getMobile());
                pr.setString(7, cm.getMkdb());
                pr.setString(8, cm.getPlace());
                pr.setString(9, cm.getAddress());
                pr.setString(10, cm.getEmail());
                pr.setString(11, cm.getPezeshk());
                pr.setString(12, cm.getCompanyProductsAck());
                pr.setString(13, cm.getCompanyProductsPop());
                pr.setString(14, cm.getSuggestion());
                pr.setInt(15, cm.getCity());
                return pr.build();
            }
        }, generatedKeyHolder);
        cm.setId(generatedKeyHolder.getKey().intValue());
    }
    
    public Dr findById(int id) {
        return (Dr) jdbcTemplate
                .queryForObject(
                        "select * from `" + getTableName() + "` where id = ?",
                        new Object[]{id},
                        new DrRowMapper());
    }
    
}
