package com.sina.sina.models;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cm")
public class Cm {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    
    @Column(name = "name", nullable = true)
    private String name;
    
    @Column(name = "a_fname", nullable = true)
    private String aFname;
    
    @Column(name = "a_lname", nullable = true)
    private String aLname;
    
    @Column(name = "a_birthday", nullable = true)
    private String aBirthday;
    
    @Column(name = "a_code", nullable = true)
    private String aCode;
    
    @Column(name = "fixed_phone", nullable = true)
    private String fixedPhone;
    
    @Column(name = "mobile", nullable = true)
    private String mobile;
    
    @Column(name = "username", nullable = true)
    private String username;
    
    @Column(name = "password", nullable = true)
    private String password;
    
    @Column(name = "created_at", nullable = true)
    private Timestamp createdAt;
    
    @Column(name = "enabled", nullable = true)
    private boolean enabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getaFname() {
        return aFname;
    }

    public void setaFname(String aFname) {
        this.aFname = aFname;
    }

    public String getaLname() {
        return aLname;
    }

    public void setaLname(String aLname) {
        this.aLname = aLname;
    }

    public String getaBirthday() {
        return aBirthday;
    }

    public void setaBirthday(String aBirthday) {
        this.aBirthday = aBirthday;
    }

    public String getaCode() {
        return aCode;
    }

    public void setaCode(String aCode) {
        this.aCode = aCode;
    }

    public String getFixedPhone() {
        return fixedPhone;
    }

    public void setFixedPhone(String fixedPhone) {
        this.fixedPhone = fixedPhone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}