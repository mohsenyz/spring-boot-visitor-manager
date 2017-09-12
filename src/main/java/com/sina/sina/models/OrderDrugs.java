package com.sina.sina.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_drugs")
public class OrderDrugs {
    
    public static final int
            RESULT_DRUGS = 1,
            CONTENT_DRUGS = 2,
            NOSKHE = 3,
            NOT_NOSKHE = 4,
            KNOWN_DRUGS = 5,
            EXISTS_DRUGS = 6,
            SAME_DRUGS = 7,
            DS_ORDER_DRUGS = 8;
    
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    
    @Column(name = "oid", nullable = true)
    private Integer oid;
    
    @Column(name = "rid", nullable = true)
    private Integer rid;
    
    @Column(name = "drug_id")
    private int drugId;
    
    @Column(name = "drug_name")
    private String drugName;
    
    @Column(name = "visit_desc")
    private String visitDesc;
    
    @Column(name = "count")
    private int count;
    
    @Column(name = "type")
    private int type;
    
    @Column(name = "reason")
    private String reason;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getVisitDesc() {
        return visitDesc;
    }

    public void setVisitDesc(String visitDesc) {
        this.visitDesc = visitDesc;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}