package com.gisquest.plan.service.vo;

/**
 * @author ：yeyh
 * @date ：Created in 2020/10/12 10:50
 * @description：
 * @modified By：
 */
public class CodingPo {
    private String name;

    private String xzqbh;

    private int codeType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXzqbh() {
        return xzqbh;
    }

    public void setXzqbh(String xzqbh) {
        this.xzqbh = xzqbh;
    }

    public int getCodeType() {
        return codeType;
    }

    public void setCodeType(int codeType) {
        this.codeType = codeType;
    }

    @Override
    public String toString() {
        return "CodingPo{" +
                "name='" + name + '\'' +
                ", xzqbh='" + xzqbh + '\'' +
                ", codeType=" + codeType +
                '}';
    }
}
