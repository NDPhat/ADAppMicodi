package com.example.appcomidi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    String time="Sat Apr 08";
    @Test
    public void tachtime()
    {
        int sum=0;
        String moi="";
        for (int i=0;i<time.length();i++)
        {
            char kq=time.charAt(i);
            moi=moi+kq;
            System.out.println(kq);
            if (kq==' ')
            {
                System.out.println(moi);
                sum++;
                if (sum==2)
                {
                    break;
                }

            }
        }
        System.out.println("Moi "+moi);
    }

}