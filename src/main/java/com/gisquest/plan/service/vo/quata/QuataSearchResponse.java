package com.gisquest.plan.service.vo.quata;

import java.io.Serializable;
import java.util.List;

/**
 * @author honght
 * @date 2020/9/28 13:54
 */
public class QuataSearchResponse implements Serializable {
    private static final long serialVersionUID = -4766596043438459940L;

    private List<String> quataIdList;  //指标id

    private List<String> yearList;

    private List<String> areaList;

    private List<String> collectList;

    private List<String> dataSourcetList;

    private String tableName;
    //年份
    private int year;
    //区域
    private String area;
    //数据来源
    private String dataSource;
    //数据收集年份
    private int collectYear;
    //省级指标
    private String provinceCode;
    //国家指标
    private String countryCode;

    private String median; //中位线

    private String ave; // 平均值

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public int getCollectYear() {
        return collectYear;
    }

    public void setCollectYear(int collectYear) {
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

    public String getMedian() {
        return median;
    }

    public void setMedian(String median) {
        this.median = median;
    }

    public String getAve() {
        return ave;
    }

    public void setAve(String ave) {
        this.ave = ave;
    }

    public List<String> getQuataIdList() {
        return quataIdList;
    }

    public void setQuataIdList(List<String> quataIdList) {
        this.quataIdList = quataIdList;
    }

    public List<String> getYearList() {
        return yearList;
    }

    public void setYearList(List<String> yearList) {
        this.yearList = yearList;
    }

    public List<String> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<String> areaList) {
        this.areaList = areaList;
    }

    public List<String> getCollectList() {
        return collectList;
    }

    public void setCollectList(List<String> collectList) {
        this.collectList = collectList;
    }

    public List<String> getDataSourcetList() {
        return dataSourcetList;
    }

    public void setDataSourcetList(List<String> dataSourcetList) {
        this.dataSourcetList = dataSourcetList;
    }
}
