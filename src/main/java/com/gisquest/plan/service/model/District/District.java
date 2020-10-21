package com.gisquest.plan.service.model.District;

public class District {
    private String name;

    private String xzqbh;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getXzqbh() {
        return xzqbh;
    }

    public void setXzqbh(String xzqbh) {
        this.xzqbh = xzqbh == null ? null : xzqbh.trim();
    }
}