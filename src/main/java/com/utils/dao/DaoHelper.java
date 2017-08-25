/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;

/**
 *
 * @author mphj
 */
public class DaoHelper {
    static public Integer getInteger(ResultSet rs, String strColName) throws SQLException {
        int nValue = rs.getInt(strColName);
        return rs.wasNull() ? null : nValue;
    }
    
    static public Double getDouble(ResultSet rs, String strColName) throws SQLException {
        double nValue = rs.getDouble(strColName);
        return rs.wasNull() ? null : nValue;
    }
    
    static public Timestamp getTimestamp(ResultSet rs, String strColName) throws SQLException {
        Timestamp nValue = rs.getTimestamp(strColName, Calendar.getInstance(TimeZone.getDefault()));
        return rs.wasNull() ? null : nValue;
    }
    
    
    static public Boolean getBoolean(ResultSet rs, String strColName) throws SQLException {
        Boolean nValue = rs.getBoolean(strColName);
        return rs.wasNull() ? null : nValue;
    }
}
