package com.example.baidu.oemequipmentsystem.equip.model.entity;

import java.io.Serializable;

/**
 * Created by zhangbinbin03 on 16/6/17.
 */
public class ResponsibleModel implements Serializable {

    public ResponsibleModel(){}

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    private String responsible;

    public ResponsibleModel(String responsible) {
        this.responsible = responsible;
    }
}
