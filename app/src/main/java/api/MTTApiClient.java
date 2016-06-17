package api;

import com.example.baidu.oemequipmentsystem.equip.model.entity.EquipModel;
import com.example.baidu.oemequipmentsystem.equip.model.entity.ManufacturerModel;
import com.example.baidu.oemequipmentsystem.equip.model.entity.ResponsibleModel;
import com.example.baidu.oemequipmentsystem.equip.model.entity.request.BaseRequest;
import com.example.baidu.oemequipmentsystem.equip.model.entity.request.SetEquipInfoRequest;
import com.example.baidu.oemequipmentsystem.equip.model.entity.request.UpdateEquipInfoRequest;
import com.example.baidu.oemequipmentsystem.equip.model.entity.response.BaseArrayResponse;
import com.example.baidu.oemequipmentsystem.equip.model.entity.response.BaseResponseModel;
import com.squareup.okhttp.OkHttpClient;

import java.net.CookieManager;
import java.net.CookiePolicy;

import retrofit.Callback;
import retrofit.Retrofit;

/**
 * Created by zhangbinbin03 on 16/6/6.
 */
public class MTTApiClient {

    private static final String BASE_URL = "http://10.95.40.15:8066";
    //private static final String BASE_URL = "http://172.22.181.71";
    private static MTTApiClient sInstance;
    private final MTTApi mAPI;
    private static OkHttpClient sOkHttpClient;

    private MTTApiClient() {
        sOkHttpClient = new OkHttpClient();
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        sOkHttpClient.setCookieHandler(cookieManager);
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(sOkHttpClient)
                .addConverterFactory(FastjsonConverterFactory.create())
                .build();
        mAPI = adapter.create(MTTApi.class);
    }

    public static MTTApiClient getInstance() {
        if (sInstance == null) {
            sInstance = new MTTApiClient();
        }
        return sInstance;
    }

    /**
     * 查询所有设备的基本信息
     */
    public void getEquipInfo(Callback<BaseArrayResponse<EquipModel>> callback) {
        BaseRequest request = new BaseRequest();
        mAPI.getEquipInfo(request).enqueue(callback);
    }

    /**
     * 添加新设备的基本信息
     */
    public void setEquipInfo(Callback<BaseResponseModel> callback, String imei, String model, String os, String cpu, String memory, String storage, String resolution, String responsible, int source, String manufacturer, int state) {
        SetEquipInfoRequest request = new SetEquipInfoRequest(imei, model, os, cpu, memory, storage, resolution, responsible, source, manufacturer, state);
        mAPI.setEquipInfo(request).enqueue(callback);
    }

    /**
     * 更改设备的信息(责任人/状态)
     */
    public void updateEquipInfo(Callback<BaseResponseModel> callback,String imei,String responsible,String manufacturer,int state){
        UpdateEquipInfoRequest request=new UpdateEquipInfoRequest(imei,responsible,manufacturer,state);
        mAPI.updateEquipInfo(request).enqueue(callback);
    }

    /**
     * 获取责任人列表
     */
    public void getResponsibleInfo(Callback<BaseArrayResponse<ResponsibleModel>> callback) {
        BaseRequest request = new BaseRequest();
        mAPI.getResponsibleInfo(request).enqueue(callback);
    }

    /**
     * 获取厂商列表
     */
    public void getManufacturerInfo(Callback<BaseArrayResponse<ManufacturerModel>> callback) {
        BaseRequest request = new BaseRequest();
        mAPI.getManufacturerInfo(request).enqueue(callback);
    }

}
