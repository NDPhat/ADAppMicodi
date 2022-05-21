package com.example.appcomidi.ViewModel;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.appcomidi.Model.DB;
import com.example.appcomidi.Model.Rating;
import com.example.appcomidi.View.MainActivity;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class RatingViewModel {
    public static void insertReView( Rating rating)
    {

        ContentValues contentValues=new ContentValues();
        contentValues.put("userid",rating.getUid());
        contentValues.put("Email",rating.getEmail());
        contentValues.put("fid",rating.getFid());
        contentValues.put("binhluan",rating.getCmt());
        contentValues.put("sosao",rating.getSosao());
        DBViewModel.sqLiteDatabase.insert("Rating","userid,Email,fid,binhluan,sosao",contentValues);

    }
    public static List<Rating> getAllReviewbyFid(int foodid)
    {
        List<Rating> ratingList=new ArrayList<>();
        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from Rating r inner join User u on r.userid=u.id where r.fid=? ",new String[]{""+foodid});
        for (int i=0;i<cursor.getCount();i++) {
            cursor.moveToPosition(i);
            int rid=cursor.getInt(0);
            int uid=cursor.getInt(1);
            String email=cursor.getString(2);
            String binhluan=cursor.getString(4);
            int sosao=cursor.getInt(5);
            byte[] photo=cursor.getBlob(9);
            Rating rating=new Rating(rid,email,uid,sosao,binhluan,photo);
            ratingList.add(rating);
        }
        return ratingList;

    }
    public static List<Rating> getAllReview()
    {

        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from Rating r inner join User u on r.userid=u.id ",null);
        if (cursor.getCount()!=0)
        {
            List<Rating> ratingList=new ArrayList<>();
            for (int i=0;i<cursor.getCount();i++) {
                cursor.moveToPosition(i);
                int rid=cursor.getInt(0);
                int uid=cursor.getInt(1);
                String email=cursor.getString(2);
                String binhluan=cursor.getString(4);
                int sosao=cursor.getInt(5);
                byte[] photo=cursor.getBlob(9);
                Rating rating=new Rating(rid,email,uid,sosao,binhluan,photo);
                ratingList.add(rating);
            }
            return ratingList;
        }
        return null;


    }
    public static int tongRVbyFid(int foodid)
    {
        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select * from Rating  where fid=? ",new String[]{""+foodid});
        if (cursor.getCount()!=0)
        {
            return cursor.getCount();
        }
        return 0;
    }

    public static float tongStarbyFid(int foodid)
    {
        Cursor cursor=DBViewModel.sqLiteDatabase.rawQuery("select avg(sosao) from Rating  where fid=? ",new String[]{""+foodid});
        if (cursor.getCount()!=0)
        {
            return cursor.getCount();
        }
        return 0;
    }
}
