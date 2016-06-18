package com.example.baidu.oemequipmentsystem.equip.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baidu.oemequipmentsystem.R;
import com.example.baidu.oemequipmentsystem.equip.model.Impl.EquipImpl;
import com.example.baidu.oemequipmentsystem.equip.model.entity.ManufacturerModel;
import com.example.baidu.oemequipmentsystem.equip.model.entity.ResponsibleModel;
import com.example.baidu.oemequipmentsystem.equip.model.listener.OnSetEquipInfoListener;
import com.example.baidu.oemequipmentsystem.equip.ui.custom.ChooseManufcturerDialog;
import com.example.baidu.oemequipmentsystem.equip.ui.custom.ChooseResponsibleDialog;
import com.example.baidu.oemequipmentsystem.equip.ui.custom.ChooseSourceDialog;
import com.example.baidu.oemequipmentsystem.equip.ui.custom.ChooseStateDialog;
import com.example.baidu.oemequipmentsystem.util.BaseUtil;

import java.util.List;

/**
 * Created by zhangbinbin03 on 16/6/8.
 */
public class AddEquipActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "AddEquipActivity";

    private ImageView img_add_back;
    private ChooseResponsibleDialog chooseResponsibleDialog;
    private ChooseSourceDialog chooseSourceDialog;
    private ChooseStateDialog chooseStateDialog;
    private ChooseManufcturerDialog chooseManufcturerDialog;
    private TextView txt_add_imei, txt_add_model, txt_add_cpu, txt_add_memory, txt_add_storage, txt_add_resolution, txt_add_os;
    private Button btn_add_submit;
    private com.example.baidu.oemequipmentsystem.equip.ui.custom.SpinnerButton btn_add_responsible, btn_add_source, btn_add_state;
    private int source, state;
    private String manufacturer;
    //    private static final int MY_READ_PHONE_STATE=1;
//    private boolean isPermit=false;
    private List<ResponsibleModel> responsibleModels;
    private List<ManufacturerModel> manufacturerModels;
    private Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_equip);
        init();
        initView();
    }

    public void init() {
        Bundle bundle = getIntent().getExtras();
        responsibleModels = (List<ResponsibleModel>) bundle.getSerializable("responsible");
        manufacturerModels = (List<ManufacturerModel>) bundle.getSerializable("manufacturer");
    }

    public void initView() {

        loadingDialog = BaseUtil.createLoadingDialog(this, "设备信息上传中...");

        txt_add_imei = (TextView) findViewById(R.id.txt_add_imei);
        txt_add_model = (TextView) findViewById(R.id.txt_add_model);
        txt_add_os = (TextView) findViewById(R.id.txt_add_os);
        txt_add_cpu = (TextView) findViewById(R.id.txt_add_cpu);
        txt_add_memory = (TextView) findViewById(R.id.txt_add_memory);
        txt_add_storage = (TextView) findViewById(R.id.txt_add_storage);
        txt_add_resolution = (TextView) findViewById(R.id.txt_add_resolution);

        txt_add_model.setText(BaseUtil.getModel());
        txt_add_os.setText("android " + BaseUtil.getOSVersion());
        txt_add_resolution.setText(BaseUtil.getResulotion(this));
        txt_add_memory.setText(BaseUtil.getMemory(this) + " GB");
        txt_add_storage.setText(BaseUtil.getTotalStorageSize() + " GB");
        txt_add_cpu.setText(BaseUtil.getCPU());
        txt_add_imei.setText(BaseUtil.getIMEI(this));

        btn_add_responsible = (com.example.baidu.oemequipmentsystem.equip.ui.custom.SpinnerButton) findViewById(R.id.btn_add_responsible);
        btn_add_responsible.setOnClickListener(this);
        btn_add_source = (com.example.baidu.oemequipmentsystem.equip.ui.custom.SpinnerButton) findViewById(R.id.btn_add_source);
        btn_add_source.setOnClickListener(this);
        btn_add_state = (com.example.baidu.oemequipmentsystem.equip.ui.custom.SpinnerButton) findViewById(R.id.btn_add_state);
        btn_add_state.setOnClickListener(this);
        btn_add_submit = (Button) findViewById(R.id.btn_add_submit);
        btn_add_submit.setOnClickListener(this);

        img_add_back = (ImageView) findViewById(R.id.img_add_back);
        img_add_back.setOnClickListener(this);

        chooseResponsibleDialog = new ChooseResponsibleDialog(this, R.style.oem_dialog_style, responsibleModels, new ChooseResponsibleDialog.ResponsibleChooseListener() {
            @Override
            public void responsibleChoose(String currentResponsible) {
                btn_add_responsible.setText(currentResponsible);
            }
        });

        chooseStateDialog = new ChooseStateDialog(this, R.style.oem_dialog_style, new ChooseStateDialog.StateChooseListener() {
            @Override
            public void stateChoose(String currentState) {
                btn_add_state.setText(currentState);
                if (currentState.equals("未还"))
                    state = 0;
                else
                    state = 1;
            }
        });

        chooseManufcturerDialog = new ChooseManufcturerDialog(this, R.style.oem_dialog_style, manufacturerModels, new ChooseManufcturerDialog.ManufacturerChooseListener() {
            @Override
            public void manufacturerChoose(String currentManufacturer) {
                manufacturer = currentManufacturer;
                btn_add_source.setText(manufacturer);
            }
        });


        chooseSourceDialog = new ChooseSourceDialog(this, R.style.oem_dialog_style, new ChooseSourceDialog.SourceChooseListener() {
            @Override
            public void sourceChoose(String currentSource) {
                btn_add_source.setText(currentSource);
                if (currentSource.equals("厂商")) {
                    source = 1;
                    chooseManufcturerDialog.show();
                } else {
                    source = 0;
                    manufacturer = "百度";
                }
            }
        });
    }

