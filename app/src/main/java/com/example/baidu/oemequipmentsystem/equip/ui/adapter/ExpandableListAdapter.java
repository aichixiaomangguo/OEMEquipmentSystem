package com.example.baidu.oemequipmentsystem.equip.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.baidu.oemequipmentsystem.R;
import com.example.baidu.oemequipmentsystem.equip.model.entity.EquipModel;
import com.example.baidu.oemequipmentsystem.util.BaseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangbinbin03 on 16/6/8.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter{

    private Context context;
    private String[] source = new String[] { "厂商", "自采" };
    private List<EquipModel> baiduList=new ArrayList<>();
    private List<EquipModel> otherList=new ArrayList<>();
    private int width;

    public ExpandableListAdapter(Context context){
        this.context=context;
        width= BaseUtil.getWindowWidth(context);
    }

    public void setData(List<EquipModel> baiduList,List<EquipModel> otherList){
        this.baiduList=baiduList;
        this.otherList=otherList;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return source.length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return source[groupPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(groupPosition==0)
            return otherList.size();
        return baiduList.size();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if(groupPosition==0)
            return otherList.get(childPosition);
        return baiduList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        HeadViewHolder headViewHolder=null;
        if(convertView==null){
            headViewHolder=new HeadViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_expandlist_head,null);
            headViewHolder.txt_head_source=(TextView) convertView.findViewById(R.id.txt_head_source);
            convertView.setTag(headViewHolder);
        }else{
            headViewHolder=(HeadViewHolder)convertView.getTag();
        }
        headViewHolder.txt_head_source.setText(source[groupPosition]);

       return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildViewHolder childViewHolder=null;
        if(convertView==null){
            childViewHolder=new ChildViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_expandlist_child,null);
            childViewHolder.txt_child_manufacturer=(TextView) convertView.findViewById(R.id.txt_child_manufacturer);
            childViewHolder.txt_child_model=(TextView) convertView.findViewById(R.id.txt_child_model);
            childViewHolder.txt_child_responsible=(TextView) convertView.findViewById(R.id.txt_child_responsible);
            childViewHolder.txt_child_state=(TextView) convertView.findViewById(R.id.txt_child_state);
            childViewHolder.ll_child=(LinearLayout) convertView.findViewById(R.id.ll_child);
            convertView.setTag(childViewHolder);
        }else{
            childViewHolder=(ChildViewHolder)convertView.getTag();
        }

        if(childPosition==0) {
            childViewHolder.ll_child.setBackgroundColor(context.getResources().getColor(R.color.colorChildFirst));
        }else if(childPosition%2==0)
            childViewHolder.ll_child.setBackgroundColor(context.getResources().getColor(R.color.colorChildOdd));
        else
            childViewHolder.ll_child.setBackgroundColor(context.getResources().getColor(R.color.colorChildEven));


        if(groupPosition==0){

            childViewHolder.txt_child_manufacturer.setVisibility(View.VISIBLE);

            LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) childViewHolder.txt_child_model.getLayoutParams();
            layoutParams.width=width/4;
            childViewHolder.txt_child_model.setLayoutParams(layoutParams);
            layoutParams= (LinearLayout.LayoutParams) childViewHolder.txt_child_responsible.getLayoutParams();
            layoutParams.width=width/4;
            childViewHolder.txt_child_responsible.setLayoutParams(layoutParams);

            if(childPosition==0){
                childViewHolder.txt_child_manufacturer.setText("厂商名称");
                childViewHolder.txt_child_model.setText("设备型号");
                childViewHolder.txt_child_responsible.setText("责任人");
                childViewHolder.txt_child_state.setText("设备状态");
                childViewHolder.txt_child_manufacturer.setTextColor(context.getResources().getColor(R.color.colorWhite));
                childViewHolder.txt_child_model.setTextColor(context.getResources().getColor(R.color.colorWhite));
                childViewHolder.txt_child_responsible.setTextColor(context.getResources().getColor(R.color.colorWhite));
                childViewHolder.txt_child_state.setTextColor(context.getResources().getColor(R.color.colorWhite));
            }else{
                childViewHolder.txt_child_manufacturer.setText(otherList.get(childPosition).getManufacturer());
                childViewHolder.txt_child_model.setText(otherList.get(childPosition).getModel());
                childViewHolder.txt_child_responsible.setText(otherList.get(childPosition).getResponsible());
                if(otherList.get(childPosition).getState()==0)
                    childViewHolder.txt_child_state.setText("未还");
                else
                    childViewHolder.txt_child_state.setText("已还");
                childViewHolder.txt_child_manufacturer.setTextColor(context.getResources().getColor(R.color.colorPurple));
                childViewHolder.txt_child_model.setTextColor(context.getResources().getColor(R.color.colorPurple));
                childViewHolder.txt_child_responsible.setTextColor(context.getResources().getColor(R.color.colorPurple));
                childViewHolder.txt_child_state.setTextColor(context.getResources().getColor(R.color.colorPurple));
            }

        }else{
            childViewHolder.txt_child_manufacturer.setVisibility(View.GONE);


            LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) childViewHolder.txt_child_model.getLayoutParams();
            layoutParams.width=width/3;
            childViewHolder.txt_child_model.setLayoutParams(layoutParams);
            layoutParams= (LinearLayout.LayoutParams) childViewHolder.txt_child_responsible.getLayoutParams();
            layoutParams.width=width/3;
            childViewHolder.txt_child_responsible.setLayoutParams(layoutParams);

            if(childPosition==0){
                childViewHolder.txt_child_model.setText("设备型号");
                childViewHolder.txt_child_responsible.setText("责任人");
                childViewHolder.txt_child_state.setText("设备状态");
                childViewHolder.txt_child_model.setTextColor(context.getResources().getColor(R.color.colorWhite));
                childViewHolder.txt_child_responsible.setTextColor(context.getResources().getColor(R.color.colorWhite));
                childViewHolder.txt_child_state.setTextColor(context.getResources().getColor(R.color.colorWhite));
            }else{
                childViewHolder.txt_child_model.setText(baiduList.get(childPosition).getModel());
                childViewHolder.txt_child_responsible.setText(baiduList.get(childPosition).getResponsible());

                if(baiduList.get(childPosition).getState()==0)
                    childViewHolder.txt_child_state.setText("未还");
                else
                    childViewHolder.txt_child_state.setText("已还");

                childViewHolder.txt_child_model.setTextColor(context.getResources().getColor(R.color.colorPurple));
                childViewHolder.txt_child_responsible.setTextColor(context.getResources().getColor(R.color.colorPurple));
                childViewHolder.txt_child_state.setTextColor(context.getResources().getColor(R.color.colorPurple));
            }
        }

        return convertView;


    }

    @Override
    public boolean isChildSelectable(int groupPosition,
                                     int childPosition) {
        return true;
    }

    class HeadViewHolder{
        private TextView txt_head_source;
    }

    class ChildViewHolder{
        private LinearLayout ll_child;
        private TextView txt_child_manufacturer;
        private TextView txt_child_model;
        private TextView txt_child_responsible;
        private TextView txt_child_state;
    }
}
