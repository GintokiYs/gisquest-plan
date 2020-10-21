package com.gisquest.plan.service.vo.quata;

import java.io.Serializable;
import java.util.List;

/**
 * @author honght
 * @date 2020/9/28 13:54
 */
public class QuataSearchResponse implements Serializable {
    private static final long serialVersionUID = -4766596043438459940L;

    private String quataId;  //指标id

    private List<String> yearList;

    private List<String> areaList;

    private String tableName;
    //年份
    private int year;
    //区域
    private String area;
    //省级指标
    private String provinceCode;
    //国家指标
    private String countryCode;

    private String median; //中位线

    private String ave; // 平均值

    private String yearString;

    private String areaString;

    private String equally; //是否同源true or false

    private String areaSoure; //指标来源 0为国家，1为省，2为市，3为区县

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

    public String getQuataId() {
        return quataId;
    }

    public void setQuataId(String quataId) {
        this.quataId = quataId;
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

    public String getYearString() {
        return yearString;
    }

    public void setYearString(String yearString) {
        this.yearString = yearString;
    }

    public String getAreaString() {
        return areaString;
    }

    public void setAreaString(String areaString) {
        this.areaString = areaString;
    }

    public String getEqually() {
        return equally;
    }

    public void setEqually(String equally) {
        this.equally = equally;
    }

    public String getAreaSoure() {
        return areaSoure;
    }

    public void setAreaSoure(String areaSoure) {
        this.areaSoure = areaSoure;
    }
}
