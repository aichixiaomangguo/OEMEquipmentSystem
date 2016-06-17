package com.example.baidu.oemequipmentsystem.equip.model.entity.request;

/**
 * Created by mango on 16/6/9.
 */
public class UpdateEquipInfoRequest extends BaseRequest {

    private String imei;
    private String responsible;//责任人
    private String manufacturer;
    private int state;// 0未还 1已还

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public UpdateEquipInfoRequest(String imei, String responsible, String manufacturer, int state) {
        this.imei = imei;
        this.responsible = responsible;
        this.manufacturer = manufacturer;
        this.state = state;
    }
}
