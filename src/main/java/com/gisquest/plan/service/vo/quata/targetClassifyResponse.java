package com.gisquest.plan.service.vo.quata;

import java.io.Serializable;

/**
 * @author honght
 * @date 2020/9/28 13:09
 */
public class targetClassifyResponse implements Serializable {

    private static final long serialVersionUID = 8594624345022891820L;

    private String id;

    private String secondQuata;

    private String alias;

    private String businessParentid;

    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecondQuata() {
        return secondQuata;
    }

    public void setSecondQuata(String secondQuata) {
        this.secondQuata = secondQuata;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getBusinessParentid() {
        return businessParentid;
    }

    public void setBusinessParentid(String businessParentid) {
        this.businessParentid = businessParentid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
