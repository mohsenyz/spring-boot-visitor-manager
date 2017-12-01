package com.sina.sina.models;


import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_list")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    
    @Column(name = "vid", nullable = true)
    private Integer vid;
    
    @Column(name = "cm_name", nullable = true)
    private String cmName;

    @Column(name = "cm_phone", nullable = true)
    private String cmPhone;
    
    @Column(name = "dsid", nullable = true)
    private Integer dsid;
    
    @Column(name = "drid", nullable = true)
    private Integer drid;
    
    @Column(name = "ds_visited_name", nullable = true)
    private String dsVisitedName;
    
    @Column(name = "ds_visited_job", nullable = true)
    private int dsVisitedJob;
    
    @Column(name = "ds_visited_phone", nullable = true)
    private String dsVisitedPhone;
    
    @Column(name = "ds_idea", nullable = true)
    private String dsIdea;
    
    @Column(name = "ds_pop_cm", nullable = true)
    private String dsPopCm;
    
    @Column(name = "ds_rival", nullable = true)
    private String dsRival;
    
    @Column(name = "ds_index_dr", nullable = true)
    private String dsIndexDr;
    
    @Column(name = "dr_visit_place", nullable = true)
    private Integer drVisitPlace;
    
    @Column(name = "dr_visit_place_name", nullable = true)
    private String drVisitPlaceName;
    
    @Column(name = "dr_suggestion", nullable = true)
    private String drSuggestion;
    
    @Column(name = "created_at")
    private Timestamp createdAt;
    
    @Column(name = "created_at_ap")
    private int createdAtAp;
    
    @Column(name = "next_session")
    private Timestamp nextSession;
    
    @Column(name = "prev_session_id", nullable = true)
    private Integer prevSessionId;
    
    @Column(name = "content")
    private String content;
    
    @Column(name = "result")
    private String result;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "given_document")
    private String givenDocument;
    
    @Column(name = "needed_document")
    private String neededDocument;
    
    @Column(name = "forward_to_vid", nullable = true)
    private Integer forwardToVid;
    
    @Column(name = "from_id", nullable = true)
    private Integer fromId;
    
    @Column(name = "submited", nullable = true)
    private Boolean submited;
    
    @Column(name = "submit_time")
    private Timestamp submitTime;
    
    @Column(name = "viewed_at")
    private Timestamp viewedAt;
    
    @Column(name = "urgency")
    private int urgency;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getCmName() {
        return cmName;
    }

    public void setCmName(String cmid) {
        this.cmName = cmid;
    }

    public String getCmPhone() {
        return cmPhone;
    }

    public void setCmPhone(String cmid) {
        this.cmPhone = cmid;
    }

    public Integer getDsid() {
        return dsid;
    }

    public void setDsid(Integer dsid) {
        this.dsid = dsid;
    }

    public String getDsVisitedName() {
        return dsVisitedName;
    }

    public void setDsVisitedName(String dsVisitedName) {
        this.dsVisitedName = dsVisitedName;
    }

    public int getDsVisitedJob() {
        return dsVisitedJob;
    }

    public void setDsVisitedJob(int dsVisitedJob) {
        this.dsVisitedJob = dsVisitedJob;
    }

    public String getDsVisitedPhone() {
        return dsVisitedPhone;
    }

    public void setDsVisitedPhone(String dsVisitedPhone) {
        this.dsVisitedPhone = dsVisitedPhone;
    }

    public String getDsIdea() {
        return dsIdea;
    }

    public void setDsIdea(String dsIdea) {
        this.dsIdea = dsIdea;
    }

    public String getDsPopCm() {
        return dsPopCm;
    }

    public void setDsPopCm(String dsPopCm) {
        this.dsPopCm = dsPopCm;
    }

    public String getDsRival() {
        return dsRival;
    }

    public void setDsRival(String dsRival) {
        this.dsRival = dsRival;
    }

    public String getDsIndexDr() {
        return dsIndexDr;
    }

    public void setDsIndexDr(String dsIndexDr) {
        this.dsIndexDr = dsIndexDr;
    }

    public Integer getDrVisitPlace() {
        return drVisitPlace;
    }

    public void setDrVisitPlace(Integer drVisitPlace) {
        this.drVisitPlace = drVisitPlace;
    }

    public String getDrVisitPlaceName() {
        return drVisitPlaceName;
    }

    public void setDrVisitPlaceName(String drVisitPlaceName) {
        this.drVisitPlaceName = drVisitPlaceName;
    }

    public String getDrSuggestion() {
        return drSuggestion;
    }

    public void setDrSuggestion(String drSuggestion) {
        this.drSuggestion = drSuggestion;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getCreatedAtAp() {
        return createdAtAp;
    }

    public void setCreatedAtAp(int createdAtAp) {
        this.createdAtAp = createdAtAp;
    }

    public Timestamp getNextSession() {
        return nextSession;
    }

    public void setNextSession(Timestamp nextSession) {
        this.nextSession = nextSession;
    }

    public Integer getPrevSessionId() {
        return prevSessionId;
    }

    public void setPrevSessionId(Integer prevSessionId) {
        this.prevSessionId = prevSessionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public String getGivenDocument() {
        return givenDocument;
    }

    public void setGivenDocument(String givenDocument) {
        this.givenDocument = givenDocument;
    }

    public String getNeededDocument() {
        return neededDocument;
    }

    public void setNeededDocument(String neededDocument) {
        this.neededDocument = neededDocument;
    }

    public Integer getForwardToVid() {
        return forwardToVid;
    }

    public void setForwardToVid(Integer forwardToVid) {
        this.forwardToVid = forwardToVid;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Boolean getSubmited() {
        return submited;
    }

    public void setSubmited(Boolean submited) {
        this.submited = submited;
    }

    public Timestamp getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }

    public Timestamp getViewedAt() {
        return viewedAt;
    }

    public void setViewedAt(Timestamp viewedAt) {
        this.viewedAt = viewedAt;
    }

    public int getUrgency() {
        return urgency;
    }

    public void setUrgency(int urgency) {
        this.urgency = urgency;
    }

    public Integer getDrid() {
        return drid;
    }

    public void setDrid(Integer drid) {
        this.drid = drid;
    }
}