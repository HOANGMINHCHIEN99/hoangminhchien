package com.example.hoangminhchien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainThongTin extends AppCompatActivity {

    TextView txtThongtinapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thong_tin);

        txtThongtinapp = findViewById(R.id.textviewthongtin);

        String thongtin = "Hoàng Minh Chiến \n"+
                "MSSV : K195480106002\n"+
                "Lớp 55KMT-01 \n "+
                "GVHD : ĐỖ DUY CỐP ";

        txtThongtinapp.setText(thongtin);

    }
}