package com.gisquest.plan.service.model.UserTargetDesignClumnname;

public class UserTargetDesignClumnname {
    private String id;

    private String userid;

    private String clumnid;

    private String tarDesParid;

    public String getTarDesParid() {
        return tarDesParid;
    }

    public void setTarDesParid(String tarDesParid) {
        this.tarDesParid = tarDesParid;
    }

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

    public String getClumnid() {
        return clumnid;
    }

    public void setClumnid(String clumnid) {
        this.clumnid = clumnid == null ? null : clumnid.trim();
    }
}