package com.sina.sina.models;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ds")
public class Ds {
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "clerk_name")
    private String clerkName;
    
    @Column(name = "company_products_ack")
    private String companyProductsAck;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "best_time", nullable = true)
    private Integer bestTime;
    
    @Column(name = "type", nullable = true)
    private Integer type;
    
    @Column(name = "company_name_ack_reason", nullable = true)
    private Integer companyNameAckReason;
    
    @Column(name = "username", nullable = true)
    private String username;
    
    @Column(name = "password", nullable = true)
    private String password;
    
    @Column(name = "city", nullable = true)
    private Integer city;
    
    @Column(name = "verified")
    private boolean verified;
    
    @Column(name = "verified_at", nullable = true)
    private Timestamp verifiedAt;
    
    @Column(name = "created_at")
    private Timestamp createdAt;
    
    @Column(name = "enabled")
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public String getCompanyProductsAck() {
        return companyProductsAck;
    }

    public void setCompanyProductsAck(String companyProductsAck) {
        this.companyProductsAck = companyProductsAck;
    }

    public int getBestTime() {
        return bestTime;
    }

    public void setBestTime(int bestTime) {
        this.bestTime = bestTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCompanyNameAckReason() {
        return companyNameAckReason;
    }

    public void setCompanyNameAckReason(int companyNameAckReason) {
        this.companyNameAckReason = companyNameAckReason;
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

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Timestamp getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(Timestamp verifiedAt) {
        this.verifiedAt = verifiedAt;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}