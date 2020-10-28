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

    private String columnData;

    private String columnId;

    private String parentid;

    private String type;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public String getColumnData() {
        return columnData;
    }

    public void setColumnData(String columnData) {
        this.columnData = columnData;
    }

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
