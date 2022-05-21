package com.example.appcomidi.ViewModel;

import android.app.Activity;
import android.database.Cursor;

import com.example.appcomidi.View.MainActivity;
import com.example.appcomidi.Model.Category;
import com.example.appcomidi.Model.DB;
import com.example.appcomidi.Model.Food;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewModel {

    public static List<Category> getListCate()
    {
        List<Category> listcate=new ArrayList<>();
        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from Category",null);
        for (int i=0;i<cursor.getCount();i++)
        {
            cursor.moveToPosition(i);
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            byte[] anh=cursor.getBlob(2);
            Category category=new Category(id,name,anh);
            listcate.add(category);
        }

        return  listcate;
    }
    public static Category getCate(int cid)
    {

        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from Category where Id=?",new String[]{""+cid});
        if (cursor.getCount()!=0)
        {
            cursor.moveToFirst();
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            byte[] anh=cursor.getBlob(2);
            Category category=new Category(id,name,anh);
            return category;
        }
        return null;
    }
    public static String[] getNameCate()
    {

        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from Category",null);
        String[] listNamecate = new String[cursor.getCount()];
        for (int i=0;i<cursor.getCount();i++)
        {
            cursor.moveToPosition(i);
            String name=cursor.getString(1);
            listNamecate[i]=name;
        }
        return listNamecate;
    }
    public static int getIDCatebyName(String name)
    {
        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from Category where Name=?",new String[]{name});
        cursor.moveToFirst();
        return cursor.getInt(0);
    }



}
