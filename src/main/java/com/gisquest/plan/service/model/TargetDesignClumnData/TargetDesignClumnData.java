package com.gisquest.plan.service.model.TargetDesignClumnData;

public class TargetDesignClumnData {
    private String id;

    private String type;

    private String targetDesignId;

    private String extend1;

    private String extend2;

    private String extend3;

    private String sequence;

    private String clumnNameId;

    private String clumnName;

    private String clumnData;

    public String getClumnName() {
        return clumnName;
    }

    public void setClumnName(String clumnName) {
        this.clumnName = clumnName;
    }

    public String getClumnData() {
        return clumnData;
    }

    public void setClumnData(String clumnData) {
        this.clumnData = clumnData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTargetDesignId() {
        return targetDesignId;
    }

    public void setTargetDesignId(String targetDesignId) {
        this.targetDesignId = targetDesignId == null ? null : targetDesignId.trim();
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1 == null ? null : extend1.trim();
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2 == null ? null : extend2.trim();
    }

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3 == null ? null : extend3.trim();
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence == null ? null : sequence.trim();
    }

    public String getClumnNameId() {
        return clumnNameId;
    }

    public void setClumnNameId(String clumnNameId) {
        this.clumnNameId = clumnNameId == null ? null : clumnNameId.trim();
    }
}