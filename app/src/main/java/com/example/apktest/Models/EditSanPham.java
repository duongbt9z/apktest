package com.example.apktest.Models;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apktest.Database.SQLiteConnect;
import com.example.apktest.R;
import com.example.apktest.SanPham;

public class EditSanPham extends AppCompatActivity {
    EditText edteditName, edteditMaSP, edteditGiaSP;
    Button btnEditSave, btnEditExit;
    SQLiteConnect sqLiteConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_san_pham);
        edteditName = findViewById(R.id.edteditName);
        edteditMaSP = findViewById(R.id.edteditMaSP);
        edteditGiaSP = findViewById(R.id.edteditGiaSP);
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        SanPham sp = (SanPham) data.get("sp_value");
        edteditMaSP.setText(sp.getMaSP());
        edteditName.setText(sp.getTenSP());
        edteditGiaSP.setText(String.valueOf(sp.getGiaSP()));
        btnEditSave = findViewById(R.id.btnEditSave);
        btnEditSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Masp = edteditMaSP.getText().toString().trim();
                String Namesp = edteditName.getText().toString().trim();
                String sGiasp = edteditGiaSP.getText().toString().trim();
                double Giasp = 0;
                try {
                    Giasp = Double.parseDouble(sGiasp);
                } catch (NumberFormatException e) {
                    System.out.println(sGiasp + " không phải là một số hợp lệ.");
                }
                if (Namesp.equals("") || Masp.equals("")) {
                    Toast.makeText(EditSanPham.this, "nhập lại", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditSanPham.this, "add thành công", Toast.LENGTH_SHORT).show();
                    String query = "UPDATE sanpham SET Masp = '" + Masp + "', Namesp = '" + Namesp + "' WHERE Giasp = '" + Giasp + "'";

                    sqLiteConnect = new SQLiteConnect(getBaseContext(), getString(R.string.db_name), null, 1);
                    sqLiteConnect.queryData(query);
                    setResult(123);
                    finish();
                    Log.d("SAVE", "onClick: SAVE wun" + Namesp);
                }
            }
        });
        btnEditExit = findViewById(R.id.btnEditExit);
        btnEditExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}