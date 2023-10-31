package com.example.apktest.Models;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apktest.R;
import com.example.apktest.SanPham;

public class ShowSanPham extends AppCompatActivity {

    TextView tvShowMa,tvShowName,tvShowGiaSP;
    Button btnExit;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_san_pham);
        tvShowMa= findViewById(R.id.tvShowMa);
        tvShowName= findViewById(R.id.tvShowName);
        tvShowGiaSP= findViewById(R.id.tvShowGiaSP);
        btnExit= findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        SanPham sp = (SanPham) data.get("sp_value");
        tvShowMa.setText("Mã SP: " + sp.getMaSP());
        tvShowName.setText("Tên SP:" + sp.getTenSP());
        tvShowGiaSP.setText("Giá SP: " + sp.getGiaSP() + " VNĐ");
    }
}