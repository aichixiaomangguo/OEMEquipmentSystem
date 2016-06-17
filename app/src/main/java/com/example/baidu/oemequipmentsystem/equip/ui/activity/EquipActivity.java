package com.example.baidu.oemequipmentsystem.equip.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.baidu.oemequipmentsystem.R;
import com.example.baidu.oemequipmentsystem.equip.model.Impl.EquipImpl;
import com.example.baidu.oemequipmentsystem.equip.model.entity.EquipModel;
import com.example.baidu.oemequipmentsystem.equip.model.entity.ManufacturerModel;
import com.example.baidu.oemequipmentsystem.equip.model.entity.ResponsibleModel;
import com.example.baidu.oemequipmentsystem.equip.model.listener.OnManufacturerInfoListener;
import com.example.baidu.oemequipmentsystem.equip.model.listener.OnResponsibleInfoListener;
import com.example.baidu.oemequipmentsystem.util.BaseUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangbinbin03 on 16/6/8.
 */
public class EquipActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "EquipActivity";

    private Button btn_add_equip, btn_watch_equip;
    private List<EquipModel> baiduList;
    private List<EquipModel> otherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        btn_add_equip = (Button) findViewById(R.id.btn_add_equip);
        btn_watch_equip = (Button) findViewById(R.id.btn_watch_equip);
        btn_add_equip.setOnClickListener(this);
        btn_watch_equip.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {

        if (!BaseUtil.isNetworkAvailable(this)) {
            Toast.makeText(EquipActivity.this, "请连接网络", Toast.LENGTH_SHORT).show();
            return;
        }

        EquipImpl.getInstance().getResponsibleInfo(new OnResponsibleInfoListener() {
            @Override
            public void getResponsibleInfoSuccess(final List<ResponsibleModel> responsibleModels) {
                EquipImpl.getInstance().getManufacturerInfo(new OnManufacturerInfoListener() {
                    @Override
                    public void getManufacturerInfoSuccess(final List<ManufacturerModel> manufacturerModels) {

                        switch (v.getId()) {
                            case R.id.btn_add_equip:
                                Intent intent = new Intent(EquipActivity.this, AddEquipActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("responsible", (Serializable) responsibleModels);
                                bundle.putSerializable("manufacturer", (Serializable) manufacturerModels);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                break;
                            case R.id.btn_watch_equip:
                                intent = new Intent(EquipActivity.this, WatchEquipActivity.class);
                                bundle = new Bundle();
                                bundle.putSerializable("responsible", (Serializable) responsibleModels);
                                bundle.putSerializable("manufacturer", (Serializable) manufacturerModels);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                break;
                        }
                    }
                });
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);

    }

}
