package com.sina.sina.models;


import java.sql.Timestamp;

public class Order {
    private int id;
    private int vid;
    private int cmid;
    private int dsid;
    private String dsVisitedName;
    private int dsVisitedJob;
    private String dsVisitedPhone;
    private String dsIdea;
    private String dsPopCm;
    private String dsRival;
    private String dsIndexDr;
    private int drVisitPlace;
    private String drVisitPlaceName;
    private String drSuggestion;
    private Timestamp createdAt;
    private int createdAtAp;
    private Timestamp nextSession;
    private Integer prevSessionId;
    private String content;
    private String result;
    private String desc;
    private String givenDocument;
    private String neededDocument;
    private Integer forwardToVid;
    private Integer fromId;
    private Boolean submited;
    private Timestamp submitTime;
    private Timestamp viewedAt;
    private int urgency;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public int getCmid() {
        return cmid;
    }

    public void setCmid(int cmid) {
        this.cmid = cmid;
    }

    public int getDsid() {
        return dsid;
    }

    public void setDsid(int dsid) {
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

    public int getDrVisitPlace() {
        return drVisitPlace;
    }

    public void setDrVisitPlace(int drVisitPlace) {
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
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
}