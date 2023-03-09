package com.example.hoangminhchien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hoangminhchien.database.databasedoctruyen;
import com.example.hoangminhchien.model.TaiKhoan;

public class MainDangKy extends AppCompatActivity {

    EditText edtDKTaiKhoan,edtDKMatKhau,edtDKEmail;
    Button btnDangKy,btnDKDangNhap;

    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_ky);

        databasedoctruyen = new databasedoctruyen(this);

        Anhxa();

        // bấm nút đăng ký
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taikhoan = edtDKTaiKhoan.getText().toString();
                String matkhau = edtDKMatKhau.getText().toString();
                String emai = edtDKEmail.getText().toString();

                TaiKhoan taikhoan1 = CreateTaiKhoan();
                if (taikhoan.equals("") || matkhau.equals("") || emai.equals("")){
                    Log.e("Thông báo:" ,"Chưa nhập đủ thông tin");
                }
                else {
                   databasedoctruyen.AddTaiKhoan(taikhoan1);
                    Toast.makeText(MainDangKy.this,"Đăng ký thành công", Toast.LENGTH_LONG).show();

                }
            }
        });

        //bấm nút quay lại
        btnDKDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    // phương thức tạo tk
    private TaiKhoan CreateTaiKhoan (){
        String taikhoan = edtDKTaiKhoan.getText().toString();
        String matkhau = edtDKMatKhau.getText().toString();
        String email = edtDKEmail.getText().toString();
        int phanquyen = 1;
        TaiKhoan tk = new TaiKhoan(taikhoan,matkhau,email,phanquyen);
        return tk;
    }
    private void Anhxa() {

        edtDKEmail = findViewById(R.id.dkemail);
        edtDKMatKhau = findViewById(R.id.dkmatkhau);
        edtDKTaiKhoan = findViewById(R.id.dktaikhoan);
        btnDangKy = findViewById(R.id.dkdangky);
        btnDKDangNhap = findViewById(R.id.dkdangnhap);

    }


}