package com.gisquest.plan.service.vo.quata;

import java.util.List;
import java.util.Map;

/**
 * @author ：yeyh
 * @date ：Created in 2020/9/30 11:19
 * @description：
 * @modified By：
 */
public class QuataByTopicVo {
    private String id;
    private String name;
    private List<QuataVo> children;

    public QuataByTopicVo(String id, String name, List<QuataVo> children) {
        this.id = id;
        this.name = name;
        this.children = children;
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

    public List<QuataVo> getChildren() {
        return children;
    }

    public void setChildren(List<QuataVo> children) {
        this.children = children;
    }
}
