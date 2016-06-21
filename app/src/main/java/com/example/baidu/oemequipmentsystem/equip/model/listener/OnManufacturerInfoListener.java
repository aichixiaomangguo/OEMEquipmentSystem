package com.example.baidu.oemequipmentsystem.equip.model.listener;

import com.example.baidu.oemequipmentsystem.equip.model.entity.ManufacturerModel;

import java.util.List;

/**
 * Created by zhangbinbin03 on 16/6/9.
 */
public interface OnManufacturerInfoListener {

    void getManufacturerInfoSuccess(List<ManufacturerModel> manufacturerModels);
}
