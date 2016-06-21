package com.example.baidu.oemequipmentsystem.equip.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.baidu.oemequipmentsystem.R;
import com.example.baidu.oemequipmentsystem.equip.model.Impl.EquipImpl;
import com.example.baidu.oemequipmentsystem.equip.model.entity.ManufacturerModel;
import com.example.baidu.oemequipmentsystem.equip.model.entity.ResponsibleModel;
import com.example.baidu.oemequipmentsystem.equip.model.listener.OnManufacturerInfoListener;
import com.example.baidu.oemequipmentsystem.equip.model.listener.OnResponsibleInfoListener;
import com.example.baidu.oemequipmentsystem.util.BaseUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhangbinbin03 on 16/6/8.
 */
public class EquipActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "EquipActivity";

    private Button btn_add_equip, btn_watch_equip;
    private Dialog loadingDialog;
    private static Boolean isClick = false;

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
        loadingDialog=BaseUtil.createLoadingDialog(this,"数据正在加载...");
    }

    @Override
    public void onClick(final View v) {

        if (!BaseUtil.isNetworkAvailable(this)) {
            Toast.makeText(EquipActivity.this, "请连接网络", Toast.LENGTH_SHORT).show();
            return;
        }
        loadingDialog.show();
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
                        loadingDialog.dismiss();
                    }
                });
            }
        });
    }
    //双击退出应用
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Timer timer = null;
            if (isClick == false) {
                isClick = true;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isClick = false; // 取消退出
                    }
                }, 2000);
            } else {
               finish();
            }
        }
        return false;
    }

}
