package com.sina.sina.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "dr")
public class Dr {
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "expert")
    private String expert;
    
    @Column(name = "best_visit_time1")
    private int bestVisitTime1;
    
    @Column(name = "fixed_phone")
    private String fixedPhone;
    
    @Column(name = "mobile")
    private String mobile;
    
    @Column(name = "mkdb")
    private String mkdb;
    
    @Column(name = "place")
    private String place;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "pezeshk")
    private String pezeshk;
    
    @Column(name = "company_products_ack", nullable = true)
    private String companyProductsAck;
    
    @Column(name = "company_products_pop", nullable = true)
    private String companyProductsPop;
    
    @Column(name = "suggestion", nullable = true)
    private String suggestion;
    
    @Column(name = "city")
    private int city;

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

    public String getExpert() {
        return expert;
    }

    public void setExpert(String expert) {
        this.expert = expert;
    }

    public int getBestVisitTime1() {
        return bestVisitTime1;
    }

    public void setBestVisitTime1(int bestVisitTime1) {
        this.bestVisitTime1 = bestVisitTime1;
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

    public String getMkdb() {
        return mkdb;
    }

    public void setMkdb(String mkdb) {
        this.mkdb = mkdb;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPezeshk() {
        return pezeshk;
    }

    public void setPezeshk(String pezeshk) {
        this.pezeshk = pezeshk;
    }

    public String getCompanyProductsAck() {
        return companyProductsAck;
    }

    public void setCompanyProductsAck(String companyProductsAck) {
        this.companyProductsAck = companyProductsAck;
    }

    public String getCompanyProductsPop() {
        return companyProductsPop;
    }

    public void setCompanyProductsPop(String companyProductsPop) {
        this.companyProductsPop = companyProductsPop;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * @return the city
     */
    public int getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(int city) {
        this.city = city;
    }
}