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
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apktest.Database.SQLiteConnect;
import com.example.apktest.MainActivity;
import com.example.apktest.Models.EditSanPham;
import com.example.apktest.SanPham;
import com.example.apktest.R;

import java.util.ArrayList;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    Activity context;
    int resource;
    ArrayList<SanPham> sanPhamArrayList,sanPhamArrayListBackUp, sanPhamArrayListFilter ;
    SQLiteConnect sqLiteConnect;

    public SanPhamAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<SanPham> sanPhamArrayList) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.sanPhamArrayList =this.sanPhamArrayListBackUp = sanPhamArrayList;
    }

    @Override
    public int getCount() {
        return sanPhamArrayList.size();
    }

    public ArrayList<SanPham> getSanPhamArrayList() {
        return sanPhamArrayList;
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

                        String query = "DELETE FROM sanpham WHERE Masp = '" + sp.getMaSP() + "' AND Namesp = '" + sp.getTenSP() + "' AND Giasp = '" + sp.getGiaSP() + "'";
                        sqLiteConnect = new SQLiteConnect(context, context.getString(R.string.db_name), null, 1);
                        sqLiteConnect.queryData(query);
                        ((MainActivity) context).LoadDataSanPham();
//

                    }
                }).setPositiveButton("No", new DialogInterface.OnClickListener() {
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
               
                Bundle data= new Bundle();
                data.putSerializable("sp_value",sp);
                Intent editIntent = new Intent(context, EditSanPham.class);
                editIntent.putExtras(data);
                context.startActivityForResult(editIntent,123);
            }
        });
        return customView;
    }

    @NonNull
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                String query = constraint.toString().trim().toLowerCase();
                if(query.length()<1){
                    sanPhamArrayListFilter=sanPhamArrayListBackUp;

                }else {
                    sanPhamArrayListFilter = new ArrayList<>();
                    for (SanPham sp:sanPhamArrayListBackUp) {
                        if(sp.getMaSP().contains(query) || sp.getTenSP().contains(query)){
                            sanPhamArrayListFilter.add(sp);
                        }
                    }
                }
                filterResults.values = sanPhamArrayListFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
             sanPhamArrayList  = (ArrayList<SanPham>) results.values;
             notifyDataSetChanged();
            }
        };
    }
}
