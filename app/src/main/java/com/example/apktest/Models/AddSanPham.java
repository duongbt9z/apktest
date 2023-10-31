package com.example.apktest.Models;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apktest.Adapter.SanPhamAdapter;
import com.example.apktest.Database.SQLiteConnect;
import com.example.apktest.R;
import com.example.apktest.SanPham;

import java.util.ArrayList;

public class AddSanPham extends AppCompatActivity {

    EditText edtName, edtMaSP, edtGiaSP;
    Button btnAddimg, btnAdd, btnExit ,btnEdit;
    ImageView imgAddLogoSP;
    ArrayList<SanPham> sanPhamArrayList;
    SanPhamAdapter Sanphamadapter;
    ListView lvSanpham;
    SQLiteConnect sqLiteConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_san_pham);
        edtName = findViewById(R.id.edtName);
        edtMaSP = findViewById(R.id.edtMaSP);
        edtGiaSP = findViewById(R.id.edtGiaSP);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSanPham();
            }
        });
        btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("list", "onItemClick: click");
                finish();
            }
        });

    }

    public void addSanPham() {

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Namesp = edtName.getText().toString().trim();
                String Masp = edtMaSP.getText().toString().trim();
                String sGiasp = edtGiaSP.getText().toString().trim();
                double Giasp = 0;
                try {
                    Giasp = Double.parseDouble(sGiasp);
                } catch (NumberFormatException e) {
                    System.out.println(sGiasp + " không phải là một số hợp lệ.");
                }
                if (Namesp.equals("")|| Masp.equals("") ){
                    Toast.makeText(AddSanPham.this,"nhập lại" ,Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AddSanPham.this,"add thành công",Toast.LENGTH_SHORT).show();
                    sqLiteConnect = new SQLiteConnect(getBaseContext(),getString(R.string.db_name),null,1);
                    String query = "INSERT INTO  sanpham(Masp,Namesp,Giasp) "+
                            "VALUES('"+Masp+"','"+Namesp+"',"+sGiasp+")";
                    sqLiteConnect.queryData(query);
                    setResult(RESULT_OK);
                    finish();
                    Log.d("add", "onClick: add wun");
                }
            }
        });

    }
}