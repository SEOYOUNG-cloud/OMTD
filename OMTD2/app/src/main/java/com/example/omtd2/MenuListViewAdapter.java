package com.example.omtd2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuListViewAdapter extends BaseAdapter {
    private ArrayList<ListVO> listVO = new ArrayList<ListVO>();
    //public MenuListViewAdapter(){}

    Context ctxt;
    int layout;
    LayoutInflater inf;

    public MenuListViewAdapter(Context ctxt, int layout, ArrayList<ListVO> list){
        this.ctxt =  ctxt;
        this.layout = layout;
        this.listVO = list;
        inf = (LayoutInflater)ctxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return listVO.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menu_listview,parent, false);
        }

        ImageView image = (ImageView) convertView.findViewById(R.id.img);
        TextView cost = (TextView) convertView.findViewById(R.id.cost);
        TextView Context = (TextView) convertView.findViewById(R.id.context);

        ListVO listViewItem = listVO.get(position);

        image.setImageResource(listViewItem.getImg());
        cost.setText(listViewItem.getCost());
        Context.setText(listViewItem.getContext());

        return convertView;

    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int position){
        return listVO.get(position);
    }

    /*public void addVO(Drawable icon, String cost, String desc){
        ListVO item = new ListVO();

        item.setImg(icon);
        item.setCost(cost);
        item.setContext(desc);

        listVO.add(item);
    }*/
}
