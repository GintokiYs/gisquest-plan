package com.gisquest.plan.service.vo.quata;

import java.util.List;

/**
 * @author ：yeyh
 * @date ：Created in 2020/9/27 15:33
 * @description： 指标条件查询Vo
 * @modified By：
 */
public class QuataSearchVo {
    //指标id列表
    private List<String> quataIdList;
    //年份
    private List<Integer> year;
    //区域代码
    private List<String> areaCode;
    //数据来源
    private List<String> dataSource;
    //数据收集年份
    private List<Integer> collectYear;
    //省级指标
    private String provinceCode;
    //国家指标
    private String countryCode;

    public List<String> getQuataIdList() {
        return quataIdList;
    }

    public void setQuataIdList(List<String> quataIdList) {
        this.quataIdList = quataIdList;
    }

    public List<Integer> getYear() {
        return year;
    }

    public void setYear(List<Integer> year) {
        this.year = year;
    }

    public List<String> getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(List<String> areaCode) {
        this.areaCode = areaCode;
    }

    public List<String> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<String> dataSource) {
        this.dataSource = dataSource;
    }

    public List<Integer> getCollectYear() {
        return collectYear;
    }

    public void setCollectYear(List<Integer> collectYear) {
        this.collectYear = collectYear;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
