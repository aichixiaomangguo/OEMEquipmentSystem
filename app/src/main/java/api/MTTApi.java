package api;

import com.example.baidu.oemequipmentsystem.equip.model.entity.EquipModel;
import com.example.baidu.oemequipmentsystem.equip.model.entity.ManufacturerModel;
import com.example.baidu.oemequipmentsystem.equip.model.entity.ResponsibleModel;
import com.example.baidu.oemequipmentsystem.equip.model.entity.request.BaseRequest;
import com.example.baidu.oemequipmentsystem.equip.model.entity.response.BaseArrayResponse;
import com.example.baidu.oemequipmentsystem.equip.model.entity.response.BaseResponseModel;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by zhangbinbin03 on 16/6/6.
 */
public interface MTTApi {

    /**
     * 查询所有设备的基本信息
     */
    @POST("/DeviceManagerSys/DeviceInfo-queryallinfo.php")
    Call<BaseArrayResponse<EquipModel>> getEquipInfo(@Body BaseRequest request);

    /**
     * 上传新设备的基本信息
     */
    @POST("/DeviceManagerSys/DeviceInfo-addoneitem.php")
    Call<BaseResponseModel> setEquipInfo(@Body BaseRequest request);

    /**
     * 更改设备的信息(责任人/状态)
     */
    @POST("/DeviceManagerSys/DeviceInfo-alterresitem.php")
    Call<BaseResponseModel> updateEquipInfo(@Body BaseRequest request);

    /**
     * 获取责任人列表
     */
    @POST("/DeviceManagerSys/DeviceResponsibleInfo-queryallres.php")
    Call<BaseArrayResponse<ResponsibleModel>> getResponsibleInfo(@Body BaseRequest request);

    /**
     * 获取厂商列表
     */
    @POST("/DeviceManagerSys/DeviceManufacturerInfo-queryallmanu.php")
    Call<BaseArrayResponse<ManufacturerModel>> getManufacturerInfo(@Body BaseRequest request);


}
