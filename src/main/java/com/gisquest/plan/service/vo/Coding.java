package com.gisquest.plan.service.vo;

import java.util.List;

/**
 * @author ：yeyh
 * @date ：Created in 2020/10/12 10:23
 * @description：
 * @modified By：
 */
public class Coding {
    private String id;

    private String name;

    // 1->省 2->市 3->县
    private String type;

    private List<Coding> codings;

    public Coding(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public List<Coding> getCodings() {
        return codings;
    }

    public void setCodings(List<Coding> codings) {
        this.codings = codings;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Coding{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", codings=" + codings +
                '}';
    }
}
