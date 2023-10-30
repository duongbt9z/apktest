package com.example.apktest.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apktest.Models.EditSanPham;
import com.example.apktest.SanPham;
import com.example.apktest.R;

import java.util.ArrayList;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    Activity context;
    int resource;
    ArrayList<SanPham> sanPhamArrayList;

    public SanPhamAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<SanPham> sanPhamArrayList) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.sanPhamArrayList = sanPhamArrayList;
    }

    @Override
    public int getCount() {
        return sanPhamArrayList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View customView = layoutInflater.inflate(resource, null);
        TextView tvID = customView.findViewById(R.id.tvMa);
        TextView tvName = customView.findViewById(R.id.tvName);
        TextView tvPrice = customView.findViewById(R.id.tvPrice);
        Button btnEdit = customView.findViewById(R.id.btnEdit);
        Button btnDelete = customView.findViewById(R.id.btnDelete);
        SanPham sp = this.sanPhamArrayList.get(position);
        tvID.setText("Mã SP: " + sp.getMaSP());
        tvName.setText("Tên SP:" + sp.getTenSP());
        tvPrice.setText("Giá SP: " + sp.getGiaSP() + " VNĐ");


      //  detele sp
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa Sản Phẩm");
                builder.setMessage("Bạn có thật sự muốn xóa sản phẩm");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sanPhamArrayList.remove(sp);
                        notifyDataSetChanged();

                    }
                });
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "onclick edit", Toast.LENGTH_SHORT).show();
                Bundle data= new Bundle();
                data.putSerializable("sp_value",sp);
                Intent editIntent = new Intent(context, EditSanPham.class);
                editIntent.putExtras(data);
//                context.startActivity(editIntent,123);
//                String Namesp = edtName.getText().toString().trim();
//                Intent showsp = new Intent(context, ShowSanPham.class);
//                context.startActivity(showsp);
//
//                tvShowMa.setText(sp.getMaSP());
//                tvShowName.setText(sp.getTenSP());
//                tvShowGiaSP.setText((int) sp.getGiaSP());
//                imgShowLogoSP.setImageURI(Uri.parse(sp.getLogoSP()));
            }


        });
        return customView;
    }
}
