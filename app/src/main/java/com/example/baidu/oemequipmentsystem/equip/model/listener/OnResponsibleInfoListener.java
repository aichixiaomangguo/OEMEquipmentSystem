package com.example.baidu.oemequipmentsystem.equip.model.listener;

import com.example.baidu.oemequipmentsystem.equip.model.entity.ResponsibleModel;

import java.util.List;

/**
 * Created by zhangbinbin03 on 16/6/9.
 */
public interface OnResponsibleInfoListener {

    void getResponsibleInfoSuccess(List<ResponsibleModel> responsibleModels);
}
