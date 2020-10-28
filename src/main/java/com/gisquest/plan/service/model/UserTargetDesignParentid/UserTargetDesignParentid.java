package com.gisquest.plan.service.model.UserTargetDesignParentid;

public class UserTargetDesignParentid {
    private String id;

    private String userid;

    private String targetDesignParentid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getTargetDesignParentid() {
        return targetDesignParentid;
    }

    public void setTargetDesignParentid(String targetDesignParentid) {
        this.targetDesignParentid = targetDesignParentid == null ? null : targetDesignParentid.trim();
    }
}