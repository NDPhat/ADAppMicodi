package com.example.appcomidi.ViewModel;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import com.example.appcomidi.Model.DB;
import com.example.appcomidi.Model.Giohang;
import com.example.appcomidi.Model.Order;
import com.example.appcomidi.View.MainActivity;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderViewModel {
    public static void insertOrderDetail(Giohang giohang)
    {

        ContentValues contentValues=new ContentValues();
        contentValues.put("Fid",giohang.getIdsp());
        contentValues.put("Name",giohang.getTensp());
        contentValues.put("Price",giohang.getGiasp());
        contentValues.put("Quantity",giohang.getSoluongsp());
        contentValues.put("Image",giohang.getHinhsp());
        contentValues.put("Orderid",giohang.getCartid());
        DBViewModel.sqLiteDatabase.insert("OrderDetail","Fid,Name,Price,Quantity,Image,Orderid",contentValues);

    }
    public static ArrayList<Giohang> getALlOrderDetailbyOrderId(int orderdetailid)
    {

        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from OrderDetail " +
                "where Orderid=? ",new String[]{""+orderdetailid});
        if (cursor.getCount()!=0)
        {
            ArrayList<Giohang>giohangArrayList=new ArrayList<>();
            for (int i=0;i<cursor.getCount();i++)
            {
                cursor.moveToPosition(i);
                int id=cursor.getInt(0);
                int fid=cursor.getInt(1);
                String fname=cursor.getString(2);
                int price=cursor.getInt(3);
                int quan=cursor.getInt(4);
                byte[] image=cursor.getBlob(5);
                int orderid=cursor.getInt(6);
                Giohang giohang=new Giohang(id,fid,fname,price,quan,image,orderid);
                giohangArrayList.add(giohang);

            }
            return giohangArrayList;
        }
        return null;


    }
    public static ArrayList<Giohang> getALlOrderDetail(int orderdetailid)
    {

        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from OrderDetail ",null);
        if (cursor.getCount()!=0)
        {
            ArrayList<Giohang>giohangArrayList=new ArrayList<>();
            for (int i=0;i<cursor.getCount();i++)
            {
                cursor.moveToPosition(i);
                int id=cursor.getInt(0);
                int fid=cursor.getInt(1);
                String fname=cursor.getString(2);
                int price=cursor.getInt(3);
                int quan=cursor.getInt(4);
                byte[] image=cursor.getBlob(5);
                int orderid=cursor.getInt(6);
                Giohang giohang=new Giohang(id,fid,fname,price,quan,image,orderid);
                giohangArrayList.add(giohang);

            }
            return giohangArrayList;
        }
        return null;


    }


}
