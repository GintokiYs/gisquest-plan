package com.gisquest.plan.service.vo.quata;

/**
 * @Title: TagResponse
 * @ProjectName plan
 * @Description: TODO
 * @Date 2020/10/23 14:34
 * @Version 2.0
 */
public class TagResponse {
    private String id;

    private String targetId;

    private String indexId;

    private String areaType;

    private Integer year;

    private Double target;

    private Integer sourceYear;

    private Integer alias;

    private String areaCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId == null ? null : targetId.trim();
    }

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId == null ? null : indexId.trim();
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType == null ? null : areaType.trim();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


    public Integer getSourceYear() {
        return sourceYear;
    }

    public void setSourceYear(Integer sourceYear) {
        this.sourceYear = sourceYear;
    }

    public Integer getAlias() {
        return alias;
    }

    public void setAlias(Integer alias) {
        this.alias = alias;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public Double getTarget() {
        return target;
    }

    public void setTarget(Double target) {
        this.target = target;
    }
}
