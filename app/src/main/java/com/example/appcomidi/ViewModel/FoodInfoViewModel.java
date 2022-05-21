package com.example.appcomidi.ViewModel;

import android.app.Activity;
import android.database.Cursor;

import com.example.appcomidi.View.MainActivity;
import com.example.appcomidi.Model.DB;
import com.example.appcomidi.Model.FoodInfo;

import java.util.ArrayList;
import java.util.List;

public class FoodInfoViewModel {

    public static FoodInfo getFoodinfoorderbyid (int fid)
    {
        FoodInfo foodInfo=new FoodInfo();
        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from FoodInfo where Id=?",new String[] {fid+""});
        if (cursor.getCount()!=0)
        {
            cursor.moveToFirst();
            int id=cursor.getInt(0);
            double sosao=cursor.getDouble(1);
            String diachi=cursor.getString(2);
            int sokm=cursor.getInt(3);
            int thoigian=cursor.getInt(4);
            foodInfo=new FoodInfo(id,diachi,sosao,sokm,thoigian);
            return foodInfo;
        }
        return null;

    }
    public static void updateStarfoodinfo (int fid)
    {


    }
    public static Integer[] getIdInfo()
    {

        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from FoodInfo ",null);
        Integer[] listid=new Integer[cursor.getCount()];
        for (int i=0;i<cursor.getCount();i++)
        {
            cursor.moveToPosition(i);
            int id=cursor.getInt(0);
            listid[i]=id;
        }
        return listid;




    }
}
