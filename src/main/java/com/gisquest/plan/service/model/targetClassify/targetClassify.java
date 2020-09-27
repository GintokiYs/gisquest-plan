package com.gisquest.plan.service.model.targetClassify;

public class targetClassify {
    private String id;

    private String type;

    private Integer alias;

    private String businessParentid;

    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getAlias() {
        return alias;
    }

    public void setAlias(Integer alias) {
        this.alias = alias;
    }

    public String getBusinessParentid() {
        return businessParentid;
    }

    public void setBusinessParentid(String businessParentid) {
        this.businessParentid = businessParentid == null ? null : businessParentid.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}