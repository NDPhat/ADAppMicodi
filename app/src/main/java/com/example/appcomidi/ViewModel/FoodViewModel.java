package com.example.appcomidi.ViewModel;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.appcomidi.Model.Address;
import com.example.appcomidi.Model.DB;
import com.example.appcomidi.Model.Food;
import com.example.appcomidi.View.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class FoodViewModel {
    public static void insertFood( Food food)
    {

        ContentValues contentValues=new ContentValues();
        contentValues.put("Id",food.getId());
        contentValues.put("Name",food.getName());
        contentValues.put("Price",food.getPrice());
        contentValues.put("IdCate",food.getIdcate());
        contentValues.put("Image",food.getImage());
        contentValues.put("Soluong",food.getSoluong());
        contentValues.put("Tinhtrang",food.getTinhtrang());
        contentValues.put("Idinfo",food.getIdfoodinfo());
        DBViewModel.sqLiteDatabase.insert("Food","Id,Name,Price,IdCate,Image,Soluong,Tinhtrang,Idinfo",contentValues);

    }
    public static void updateFood(Food food)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("Id",food.getId());
        contentValues.put("Name",food.getName());
        contentValues.put("Price",food.getPrice());
        contentValues.put("IdCate",food.getIdcate());
        contentValues.put("Image",food.getImage());
        contentValues.put("Soluong",food.getSoluong());
        contentValues.put("Tinhtrang",food.getTinhtrang());
        contentValues.put("Idinfo",food.getIdfoodinfo());
        DBViewModel.sqLiteDatabase.update("Food",contentValues,"Id=?",new String[]{""+food.getId()});

    }
    public static List<Food> getAllListFood()
    {
        List<Food> foodlist=new ArrayList<>();
        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from Food ",null);
        for (int i=0;i<cursor.getCount();i++)
        {
            cursor.moveToPosition(i);
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            int price=cursor.getInt(2);
            int idcate=cursor.getInt(3);
            byte[] anh=cursor.getBlob(4);
            int num=cursor.getInt(5);
            String tt=cursor.getString(6);
            int idfoodinfo=cursor.getInt(7);
            Food food=new Food(id,name,price,idcate,anh,num,tt,idfoodinfo);
            foodlist.add(food);
        }
        return foodlist;
    }
    public static int FindNextId()
    {

        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from Food ",null);
        cursor.moveToLast();
        return (cursor.getInt(0)+1);
    }

    public static List<Food> getListFoodorderbyidcate(int idcategory)
    {
        List<Food> foodlist=new ArrayList<>();

        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from Food where IdCate=?",new String[] {idcategory+""});
        for (int i=0;i<cursor.getCount();i++)
        {
            cursor.moveToPosition(i);
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            int price=cursor.getInt(2);
            int idcate=cursor.getInt(3);
            byte[] anh=cursor.getBlob(4);
            int num=cursor.getInt(5);
            String tt=cursor.getString(6);
            int idfoodinfo=cursor.getInt(7);
            Food food=new Food(id,name,price,idcate,anh,num,tt,idfoodinfo);
            foodlist.add(food);
        }
        return foodlist;
    }
    public static List<Food> getTopListFood()
    {
        List<Food> foodlist=new ArrayList<>();

        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from Food f inner join FoodInfo fi on f.Idinfo=fi.id where fi.sosao >= 4.5",null);
        for (int i=0;i<cursor.getCount();i++)
        {
            cursor.moveToPosition(i);
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            int price=cursor.getInt(2);
            int idcate=cursor.getInt(3);
            byte[] anh=cursor.getBlob(4);
            int num=cursor.getInt(5);
            String tt=cursor.getString(6);
            int idfoodinfo=cursor.getInt(7);
            Food food=new Food(id,name,price,idcate,anh,num,tt,idfoodinfo);
            foodlist.add(food);
        }
        return foodlist;
    }
    public static Food getFoobById(int fid)
    {


        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from Food where Id=?",new String[]{""+fid});
        if (cursor.getCount()!=0)
        {

            cursor.moveToFirst();
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            int price=cursor.getInt(2);
            int idcate=cursor.getInt(3);
            byte[] anh=cursor.getBlob(4);
            int num=cursor.getInt(5);
            String tt=cursor.getString(6);
            int idfoodinfo=cursor.getInt(7);
            Food food=new Food(id,name,price,idcate,anh,num,tt,idfoodinfo);
            return food;
        }
        return null;
    }
}
