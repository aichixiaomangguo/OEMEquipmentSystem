package com.example.baidu.oemequipmentsystem.equip.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.baidu.oemequipmentsystem.R;
import com.example.baidu.oemequipmentsystem.equip.model.Impl.EquipImpl;
import com.example.baidu.oemequipmentsystem.equip.model.entity.EquipModel;
import com.example.baidu.oemequipmentsystem.equip.model.entity.ManufacturerModel;
import com.example.baidu.oemequipmentsystem.equip.model.entity.ResponsibleModel;
import com.example.baidu.oemequipmentsystem.equip.model.listener.OnDeleteEquipInfoListener;
import com.example.baidu.oemequipmentsystem.equip.model.listener.OnGetEquipInfoListener;
import com.example.baidu.oemequipmentsystem.equip.ui.adapter.ExpandableListAdapter;
import com.example.baidu.oemequipmentsystem.equip.ui.custom.DeleteConfirmDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangbinbin03 on 16/6/8.
 */
public class WatchEquipActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener  {

    private static final String TAG = "WatchEquipActivity";

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private SwipeRefreshLayout mSwipeLayout;
    private ImageView img_watch_back;
    private List<EquipModel> baiduList;
    private List<EquipModel> otherList;
    private List<ResponsibleModel> responsibleModels;
    private List<ManufacturerModel> manufacturerModels;
    private DeleteConfirmDialog deleteConfirmDialog;
    private int deleteChildPosition,deleteGroupPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_equip);
        init();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSwipeLayout.post(new Runnable(){
            @Override
            public void run() {
                mSwipeLayout.setRefreshing(true);
            }
        });
        onRefresh();
    }

    public void init(){
        Bundle bundle=getIntent().getExtras();
        responsibleModels=(List<ResponsibleModel>)bundle.getSerializable("responsible");
        manufacturerModels=(List<ManufacturerModel>)bundle.getSerializable("manufacturer");
    }

    public void initView() {

        img_watch_back = (ImageView) findViewById(R.id.img_watch_back);
        img_watch_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleteConfirmDialog=new DeleteConfirmDialog(this, R.style.oem_dialog_style, new DeleteConfirmDialog.DeleteChooseListener() {
            @Override
            public void deleteChoose(final boolean isDelete) {
                if(isDelete){
                    String imei="";
                    if(deleteGroupPosition==0){
                        imei=otherList.get(deleteChildPosition).getImei();
                    }else
                        imei=baiduList.get(deleteChildPosition).getImei();

                    EquipImpl.getInstance().deleteEquipInfo(imei,new OnDeleteEquipInfoListener() {
                        @Override
                        public void deleteEquipInfoSuccess(String isSuccess) {
                            if(isSuccess.equals("success")){
                                Toast.makeText(WatchEquipActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                                mSwipeLayout.post(new Runnable(){
                                    @Override
                                    public void run() {
                                        mSwipeLayout.setRefreshing(true);
                                    }
                                });
                                onRefresh();
                            }
                            else
                                Toast.makeText(WatchEquipActivity.this,"删除失败",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        expandableListView = (ExpandableListView) findViewById(R.id.el_watch_equip);
        expandableListAdapter = new ExpandableListAdapter(WatchEquipActivity.this);
        expandableListView.setAdapter(expandableListAdapter);

        for(int i = 0; i < expandableListAdapter.getGroupCount(); i++){
            expandableListView.expandGroup(i);
        }

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                if(childPosition!=0){
                    Intent intent =new Intent(WatchEquipActivity.this,EquipDetailActivity.class);
                    Bundle bundle=new Bundle();
                    if(groupPosition==0)
                        bundle.putSerializable("model",otherList.get(childPosition));
                    else
                        bundle.putSerializable("model",baiduList.get(childPosition));

                    bundle.putSerializable("responsible",(Serializable)responsibleModels);
                    bundle.putSerializable("manufacturer",(Serializable)manufacturerModels);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                if(groupPosition==0&&childPosition==0){
                    if(expandableListView.isGroupExpanded(0))
                        expandableListView.collapseGroup(0);
                    else
                        expandableListView.expandGroup(0);

                }
                if(groupPosition==1&&childPosition==0)
                    if(expandableListView.isGroupExpanded(1))
                        expandableListView.collapseGroup(1);
                    else
                        expandableListView.expandGroup(1);

                return false;
            }
        });

        expandableListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            public boolean onItemLongClick(AdapterView<?> parent, View childView, int flatPos, long id)
            {
                if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_CHILD)
                {
                    long packedPos = ((ExpandableListView) parent).getExpandableListPosition(flatPos);
                    deleteGroupPosition = ExpandableListView.getPackedPositionGroup(packedPos);
                    deleteChildPosition = ExpandableListView.getPackedPositionChild(packedPos);

                    if(deleteChildPosition!=0){
                        deleteConfirmDialog.show();
                    }

                    return true;
                }
                return false;
            }

        });

        mSwipeLayout= (SwipeRefreshLayout) findViewById(R.id.sl_watch_equip);
        mSwipeLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {

        baiduList=new ArrayList<>();
        otherList=new ArrayList<>();

        EquipImpl.getInstance().getEquipInfo(new OnGetEquipInfoListener() {
            @Override
            public void getEquipInfoSuccess(List<EquipModel> equipModels) {
                for(EquipModel equipModel:equipModels){
                    if(equipModel.getSource()==1){
                        otherList.add(equipModel);
                    }else
                        baiduList.add(equipModel);
                }
                if(otherList.size()!=0)
                    otherList.add(0,new EquipModel("","","","","","","","",0,"",0));
                if(baiduList.size()!=0)
                    baiduList.add(0,new EquipModel("","","","","","","","",0,"",0));

                expandableListAdapter.setData(baiduList,otherList);

                mSwipeLayout.setRefreshing(false);
            }
        });

//        otherList.add(new EquipModel("2367126482","CHE2-LUL00","ANDROID 4", BaseUtil.getCPU(),"16db","20gb","1920*1080","刘准",0,"小米",0));
//        otherList.add(new EquipModel("2367126482","CHE2-LUL00","ANDROID 4", BaseUtil.getCPU(),"16db","20gb","1920*1080","刘准",0,"小米",0));
//        otherList.add(new EquipModel("2367126482","CHE2-LUL00","ANDROID 4", BaseUtil.getCPU(),"16db","20gb","1920*1080","刘准",0,"小米",0));
//        otherList.add(new EquipModel("2367126482","CHE2-LUL00","ANDROID 4", BaseUtil.getCPU(),"16db","20gb","1920*1080","刘准",0,"小米",0));
//
//        baiduList.add(new EquipModel("2367126482","CHE2-LUL00","ANDROID 4", BaseUtil.getCPU(),"16db","20gb","1920*1080","刘准",0,"华为",0));
//        baiduList.add(new EquipModel("2367126482","CHE2-LUL00","ANDROID 4", BaseUtil.getCPU(),"16db","20gb","1920*1080","刘准",0,"华为",0));
//        baiduList.add(new EquipModel("2367126482","CHE2-LUL00","ANDROID 4", BaseUtil.getCPU(),"16db","20gb","1920*1080","刘准",0,"华为",0));
//        baiduList.add(new EquipModel("2367126482","CHE2-LUL00","ANDROID 4", BaseUtil.getCPU(),"16db","20gb","1920*1080","刘准",0,"华为",0));
//
//
//        if(otherList.size()!=0)
//            otherList.add(0,new EquipModel("","","","","","","","",0,"",0));
//        if(baiduList.size()!=0)
//            baiduList.add(0,new EquipModel("","","","","","","","",0,"",0));
//        expandableListAdapter.setData(baiduList,otherList);
//
//        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
