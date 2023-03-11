package com.example.hoangminhchien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.hoangminhchien.adapter.adaptertruyen;
import com.example.hoangminhchien.database.databasedoctruyen;
import com.example.hoangminhchien.model.Truyen;

import java.util.ArrayList;
import java.util.Locale;

public class MainTimKiem extends AppCompatActivity {
    ListView listView;
    EditText edt;
    ArrayList<Truyen> TruyenArrayList;
    ArrayList<Truyen> arrayList;
    adaptertruyen adapterTruyen;
    databasedoctruyen databasedocTruyen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tim_kiem);

        listView = findViewById(R.id.listviewTimKiem);
        edt = findViewById(R.id.timkiem);

        initlist();

        // nhận item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainTimKiem.this,MainNoiDung.class);

                String tent =arrayList.get(position).getTenTruyen();
                String noidungt = arrayList.get(position).getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                startActivity(intent);

            }
        });

        // edit text tìm kiếm
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }


        });
    }

    private void filter (String text){
        //xoá dữ liệu mảng
        arrayList.clear();
        ArrayList<Truyen>filteredList = new ArrayList<>();
        for (Truyen item : TruyenArrayList){
            if (item.getTenTruyen().toLowerCase().contains(text.toLowerCase())){
                // thêm item vào filteredlist
                filteredList.add(item);
                //them vào mảng
                arrayList.add(item);
            }
        }
        adapterTruyen.filterList(filteredList);
    }
// phương thức lấy dữ liệu gắn vào lisview
    private void initlist() {
        TruyenArrayList = new ArrayList<>();

        arrayList = new ArrayList<>();

        databasedocTruyen = new databasedoctruyen(this);
        Cursor cursor = databasedocTruyen.getData2();

        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String tentruyen = cursor.getString(1);
            String noidung = cursor.getString(2);
            String anh = cursor.getString(3);
            int id_tk = cursor.getInt(4);

            adapterTruyen=new adaptertruyen(getApplicationContext(),TruyenArrayList);

            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

            arrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));


            listView.setAdapter(adapterTruyen);
        }
        cursor.moveToFirst();
        cursor.close();
    }
}