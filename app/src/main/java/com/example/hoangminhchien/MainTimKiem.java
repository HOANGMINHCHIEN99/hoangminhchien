package com.example.hoangminhchien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.hoangminhchien.adapter.adaptertruyen;
import com.example.hoangminhchien.database.databasedoctruyen;
import com.example.hoangminhchien.model.Truyen;

import java.util.ArrayList;

public class MainTimKiem extends AppCompatActivity {
    ListView listView;
    EditText edt;
    ArrayList<Truyen> TruyenArrayList;

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

                String tent = TruyenArrayList.get(position).getTenTruyen();
                String noidungt = TruyenArrayList.get(position).getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                startActivity(intent);

            }
        });
    }
// phương thức lấy dữ liệu gắn vào lisview
    private void initlist() {
        TruyenArrayList = new ArrayList<>();
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
            listView.setAdapter(adapterTruyen);
        }
        cursor.moveToFirst();
        cursor.close();
    }
}