package com.gisquest.plan.service.vo.quata;

import java.io.Serializable;

/**
 * @author honght
 * @date 2020/9/28 14:43
 */
public class SimpleQuataResponse implements Serializable {
    private static final long serialVersionUID = -8008909050681870482L;
    private String trage;

    private String area;

    private int year;

    private String source;

    private String target; //指标量

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTrage() {
        return trage;
    }

    public void setTrage(String trage) {
        this.trage = trage;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public SimpleQuataResponse(String trage, String area, int year, String source, String target) {
        this.trage = trage;
        this.area = area;
        this.year = year;
        this.source = source;
        this.target = target;
    }
}
