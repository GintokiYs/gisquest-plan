package com.gisquest.plan.service.model.businessClassify;

public class businessClassify {
    private String id;

    private String type;

    private String alias;

    private String resourceParentid;

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getResourceParentid() {
        return resourceParentid;
    }

    public void setResourceParentid(String resourceParentid) {
        this.resourceParentid = resourceParentid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}
