package com.example.appcomidi.ViewModel;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.example.appcomidi.Model.Address;
import com.example.appcomidi.Model.DB;
import com.example.appcomidi.Model.Users;
import com.example.appcomidi.R;
import com.example.appcomidi.View.MainActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserViewModel {
    public static int getUserIdByAccId(int accid)
    {
        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from User " +
                "where Aid=? ",new String[]{""+accid});
        cursor.moveToFirst();
        return cursor.getInt(0);
    }
    public static Users getUserIdById(int uid)
    {

        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from User " +
                "where Id=? ",new String[]{""+uid});
        if (cursor.getCount()!=0)
        {
            cursor.moveToFirst();
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String phone=cursor.getString(2);
            byte[] image=cursor.getBlob(3);
            int rid=cursor.getInt(4);
            int accid=cursor.getInt(5);
            Users users=new Users(id,name,phone,image,rid,accid);
            return users;
        }
        return null;

    }
    public static void updateUserById( Users users)
    {

        ContentValues contentValues=new ContentValues();
        contentValues.put("Name",users.getName());
        contentValues.put("Phone",users.getPhone());
        contentValues.put("Photo",users.getImage());
        DBViewModel.sqLiteDatabase.update("User",contentValues,"Id =?",new String[]{""+users.getId()});

    }
    public static List<Users> getAllUser()
    {
        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from User",null);
        if (cursor.getCount()!=0)
        {
            List<Users> usersList=new ArrayList<>();
            for (int i=0;i<cursor.getCount();i++)
            {
                cursor.moveToPosition(i);
                int id=cursor.getInt(0);
                String name=cursor.getString(1);
                String phone=cursor.getString(2);
                byte[] image=cursor.getBlob(3);
                int rid=cursor.getInt(4);
                int accid=cursor.getInt(5);
                Users users=new Users(id,name,phone,image,rid,accid);
                usersList.add(users);
            }
            return usersList;
        }
        return null;
    }

    public static void updateUserChitieteById(Users users)
    {
        //DBViewModel.sqLiteDatabase= DB.initDatabase(activity,DBViewModel.DBName);
        ContentValues contentValues=new ContentValues();
        contentValues.put("Name",users.getName());
        contentValues.put("Phone",users.getPhone());
        DBViewModel.sqLiteDatabase.update("User",contentValues,"Id =?",new String[]{""+users.getId()});

    }
    public static void insertUser(int acid)
    {
        //DBViewModel.sqLiteDatabase= DB.initDatabase(activity,DBViewModel.DBName);
        ContentValues contentValues=new ContentValues();
        contentValues.put("RoleID",1);
        contentValues.put("Aid",acid);
        DBViewModel.sqLiteDatabase.insert("User","RoleID,Aid",contentValues);

    }
    public static void createUser(int acid,int roleID)
    {
        //DBViewModel.sqLiteDatabase= DB.initDatabase(activity,DBViewModel.DBName);
        ContentValues contentValues=new ContentValues();
        contentValues.put("RoleID",roleID);
        contentValues.put("Aid",acid);
        DBViewModel.sqLiteDatabase.insert("User","RoleID,Aid",contentValues);

    }
    public static void updateStatusUserById( int aid)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("Active","Banned");
        DBViewModel.sqLiteDatabase.update("Account",contentValues,"Id =?",new String[]{""+aid});

    }



}
