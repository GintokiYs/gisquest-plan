package com.gisquest.plan.service.vo.quata;

/**
 * @author ：yeyh
 * @date ：Created in 2020/9/27 16:01
 * @description：指标具体数据Vo
 * @modified By：
 */
public class QuataDataVo {
    //主键Id
    private String id;
    //指标名
    private String name;
    //度量值
    private String data;
    //度量单位
    private String unit;
    //年份
    private int year;
    //区域
    private String area;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
