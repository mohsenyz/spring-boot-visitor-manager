/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;

/**
 *
 * @author mphj
 */
public class PSetter{
    PreparedStatement preparedStatement;
    
    public PSetter(PreparedStatement ps){
        this.preparedStatement = ps;
    }
    
    public static PSetter from(PreparedStatement ps){
        return new PSetter(ps);
    }
    
    
    public void setInt(int index, Integer integer) throws SQLException{
        if (integer == null){
            preparedStatement.setNull(index, Types.INTEGER);
        } else {
            preparedStatement.setInt(index, integer);
        }
    }
    
    
    public void setString(int index, String integer) throws SQLException{
        preparedStatement.setString(index, integer);
    }
    
    public void setTimestamp(int index, Timestamp timestamp, Calendar calendar) throws SQLException{
        preparedStatement.setTimestamp(index, timestamp, calendar);
    }
    
    
    public void setBoolean(int index, Boolean bool) throws SQLException{
        if (bool == null){
            preparedStatement.setNull(index, Types.BOOLEAN);
        } else {
            preparedStatement.setBoolean(index, bool);
        }
    }
    
    public PreparedStatement build(){
        return preparedStatement;
    }
    
    
    
}
