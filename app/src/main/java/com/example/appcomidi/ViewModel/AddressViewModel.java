package com.example.appcomidi.ViewModel;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.appcomidi.Model.Address;
import com.example.appcomidi.Model.DB;
import com.example.appcomidi.View.MainActivity;

public class AddressViewModel {
    public static Address getAddressbyuid(int uid)
    {

        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from Address " +
                "where userid=? ",new String[]{""+uid});
        if (cursor.getCount()!=0)
        {
            cursor.moveToFirst();
            Address address=new Address(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4));
            return address;
        }
        return null;


    }
    public static void updateAddbyId(Address address)
    {

        ContentValues contentValues=new ContentValues();
        contentValues.put("sonha",address.getHomenumber());
        contentValues.put("duong",address.getStreet());
        contentValues.put("thanhpho",address.getCity());
        DBViewModel.sqLiteDatabase.update("Address",contentValues,"Id =?",new String[]{""+address.getId()});

    }

    public static void insertAddbyId(Address address)
    {

        ContentValues contentValues=new ContentValues();
        contentValues.put("sonha",address.getHomenumber());
        contentValues.put("duong",address.getStreet());
        contentValues.put("thanhpho",address.getCity());
        contentValues.put("userid",MainActivity.user.getId());
        DBViewModel.sqLiteDatabase.insert("Address","sonha,duong,thanhpho,userid",contentValues);

    }
    public static int FindNextId()
    {
        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from Address ",null);
        cursor.moveToLast();
        return (cursor.getInt(0)+1);
    }

    public static void addnewAddbyId(int addid,int usid)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("Id",addid);
        contentValues.put("userid",usid);
        DBViewModel.sqLiteDatabase.insert("Address","Id,userid",contentValues);

    }
}
