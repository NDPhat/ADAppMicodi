package com.example.appcomidi.ViewModel;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.widget.Toast;

import com.example.appcomidi.Model.Account;
import com.example.appcomidi.Model.DB;
import com.example.appcomidi.View.MainActivity;

public class AccountViewModel {

    public static int CheckUserandPassword(String email, String password)
    {
        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from Account " +
                "where Email=? and Password=? and Active=?",new String[]{email,password,"Active"});
        cursor.moveToFirst();
        if (cursor.getCount()!=0)
        {
            return cursor.getInt(0) ;
        }
        return 0;
    }
    public static void updateActiveAccount(Activity activity, String status,Account acc)
    {
            DBViewModel.sqLiteDatabase= DB.initDatabase(activity,DBViewModel.DBName);
            ContentValues contentValues=new ContentValues();
            contentValues.put("Active",status);
            DBViewModel.sqLiteDatabase.update("Account",contentValues,"Id=?",new String[]{""+acc.getId()});
            Toast.makeText(activity,"Update thành công!! ",Toast.LENGTH_LONG).show();
    }

    public static void updateAccount(Activity activity, Account acc)
    {
        if (!acc.isValidEmail() )
        {
            Toast.makeText(activity,"Không đúng định dạng email!!",Toast.LENGTH_LONG).show();
        }
        else if (!acc.isValidPassword())
        {
            Toast.makeText(activity,"Mật khẩu cần dài hơn 6 ký tự!!",Toast.LENGTH_LONG).show();
        }
        else if (!acc.getPass().equals(acc.getRepass())){
            Toast.makeText(activity,"Repass không khớp với Password!! ",Toast.LENGTH_LONG).show();
        }
        else {
            //DBViewModel.sqLiteDatabase= DB.initDatabase(activity,DBViewModel.DBName);
            ContentValues contentValues=new ContentValues();
            contentValues.put("Email",acc.getEmail());
            contentValues.put("Password",acc.getPass());
            DBViewModel.sqLiteDatabase.update("Account",contentValues,"Id=?",new String[]{""+acc.getId()});
            Toast.makeText(activity,"Update thành công!! ",Toast.LENGTH_LONG).show();
        }
    }
    public static Account getDataAccountbyAccid(int aid)
    {
        //DBViewModel.sqLiteDatabase= DB.initDatabase(activity,DBViewModel.DBName);
        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from Account " +
                "where Id=?",new String[]{""+aid});
        if (cursor.getCount()!=0)
        {
            cursor.moveToFirst();
            String email=cursor.getString(1);
            String pass=cursor.getString(2);
            Account account=new Account(email,pass);
            return account;
        }

            return null;

    }
    public static boolean CheckDataRegister(Activity activity, Account acc)
    {
        if (!acc.isValidEmail() )
        {
            Toast.makeText(activity,"Không đúng định dạng email!!",Toast.LENGTH_LONG).show();
            return false;
        }
        else if (!acc.isValidPassword())
        {
            Toast.makeText(activity,"Mật khẩu cần dài hơn 6 ký tự!!",Toast.LENGTH_LONG).show();
            return false;
        }
        else if (!acc.getRepass().equals(acc.getPass())){
            Toast.makeText(activity,"Repass không khớp với Password!! ",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    public static void RegisterAccount( Account acc)
    {
            //DBViewModel.sqLiteDatabase= DB.initDatabase(activity,DBViewModel.DBName);
            ContentValues contentValues=new ContentValues();
            contentValues.put("Email",acc.getEmail());
            contentValues.put("Password",acc.getPass());
            contentValues.put("Active","Active");
            DBViewModel.sqLiteDatabase.insert("Account","Email,Password,Active",contentValues);
            int id=CheckUserandPassword(acc.getEmail(),acc.getPass());
            System.out.println(""+id);
            acc.setId(id);
            UserViewModel.insertUser(CheckUserandPassword(acc.getEmail(),acc.getPass()));

    }
}
