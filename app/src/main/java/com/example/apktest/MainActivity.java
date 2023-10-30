package com.example.apktest;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apktest.Adapter.SanPhamAdapter;
import com.example.apktest.Database.SQLiteConnect;
import com.example.apktest.Models.AddSanPham;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAdd_sp,btnShow;

    ListView lvSanpham;
    SanPhamAdapter Sanphamadapter;
    ArrayList<SanPham> sanPhamArrayList;
    SQLiteConnect sqLiteConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvSanpham = findViewById(R.id.lvSanPham);
        sanPhamArrayList = new ArrayList<>();
        sqLiteConnect = new SQLiteConnect(getBaseContext(),getString(R.string.db_name),null,1);
        String query = "CREATE TABLE IF NOT EXISTS sanpham("+
                "Masp TEXT not null,"+
                "Namesp TEXT not null,"+
                "Giasp REAL)";
        sqLiteConnect.queryData(query);
        query = "INSERT INTO  sanpham(Masp,Namesp,Giasp) "+
                "VALUES('SP001','COCA',10000)";
        sqLiteConnect.queryData(query);
        Sanphamadapter = new SanPhamAdapter(MainActivity.this, R.layout.lv_sanpham,sanPhamArrayList);
        lvSanpham.setAdapter(Sanphamadapter);
        LoadDataSanPham();
//        lvSanpham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),sanPhamArrayList.get(position).getTenSP(), Toast.LENGTH_SHORT).show();
//                Log.d("list", "onItemClick: click");
//                SanPham sp =sanPhamArrayList.get(position);
//                Bundle data = new Bundle();
//                data.putSerializable("sp_value",sp);
//                Intent showsp = new Intent(MainActivity.this, ShowSanPham.class);
//                showsp.putExtras(data);
//                startActivity(showsp);
//            }
//        });
        btnAdd_sp = findViewById(R.id.btnAdd_sp);
        btnAdd_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAdd = new Intent(MainActivity.this, AddSanPham.class);
//                startActivity(intentAdd);
                intentAddlauncher.launch(intentAdd);
            }
        });

    }
    public  void  LoadDataSanPham(){
        String query = "SELECT * FROM sanpham";
        Cursor data = sqLiteConnect.getData(query);
        sanPhamArrayList.clear();
        while (data.moveToNext()){
            try {
                String Namesp = data.getString(0);
                String Masp = data.getString(1);
                Double Giasp = Double.valueOf(data.getString(2));
                SanPham sp = new SanPham(Masp,Namesp,Giasp);
                sanPhamArrayList.add(sp);
                Log.d("show", "LoadDataSanPham: show try");
            }catch (Exception e){
                Toast.makeText(this, "Error loading data: " + e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }
        Sanphamadapter.notifyDataSetChanged();
        Log.d("show", "LoadDataSanPham: show");
    }
    ActivityResultLauncher intentAddlauncher= registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode()==RESULT_OK ){
                LoadDataSanPham();
            }
        }
    });
}