package com.example.appcomidi.ViewModel;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.appcomidi.Model.Address;
import com.example.appcomidi.Model.DB;
import com.example.appcomidi.Model.Order;
import com.example.appcomidi.View.HomePageActivity;
import com.example.appcomidi.Model.Food;
import com.example.appcomidi.Model.Giohang;
import com.example.appcomidi.View.MainActivity;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CartViewModel {
    public static void addItemtoCart(int quantity, Food food) {
        if (quantity > 0) {
            if (HomePageActivity.giohangArrayList.size() > 0) {
                boolean exit = false;

                for (int i = 0; i < HomePageActivity.giohangArrayList.size(); i++) {
                    if (HomePageActivity.giohangArrayList.get(i).getIdsp() == food.getId()) {
                        HomePageActivity.giohangArrayList.get(i).setSoluongsp(HomePageActivity.giohangArrayList.get(i).getSoluongsp() + quantity);
                        HomePageActivity.giohangArrayList.get(i).setGiasp(HomePageActivity.giohangArrayList.get(i).getGiasp()*HomePageActivity.giohangArrayList.get(i).getSoluongsp());
                        exit = true;
                    }
                }
                if (exit == false) {
                    HomePageActivity.giohangArrayList.add(new Giohang(food.getId(), food.getName(), food.getPrice() * quantity, food.getImage(), quantity));

                }
            } else {

                HomePageActivity.giohangArrayList.add(new Giohang(food.getId(), food.getName(), food.getPrice() * quantity, food.getImage(), quantity));

            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void Updatecart( double tongtien,int orderid) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tinhtrang", "Pending");
        contentValues.put("tongtien", tongtien);
        DBViewModel.sqLiteDatabase.update("SaleOrder", contentValues, "Id=?", new String[]{"" + orderid});

    }

    public static int FindNextIdCart() {

        Cursor cursor = DBViewModel.sqLiteDatabase.rawQuery("select * from SaleOrder "
                , null);
        if (cursor.getCount() != 0) {
            cursor.moveToLast();
            return cursor.getInt(0) + 1;
        }
        return 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void initcart(Activity activity, int userid, int orderidnext) {
        String timenow = String.valueOf(Date.from(Instant.now()));
        String timemoi = tachtime(timenow);
        DBViewModel.sqLiteDatabase = DB.initDatabase(activity, DBViewModel.DBName);
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", orderidnext);
        contentValues.put("Userid", userid);
        contentValues.put("ngay", timemoi);
        contentValues.put("tongtien", 0);
        contentValues.put("tinhtrang", "Pending");
        DBViewModel.sqLiteDatabase.insert("SaleOrder", "Id,Userid,ngay,tinhtrang,tongtien", contentValues);

    }

    public static Order getCartStillbuy( int userid) {


        Cursor cursor = DBViewModel.sqLiteDatabase.rawQuery("select * from SaleOrder " +
                "where Userid=? and tinhtrang=? and tongtien=0 ", new String[]{"" + userid, "Pending"});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            Order order = new Order(cursor.getInt(0), cursor.getInt(1), cursor.getString(2));
            return order;
        }
        return null;
    }

    public static String tachtime(String time) {
        int sum = 0;
        String moi = "";
        for (int i = 0; i < time.length(); i++) {
            char kq = time.charAt(i);
            moi = moi + kq;
            if (kq == ' ') {
                sum++;
                if (sum == 3) {
                    break;
                }
            }
        }
        return moi;
    }

    public static List<Order> getListSaleOrderbyUserid(int userid) {
        Cursor cursor = DBViewModel.sqLiteDatabase.rawQuery("select * from SaleOrder where Userid=? and tongtien >0", new String[]{"" + userid});

        if (cursor.getCount() != 0) {
            List<Order> orderList = new ArrayList<>();
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                int id = cursor.getInt(0);
                int uid = cursor.getInt(1);
                String ngay = cursor.getString(2);
                String tinhtrang = cursor.getString(3);
                double tongtien = cursor.getDouble(4);
                Order order = new Order(id, uid, ngay, tinhtrang, tongtien);
                orderList.add(order);
            }
            return orderList;
        }
        return null;

    }
    public static List<Order> getAllSaleOrder() {
        Cursor cursor = DBViewModel.sqLiteDatabase.rawQuery("select * from SaleOrder ",null);
        if (cursor.getCount() != 0) {
            List<Order> orderList = new ArrayList<>();
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                int id = cursor.getInt(0);
                int uid = cursor.getInt(1);
                String ngay = cursor.getString(2);
                String tinhtrang = cursor.getString(3);
                double tongtien = cursor.getDouble(4);
                Order order = new Order(id, uid, ngay, tinhtrang, tongtien);
                orderList.add(order);
            }
            return orderList;
        }
        return null;

    }

    public static void cancelSaleOrder(int orderid) {
        DBViewModel.sqLiteDatabase.delete("SaleOrder", "id=?", new String[]{"" + orderid});
    }

    public static List<Order> getAllListSaleOrder() {
        Cursor cursor = DBViewModel.sqLiteDatabase.rawQuery("select * from SaleOrder where tinhtrang=? and tongtien>0", new String[]{"Pending"});

        if (cursor.getCount() != 0) {
            List<Order> orderList = new ArrayList<>();
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                int id = cursor.getInt(0);
                int uid = cursor.getInt(1);
                String ngay = cursor.getString(2);
                String tinhtrang = cursor.getString(3);
                double tongtien = cursor.getDouble(4);
                Order order = new Order(id, uid, ngay, tinhtrang, tongtien);
                orderList.add(order);
            }
            return orderList;
        }
        return null;

    }

    public static void updateSaleOrder( int orderid,String tinhtrang) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("tinhtrang", tinhtrang);
        DBViewModel.sqLiteDatabase.update("SaleOrder", contentValues, "id=?", new String[]{"" + orderid});
    }

    public static Order getCartbyId(int orderid) {

        Cursor cursor = DBViewModel.sqLiteDatabase.rawQuery("select * from SaleOrder where Id=?", new String[]{"" + orderid});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            int id = cursor.getInt(0);
            int uid = cursor.getInt(1);
            String ngay = cursor.getString(2);
            String tinhtrang = cursor.getString(3);
            double tongtien = cursor.getDouble(4);
            Order order = new Order(id, uid, ngay, tinhtrang, tongtien);
            return order;
        }
        return null;
    }


}
