package com.example.baidu.oemequipmentsystem.equip.ui.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.baidu.oemequipmentsystem.R;

import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.OnWheelChangedListener;
import antistatic.spinnerwheel.adapters.ArrayWheelAdapter;


public class ChooseSourceDialog extends Dialog implements View.OnClickListener{

    private Context context;
    private SourceChooseListener listener;
    private AbstractWheel bicycleBrand;
    private Button btn_branddialog_submit;
    private ImageView iv_close;

    private String[] brandItems={"自采","厂商"};
    private String currentSource="自采";

    public ChooseSourceDialog(Context context, int theme, SourceChooseListener listener) {
        super(context, theme);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_choose_source);

        initUI();
        init();

    }
    private void init(){
        ArrayWheelAdapter<String> adapter = new ArrayWheelAdapter<String>(context ,brandItems);
        adapter.setTextSize(20);
        bicycleBrand.setViewAdapter(adapter);
    }
    private void initUI() {
        setCanceledOnTouchOutside(false);
        iv_close = (ImageView) findViewById(R.id.iv_dialog_close);
        iv_close.setOnClickListener(this);
        bicycleBrand = (AbstractWheel) findViewById(R.id.bicycleBrand);
        bicycleBrand.setVisibleItems(5);
        bicycleBrand.addChangingListener(new OnWheelChangedListener() {
            public void onChanged(AbstractWheel wheel, int oldValue, int newValue) {
                currentSource = brandItems[newValue];
            }
        });

        btn_branddialog_submit=(Button)findViewById(R.id.btn_branddialog_submit);
        btn_branddialog_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_branddialog_submit:
                listener.sourceChoose(currentSource);
                dismiss();
                break;
            case R.id.iv_dialog_close:
                dismiss();
                break;
        }
    }

    public interface SourceChooseListener{
        void sourceChoose(String currentSource);
    }
}
