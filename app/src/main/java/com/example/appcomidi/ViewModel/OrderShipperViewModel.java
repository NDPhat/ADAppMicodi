package com.example.appcomidi.ViewModel;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.appcomidi.Model.DB;
import com.example.appcomidi.Model.Giohang;
import com.example.appcomidi.Model.Order;
import com.example.appcomidi.Model.OrderShipper;

import java.util.ArrayList;

public class OrderShipperViewModel {
    public static void insertShipperOrderDetail(OrderShipper orderShipper)
    {

        ContentValues contentValues=new ContentValues();
        contentValues.put("ShipperID",orderShipper.getSid());
        contentValues.put("Userid",orderShipper.getUid());
        contentValues.put("Saleorderid",orderShipper.getOrderid());
        contentValues.put("tongtien",orderShipper.getTongtien());
        contentValues.put("nguoimua",orderShipper.getNguoimua());
        contentValues.put("tinhtrang",orderShipper.getTinhtrang());
        DBViewModel.sqLiteDatabase.insert("ShipperOrderDetail","ShipperID,Userid,Saleorderid,tongtien,nguoimua,tinhtrang",contentValues);

    }
    public static ArrayList<OrderShipper> getALlShipperOrderDetailbySId(int shipperid)
    {

        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from ShipperOrderDetail " +
                "where ShipperID=? ",new String[]{""+shipperid});
        if (cursor.getCount()!=0)
        {
            ArrayList<OrderShipper>shipperorderArrayList=new ArrayList<>();
            for (int i=0;i<cursor.getCount();i++)
            {
                cursor.moveToPosition(i);
                int id=cursor.getInt(0);
                int sid=cursor.getInt(1);
                int uid=cursor.getInt(2);
                int orderid=cursor.getInt(3);
                double tongtien=cursor.getDouble(4);
                String nguoimua=cursor.getString(5);
                String tinhtrang=cursor.getString(6);
                OrderShipper orderShipper=new OrderShipper(id,sid,uid,orderid,tongtien,nguoimua,tinhtrang);
                shipperorderArrayList.add(orderShipper);

            }
            return shipperorderArrayList;
        }
        return null;
    }

    public static boolean getStatusShipper(Activity activity, int shipperid)
    {
        DBViewModel.sqLiteDatabase= DB.initDatabase(activity,DBViewModel.DBName);
        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from ShipperOrderDetail " +
                "where ShipperID=? and tinhtrang=?",new String[]{""+shipperid,"Delivery"});
        if (cursor.getCount()==0)
        {
           return true;
        }
        return false;
    }
    public static  void updateTinhtrang(Activity activity, int orderid,String tinhtrang)
    {
        DBViewModel.sqLiteDatabase= DB.initDatabase(activity,DBViewModel.DBName);
        ContentValues contentValues=new ContentValues();
        contentValues.put("tinhtrang",tinhtrang);
        DBViewModel.sqLiteDatabase.update("ShipperOrderDetail",contentValues,"Saleorderid=?",new String[]{""+orderid});

    }

}
