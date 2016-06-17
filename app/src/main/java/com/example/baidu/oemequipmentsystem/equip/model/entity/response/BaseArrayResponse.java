package com.example.baidu.oemequipmentsystem.equip.model.entity.response;

import java.util.List;

/**
 * Created by zhangbinbin03 on 16/6/6.
 */
public class BaseArrayResponse<T> extends BaseResponse {
    private List<T> objList;

    public List<T> getObjList() {
        return objList;
    }

    public void setObjList(List<T> objList) {
        this.objList = objList;
    }
}
