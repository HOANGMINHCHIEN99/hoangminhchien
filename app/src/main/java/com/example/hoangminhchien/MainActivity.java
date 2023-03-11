package com.example.hoangminhchien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.app.Notification;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ActionMenuView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.window.OnBackInvokedDispatcher;

import com.example.hoangminhchien.adapter.adapterchuyenmuc;
import com.example.hoangminhchien.adapter.adapterthongtin;
import com.example.hoangminhchien.adapter.adaptertruyen;
import com.example.hoangminhchien.database.databasedoctruyen;
import com.example.hoangminhchien.model.TaiKhoan;
import com.example.hoangminhchien.model.Truyen;
import com.example.hoangminhchien.model.chuyenmuc;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView, listViewNew, listViewThongTin;
    DrawerLayout drawerLayout;

    String email;
    String tentaikhoan;

    ArrayList<Truyen>  TruyenArraylist;
    adaptertruyen adaptertruyen;
    ArrayList<chuyenmuc> chuyenmucArrayList;
    ArrayList<TaiKhoan> taiKhoanArrayList;
    databasedoctruyen databasedoctruyen;

    adapterchuyenmuc adapterchuyenmuc;
    adapterthongtin adapterthongtin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // lấy dữ liệu ở mảng đăng nhập gửi
        Intent intentpq=getIntent();
        int i = intentpq.getIntExtra("phang",0);
        int idd= intentpq.getIntExtra("idd",0);
        tentaikhoan = intentpq.getStringExtra("tentaikhoan");
        email = intentpq.getStringExtra("email");

        databasedoctruyen = new databasedoctruyen(this);



        Anhxa();

        Actionbar();

        ActionViewFlipper();

        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MainActivity.this,MainNoiDung.class);
            String tent = TruyenArraylist.get(position).getTenTruyen();
            String noidung = TruyenArraylist.get(position).getNoiDung();
            intent.putExtra("tentruyen",tent);
            intent.putExtra("noidung",noidung);
            startActivity(intent);
            }
        });

        // bắt click item cho list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    if (i == 2) {

                    } else {
                        Toast.makeText(MainActivity.this, "Bạn chưa được cấp quyền", Toast.LENGTH_SHORT).show();
                        Log.e("Đăng bài :", " Bạn chưa được cấp quyền");

                    }
                } else if (position == 1) {
                } else if (position == 2) {
                    finish();
                }
            }
        });
    }
    // thanh actionbar vs toolbar
    private void Actionbar() {
        //hàm hỗ trợ toolbar
        setSupportActionBar(toolbar);

        // sét nút cho acctionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //tạo icon toolbar
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        //bắt sự kiện chọn
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }


    // chạy quảng cáo với viewFliper
    private void ActionViewFlipper() {
// mảng chứa ảnh quảng cáo
        ArrayList<String> mangquangcao = new ArrayList<>();
        //add ảnh đầu vào
        mangquangcao.add("https://toplist.vn/images/800px/cau-chuyen-co-tich-viet-nam-hay-nhat-43873.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/cay-tre-tram-dot-43889.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/su-tich-da-trang-xe-cat-bien-dong-43881.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/su-tich-cay-vu-sua-43890.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/tich-chu-43894.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/su-tich-bong-hoa-cuc-trang-43898.jpg");
        mangquangcao.add("https://file.nhasachmienphi.com/jpg/nhasachmienphi-truyen-tranh-doremon-333102-0.jpg");


        // thực hiện vòng lặp for gán ảnh vào image view rồi từ imageview tải lên app
        for (int i=0;i<mangquangcao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());

            // sử dụng thư viện picasso
            Picasso.get().load(mangquangcao.get(i)).into(imageView);

            //phương thức chỉnh hình vừa khung quảng cáo
            viewFlipper.addView(imageView);
        }
        //thiết lập tự động chạy cho viewfilip chạy trong 4s
        viewFlipper.setFlipInterval(4000);
        //chạy viewfilip tự động
        viewFlipper.setAutoStart(true);

        //gọi animation cho vào và ra
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.side_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
    }



    //Phương thức ánh xạ
    private void Anhxa() {
        toolbar = findViewById(R.id.toobarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewfliper);
        listViewNew = findViewById(R.id.listviewNew);
        listView = findViewById(R.id.listviewmanhinhchinh);
        listViewThongTin = findViewById(R.id.listviewthongtin);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerlayout);

        TruyenArraylist = new ArrayList<>();
        Cursor cursor1 = databasedoctruyen.getData();
        while (cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung= cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);
            TruyenArraylist.add(new Truyen(id,tentruyen,noidung,anh,id_tk));
            adaptertruyen = new adaptertruyen(getApplicationContext(),TruyenArraylist);
            listViewNew.setAdapter(adaptertruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();

        // thông tin
        taiKhoanArrayList = new ArrayList<>();
        taiKhoanArrayList.add(new TaiKhoan(tentaikhoan,email));

        adapterthongtin = new adapterthongtin(this,R.layout.navigation_thongtin,taiKhoanArrayList );
        listViewThongTin.setAdapter(adapterthongtin);


        // chuyên mục
        chuyenmucArrayList = new ArrayList<>();
        chuyenmucArrayList.add(new chuyenmuc("Đăng bài",R.drawable.baseline_post_add_24));
        chuyenmucArrayList.add(new chuyenmuc("Thông tin",R.drawable.baseline_tag_faces_24));
        chuyenmucArrayList.add(new chuyenmuc("Đăng xuất",R.drawable.baseline_logout_24));


        adapterchuyenmuc = new adapterchuyenmuc(this,R.layout.chuyenmuc,chuyenmucArrayList);

        listView.setAdapter(adapterchuyenmuc);
    }
    // nạp một menu tìm kiếm vào acctionbar
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return  true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // nêys chon vào icon tìm kiếm sẽ chuyển tới trang tìm kiếm
        switch (item.getItemId()){
            case R.id.menu1:
                Intent intent = new Intent(MainActivity.this,MainTimKiem.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}