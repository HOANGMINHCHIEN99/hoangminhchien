package com.example.hoangminhchien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.collection.CircularArray;

import com.example.hoangminhchien.R;
import com.example.hoangminhchien.model.Truyen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adaptertruyen extends BaseAdapter {

    private Context context;
    private ArrayList<Truyen>listTruyen;
    public adaptertruyen(Context context,ArrayList<Truyen>listTruyen){
        this.context = context;
        this.listTruyen = listTruyen;
    }

    @Override
    public int getCount() {

        return listTruyen.size();
    }

    @Override
    public Object getItem(int position) {

        return listTruyen.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    public class ViewHolder{
        TextView txtTenTruyen;
        ImageView imgtruyen;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        viewHolder = new ViewHolder();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.newtruyen,null);
        viewHolder.txtTenTruyen=convertView.findViewById(R.id.textviewTentruyenNew);
        viewHolder.imgtruyen=convertView.findViewById(R.id.imgNewTruyen);

        Truyen truyen = (Truyen) getItem(position);
        viewHolder.txtTenTruyen.setText(truyen.getTenTruyen());
        Picasso.get().load(truyen.getAnh()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(viewHolder.imgtruyen);
        return convertView;

    }
}
