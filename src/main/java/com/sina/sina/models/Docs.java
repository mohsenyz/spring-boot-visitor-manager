package com.sina.sina.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "docs")
public class Docs {
    
    public static final int
            ORDER_DOCS = 1,
            VISITOR_DOC1 = 2,
            VISITOR_DOC2 = 3,
            VISITOR_DOCS = 4,
            CM_DOC = 5,
            CM_DOC1 = 6;
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "desc")
    private String desc;
    
    @Column(name = "type")
    private int type;
    
    @Column(name = "oid", nullable = true)
    private Integer oid;
    
    @Column(name = "vid", nullable = true)
    private Integer vid;
    
    @Column(name = "cid", nullable = true)
    private Integer cid;
    
    @Column(name = "did", nullable = true)
    private Integer did;
    
    @Column(name = "rid", nullable = true)
    private Integer rid;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}