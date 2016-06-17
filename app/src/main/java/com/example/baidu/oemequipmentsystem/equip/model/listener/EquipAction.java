package com.example.baidu.oemequipmentsystem.equip.model.listener;

/**
 * Created by zhangbinbin03 on 16/6/9.
 */
public interface EquipAction {

    /**
     * 查询所有设备的基本信息
     */
    void getEquipInfo(OnGetEquipInfoListener onGetEquipInfoListener);

    /**
     * 查询责任人列表
     */
    void getResponsibleInfo(OnResponsibleInfoListener onResponsibleInfoListener);

    /**
     * 查询厂商列表
     */
    void getManufacturerInfo(OnManufacturerInfoListener onManufacturerInfoListener);

    /**
     * 添加新设备的基本信息
     */
    void setEquipInfo(String imei, String model, String os, String cpu, String memory, String storage, String resolution, String responsible, int source, String manufacturer, int state, OnSetEquipInfoListener onSetEquipInfoListener);

    /**
     * 更改设备的信息(责任人/状态)
     */
    void updateEquipInfo(String imei,String responsible, String manufacturer,int state,OnUpdateEquipInfoListener onUpdateEquipInfoListener);
}
