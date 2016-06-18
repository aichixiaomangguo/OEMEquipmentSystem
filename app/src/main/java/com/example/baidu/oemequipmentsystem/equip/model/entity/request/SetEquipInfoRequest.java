package com.example.baidu.oemequipmentsystem.equip.model.entity.request;

/**
 * Created by mango on 16/6/9.
 */
public class SetEquipInfoRequest extends BaseRequest {
    private String imei;
    private String model;//设备型号
    private String os;//系统版本
    private String cpu;
    private String memory;//运行内存
    private String storage;//存储空间
    private String resolution;//分辨率
    private String responsible;//责任人
    private int source;//1厂商 0自采
    private String manufacturer;//厂商名 or ""
    private int state;// 0未还 1已还

    public SetEquipInfoRequest(String imei, String model, String os, String cpu, String memory, String storage, String resolution, String responsible, int source, String manufacturer, int state) {
        this.imei = imei;
        this.model = model;
        this.os = os;
        this.cpu = cpu;
        this.memory = memory;
        this.storage = storage;
        this.resolution = resolution;
        this.responsible = responsible;
        this.source = source;
        this.manufacturer = manufacturer;
        this.state = state;
    }


    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
