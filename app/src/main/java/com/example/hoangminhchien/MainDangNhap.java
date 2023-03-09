package com.example.hoangminhchien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hoangminhchien.database.databasedoctruyen;

public class MainDangNhap extends AppCompatActivity {

    //tạo đối tượng cho đăng nhập
    EditText edtTaiKhoan,edtMatKhau;
    Button btnDangNhap,btnDangKy;

    // tạo đối tượng cho database
   databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_nhap);

        Anhxa();


// đối tượng dattabase
        databasedoctruyen = new databasedoctruyen(this);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainDangNhap.this,MainDangKy.class);
                startActivity(intent);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gán cho các biến giá trị nhập vào từ editText
                String tentaikhoan = edtTaiKhoan.getText().toString();
                String matkhau = edtMatKhau.getText().toString();

                // sử dụng con trở để lấy dữ liệu gọi tới getdataa để lấy tất cả tk ở database
                Cursor cursor = databasedoctruyen.getData();
                while (cursor.moveToNext()){

                    // gán dữ liệu vào biến dữ liệu mk số 1 mà dữ liệu tk số 2
                    String datatentaikhoan = cursor.getString(1);
                    String datamatkhau = cursor.getString(2);
                    // nếu tk mk nhập bàn phím khớp database thì
                    if (datatentaikhoan.equals(tentaikhoan) && datamatkhau.equals(matkhau)){
                        int phanquyen = cursor.getInt(4);
                        int idd = cursor.getInt(0);

                        String email = cursor.getString(3);
                        String tentk = cursor.getString(1);

                        // chuyển qua màn hình main acctyvity
                        Intent intent = new Intent(MainDangNhap.this,MainActivity.class);
                        // gửi dữ liệu qua main actyvity
                        intent.putExtra("phanq",phanquyen);
                        intent.putExtra("idd",idd);
                        intent.putExtra("email",email);
                        intent.putExtra("tentaikhoan",tentk);

                        startActivity(intent);
                    }
                }
                // thực hiện trả cusor về đầu
                cursor.moveToFirst();
                // đóng
                cursor.close();
            }
        });
    }

    private void Anhxa() {
        edtMatKhau = findViewById(R.id.matkhau);
        edtTaiKhoan = findViewById(R.id.taikhoan);
        btnDangKy = findViewById(R.id.dangky);
        btnDangNhap=findViewById(R.id.dangnhap);
    }
}