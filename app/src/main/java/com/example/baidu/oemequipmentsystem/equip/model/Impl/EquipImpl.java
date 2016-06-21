package com.example.baidu.oemequipmentsystem.equip.model.Impl;

import android.util.Log;

import com.example.baidu.oemequipmentsystem.equip.model.entity.EquipModel;
import com.example.baidu.oemequipmentsystem.equip.model.entity.ManufacturerModel;
import com.example.baidu.oemequipmentsystem.equip.model.entity.ResponsibleModel;
import com.example.baidu.oemequipmentsystem.equip.model.entity.response.BaseArrayResponse;
import com.example.baidu.oemequipmentsystem.equip.model.entity.response.BaseResponseModel;
import com.example.baidu.oemequipmentsystem.equip.model.listener.EquipAction;
import com.example.baidu.oemequipmentsystem.equip.model.listener.OnDeleteEquipInfoListener;
import com.example.baidu.oemequipmentsystem.equip.model.listener.OnGetEquipInfoListener;
import com.example.baidu.oemequipmentsystem.equip.model.listener.OnManufacturerInfoListener;
import com.example.baidu.oemequipmentsystem.equip.model.listener.OnResponsibleInfoListener;
import com.example.baidu.oemequipmentsystem.equip.model.listener.OnSetEquipInfoListener;
import com.example.baidu.oemequipmentsystem.equip.model.listener.OnUpdateEquipInfoListener;

import api.MTTApiClient;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by zhangbinbin03 on 16/6/8.
 */
public class EquipImpl implements EquipAction {

    private static final String TAG = "EquipImpl";

    private static EquipImpl equipImpl;
    private static MTTApiClient apiClient;

    public static EquipImpl getInstance(){
        if(equipImpl == null){
            equipImpl = new EquipImpl();
            apiClient = MTTApiClient.getInstance();
        }
        return equipImpl;
    }

    @Override
    public void getEquipInfo(final OnGetEquipInfoListener onGetEquipInfoListener) {
        apiClient.getEquipInfo(new Callback<BaseArrayResponse<EquipModel>>() {

            @Override
            public void onResponse(Response<BaseArrayResponse<EquipModel>> response, Retrofit retrofit) {
                onGetEquipInfoListener.getEquipInfoSuccess(response.body().getObjList());
                Log.e(TAG, "onResponse: "+response.body().getObjList().size() );
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "onFailure: "+t.toString() );
            }
        });
    }

    @Override
    public void setEquipInfo(String imei, String model, String os, String cpu, String memory, String storage, final String resolution, String responsible, int source, String manufacturer, int state, final OnSetEquipInfoListener onSetEquipInfoListener) {
        apiClient.setEquipInfo(new Callback<BaseResponseModel>() {

            @Override
            public void onResponse(Response<BaseResponseModel> response, Retrofit retrofit) {
                onSetEquipInfoListener.setEquipInfoSuccess(response.body().getMsg());
            }

            @Override
            public void onFailure(Throwable t) {
                //Log.e(TAG, "onResponse: "+t.toString());
            }
        },imei, model, os, cpu, memory, storage, resolution, responsible, source, manufacturer, state);
    }

    @Override
    public void updateEquipInfo(String imei, final String responsible,String manufacturer,int state, final OnUpdateEquipInfoListener onUpdateEquipInfoListener) {
        apiClient.updateEquipInfo(new Callback<BaseResponseModel>() {

            @Override
            public void onResponse(Response<BaseResponseModel> response, Retrofit retrofit) {
                onUpdateEquipInfoListener.updateEquipInfoSuccess(response.body().getMsg());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        },imei,responsible,manufacturer,state);
    }

    @Override
    public void getResponsibleInfo(final OnResponsibleInfoListener onResponsibleInfoListener) {
        apiClient.getResponsibleInfo(new Callback<BaseArrayResponse<ResponsibleModel>>() {
            @Override
            public void onResponse(Response<BaseArrayResponse<ResponsibleModel>> response, Retrofit retrofit) {
                onResponsibleInfoListener.getResponsibleInfoSuccess(response.body().getObjList());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    @Override
    public void getManufacturerInfo(final OnManufacturerInfoListener onManufacturerInfoListener) {
        apiClient.getManufacturerInfo(new Callback<BaseArrayResponse<ManufacturerModel>>() {
            @Override
            public void onResponse(Response<BaseArrayResponse<ManufacturerModel>> response, Retrofit retrofit) {
                onManufacturerInfoListener.getManufacturerInfoSuccess(response.body().getObjList());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public void deleteEquipInfo(String imei,final OnDeleteEquipInfoListener onDeleteEquipInfoListener) {

        apiClient.deleteEquipInfo(new Callback<BaseResponseModel>() {
            @Override
            public void onResponse(Response<BaseResponseModel> response, Retrofit retrofit) {
                onDeleteEquipInfoListener.deleteEquipInfoSuccess(response.body().getMsg());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        },imei);
    }

}
