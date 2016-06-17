package com.example.baidu.oemequipmentsystem.equip.ui.activity;

import android.app.Activity;
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
import com.example.baidu.oemequipmentsystem.equip.model.entity.EquipModel;
import com.example.baidu.oemequipmentsystem.equip.model.entity.ManufacturerModel;
import com.example.baidu.oemequipmentsystem.equip.model.entity.ResponsibleModel;
import com.example.baidu.oemequipmentsystem.equip.model.listener.OnUpdateEquipInfoListener;
import com.example.baidu.oemequipmentsystem.equip.ui.custom.ChooseManufcturerDialog;
import com.example.baidu.oemequipmentsystem.equip.ui.custom.ChooseResponsibleDialog;
import com.example.baidu.oemequipmentsystem.equip.ui.custom.ChooseStateDialog;
import com.example.baidu.oemequipmentsystem.util.BaseUtil;

import java.util.List;

/**
 * Created by zhangbinbin03 on 16/6/9.
 */
public class EquipDetailActivity extends Activity implements View.OnClickListener{

    private TextView txt_detail_imei, txt_detail_model,txt_detail_manufacturer, txt_detail_cpu, txt_detail_memory, txt_detail_storage, txt_detail_resolution, txt_detail_os,txt_detail_responsible,txt_detail_source,txt_detail_state,txt_detail_manufacturername;
    private Button btn_detail_submit;
    private com.example.baidu.oemequipmentsystem.equip.ui.custom.SpinnerButton btn_detail_change_responsible,btn_detail_change_manufacturer,btn_detail_change_state;
    private ChooseResponsibleDialog chooseResponsibleDialog;
    private ChooseStateDialog chooseStateDialog;
    private ChooseManufcturerDialog chooseManufcturerDialog;
    private EquipModel equipModel;
    private ImageView img_watch_back;
    private int state;
    private String preResponsible,preState,preManufacturer;
    private List<ResponsibleModel> responsibleModels;
    private List<ManufacturerModel> manufacturerModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_detail);
        init();
        initView();
    }

    public void init(){
        Bundle bundle=getIntent().getExtras();
        equipModel= (EquipModel)bundle.getSerializable("model");
        responsibleModels=(List<ResponsibleModel>)bundle.getSerializable("responsible");
        manufacturerModels=(List<ManufacturerModel>)bundle.getSerializable("manufacturer");
    }

    public void initView() {

        img_watch_back = (ImageView) findViewById(R.id.img_detail_back);
        img_watch_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EquipDetailActivity.this, WatchEquipActivity.class);
                startActivity(intent);
                finish();
            }
        });

        txt_detail_imei=(TextView) findViewById(R.id.txt_detail_imei);
        txt_detail_model=(TextView) findViewById(R.id.txt_detail_model);
        txt_detail_cpu=(TextView) findViewById(R.id.txt_detail_cpu);
        txt_detail_memory=(TextView) findViewById(R.id.txt_detail_memory);
        txt_detail_storage=(TextView) findViewById(R.id.txt_detail_storage);
        txt_detail_resolution=(TextView) findViewById(R.id.txt_detail_resolution);
        txt_detail_os=(TextView) findViewById(R.id.txt_detail_os);
        txt_detail_responsible=(TextView) findViewById(R.id.txt_detail_responsible);
        txt_detail_source=(TextView) findViewById(R.id.txt_detail_source);
        txt_detail_manufacturer=(TextView) findViewById(R.id.txt_detail_manufacturer);
        txt_detail_state=(TextView) findViewById(R.id.txt_detail_state);
        txt_detail_manufacturername=(TextView) findViewById(R.id.txt_detail_manufacturername);

        txt_detail_imei.setText(equipModel.getImei());
        txt_detail_model.setText(equipModel.getModel());
        txt_detail_cpu.setText(equipModel.getCpu());
        txt_detail_memory.setText(equipModel.getMemory());
        txt_detail_storage.setText(equipModel.getStorage());
        txt_detail_resolution.setText(equipModel.getResolution());
        txt_detail_os.setText(equipModel.getOs());
        txt_detail_responsible.setText(equipModel.getResponsible());

        if(equipModel.getState()==0)
            txt_detail_state.setText("未还");
        else
            txt_detail_state.setText("已还");

        preResponsible=equipModel.getResponsible();
        preState=txt_detail_state.getText().toString();

        btn_detail_change_responsible=(com.example.baidu.oemequipmentsystem.equip.ui.custom.SpinnerButton) findViewById(R.id.btn_detail_change_responsible);
        btn_detail_change_responsible.setOnClickListener(this);
        btn_detail_change_state=(com.example.baidu.oemequipmentsystem.equip.ui.custom.SpinnerButton) findViewById(R.id.btn_detail_change_state);
        btn_detail_change_state.setOnClickListener(this);
        btn_detail_submit=(Button) findViewById(R.id.btn_detail_submit);
        btn_detail_submit.setOnClickListener(this);
        btn_detail_change_manufacturer=(com.example.baidu.oemequipmentsystem.equip.ui.custom.SpinnerButton) findViewById(R.id.btn_detail_change_manufacturer);
        btn_detail_change_manufacturer.setOnClickListener(this);

        if(equipModel.getSource()==1){
            txt_detail_source.setText("厂商");
            btn_detail_change_manufacturer.setVisibility(View.VISIBLE);
            txt_detail_manufacturername.setVisibility(View.VISIBLE);
            txt_detail_manufacturer.setVisibility(View.VISIBLE);
            txt_detail_manufacturername.setText(equipModel.getManufacturer());
            preManufacturer=equipModel.getManufacturer();
        }
        else{
            txt_detail_source.setText("自采");
        }

        chooseResponsibleDialog=new ChooseResponsibleDialog(this, R.style.oem_dialog_style, responsibleModels,new ChooseResponsibleDialog.ResponsibleChooseListener() {
            @Override
            public void responsibleChoose(String currentResponsible) {
                txt_detail_responsible.setText(currentResponsible);
            }
        });

        chooseStateDialog=new ChooseStateDialog(this, R.style.oem_dialog_style, new ChooseStateDialog.StateChooseListener() {
            @Override
            public void stateChoose(String currentState) {
                txt_detail_state.setText(currentState);
            }
        });

        chooseManufcturerDialog=new ChooseManufcturerDialog(this, R.style.oem_dialog_style, manufacturerModels,new ChooseManufcturerDialog.ManufacturerChooseListener() {
            @Override
            public void manufacturerChoose(String currentManufacturer) {
                txt_detail_manufacturername.setText(currentManufacturer);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_detail_change_responsible:
                chooseResponsibleDialog.show();
                break;
            case R.id.btn_detail_change_state:
                chooseStateDialog.show();
                break;
            case R.id.btn_detail_change_manufacturer:
                chooseManufcturerDialog.show();
                break;
            case R.id.btn_detail_submit:
                if (BaseUtil.isNetworkAvailable(this)) {
                    if(equipModel.getSource()==1){
                        if(preResponsible.equals(txt_detail_responsible.getText().toString()) && preState.equals(txt_detail_state.getText().toString())&& preManufacturer.equals(txt_detail_manufacturername.getText().toString())){
                            Toast.makeText(EquipDetailActivity.this,"未对信息进行更改",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }else {
                        if(preResponsible.equals(txt_detail_responsible.getText().toString()) && preState.equals(txt_detail_state.getText().toString())){
                            Toast.makeText(EquipDetailActivity.this,"未对信息进行更改",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    if(txt_detail_state.getText().toString().equals("未还"))
                        state=0;
                    else
                        state=1;
                    EquipImpl.getInstance().updateEquipInfo(txt_detail_imei.getText().toString(), txt_detail_responsible.getText().toString(), txt_detail_manufacturername.getText().toString(),state, new OnUpdateEquipInfoListener() {
                        @Override
                        public void updateEquipInfoSuccess(String isSuccess) {
                            if(isSuccess.equals("success")){
                                Toast.makeText(EquipDetailActivity.this,"更改信息成功",Toast.LENGTH_SHORT).show();
                                finish();
                            }else
                                Toast.makeText(EquipDetailActivity.this,"网络有点小毛病...",Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(EquipDetailActivity.this, "请连接网络", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK)
            finish();
        return super.onKeyDown(keyCode, event);
    }
}
