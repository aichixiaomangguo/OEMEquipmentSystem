package com.example.baidu.oemequipmentsystem.equip.model.entity;

import java.io.Serializable;

/**
 * Created by zhangbinbin03 on 16/6/17.
 */
public class ManufacturerModel implements Serializable {
    public ManufacturerModel() {
    }

    private String manufacturer;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public ManufacturerModel(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
