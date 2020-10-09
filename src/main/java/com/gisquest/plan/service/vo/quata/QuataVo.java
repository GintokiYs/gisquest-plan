package com.gisquest.plan.service.vo.quata;

/**
 * @author ：yeyh
 * @date ：Created in 2020/9/27 16:03
 * @description：指标Vo
 * @modified By：
 */
public class QuataVo {
    //主键Id
    private String id;
    //主题
    private String topic;
    //一级指标
    private String firstQuata;

    private String firstQuataId;
    //二级指标
    private String name;
    //表名
    private String tableName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getFirstQuata() {
        return firstQuata;
    }

    public void setFirstQuata(String firstQuata) {
        this.firstQuata = firstQuata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFirstQuataId() {
        return firstQuataId;
    }

    public void setFirstQuataId(String firstQuataId) {
        this.firstQuataId = firstQuataId;
    }
}
