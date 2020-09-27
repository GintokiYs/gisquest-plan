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
    //二级指标
    private String secondQuata;

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

    public String getSecondQuata() {
        return secondQuata;
    }

    public void setSecondQuata(String secondQuata) {
        this.secondQuata = secondQuata;
    }
}