//动态获取权限
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(Build.VERSION.SDK_INT>=23){
//            if(!isPermit)
//                getPermission();
//        }else{
//            txt_add_imei.setText(BaseUtil.getIMEI(this));
//        }
//    }
//
//    public void getPermission(){
//        //申请权限
//        if (ContextCompat.checkSelfPermission(AddEquipActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(AddEquipActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, MY_READ_PHONE_STATE);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_READ_PHONE_STATE: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    txt_add_imei.setText(BaseUtil.getIMEI(this));
//                    isPermit=true;
//                }else{
//                    isPermit=false;
//                }
//                return;
//            }
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_add_back:
                Intent intent = new Intent(AddEquipActivity.this, EquipActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_add_responsible:
                chooseResponsibleDialog.show();
                break;
            case R.id.btn_add_source:
                chooseSourceDialog.show();
                break;
            case R.id.btn_add_state:
                chooseStateDialog.show();
                break;
            case R.id.btn_add_submit:
                if (BaseUtil.isNetworkAvailable(this)) {

                    if (btn_add_responsible.getText().toString().equals("责任人"))
                        Toast.makeText(AddEquipActivity.this, "请选择设备责任人", Toast.LENGTH_SHORT).show();
                    else if (btn_add_source.getText().toString().equals("来源"))
                        Toast.makeText(AddEquipActivity.this, "请选择设备来源", Toast.LENGTH_SHORT).show();
                    else if (btn_add_state.getText().toString().equals("状态"))
                        Toast.makeText(AddEquipActivity.this, "请选择设备状态", Toast.LENGTH_SHORT).show();
                    else {
                        loadingDialog.show();
                        EquipImpl.getInstance().setEquipInfo(txt_add_imei.getText().toString(), txt_add_model.getText().toString(), txt_add_os.getText().toString(), txt_add_cpu.getText().toString(),
                                txt_add_memory.getText().toString(), txt_add_storage.getText().toString(), txt_add_resolution.getText().toString(), btn_add_responsible.getText().toString(),
                                source, manufacturer, state, new OnSetEquipInfoListener() {
                                    @Override
                                    public void setEquipInfoSuccess(String isSuccess) {
                                        loadingDialog.dismiss();
                                        if (isSuccess.equals("success")) {
                                            Toast.makeText(AddEquipActivity.this, "添加信息成功", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else if (isSuccess.equals("exist"))
                                            Toast.makeText(AddEquipActivity.this, "添加失败,本台设备信息已存在", Toast.LENGTH_SHORT).show();
                                        else
                                            Toast.makeText(AddEquipActivity.this, "网络有点小毛病...", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                } else {
                    Toast.makeText(AddEquipActivity.this, "请连接网络", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent i = new Intent(AddEquipActivity.this, EquipActivity.class);
            startActivity(i);
        }
        return super.onKeyDown(keyCode, event);

    }
}
