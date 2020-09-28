package com.gisquest.plan.service.vo.quata;

import java.io.Serializable;
import java.util.List;

/**
 * @author honght
 * @date 2020/9/28 10:15
 */
public class TargetResponse implements Serializable {
    private static final long serialVersionUID = -671997847870564670L;

    private String id;

    private String firstQuata;

    private List<targetClassifyResponse> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstQuata() {
        return firstQuata;
    }

    public void setFirstQuata(String firstQuata) {
        this.firstQuata = firstQuata;
    }

    public List<targetClassifyResponse> getList() {
        return list;
    }

    public void setList(List<targetClassifyResponse> list) {
        this.list = list;
    }
}
