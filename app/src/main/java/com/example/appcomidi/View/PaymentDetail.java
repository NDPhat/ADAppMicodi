package com.example.appcomidi.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.appcomidi.R;
import com.paypal.android.sdk.payments.PaymentActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentDetail extends AppCompatActivity {

    private TextView btnhome;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_detail);

        showNotification(0);
        btnhome=findViewById(R.id.buttonHomeDone);
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PaymentDetail.this,HomePageActivity.class);
                startActivity(intent);
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showNotification(Integer id) {

        NotificationManager nm = (NotificationManager) getSystemService(NotificationManager.class);
        NotificationChannel channel = new NotificationChannel("1", "Your Order!!", NotificationManager.IMPORTANCE_DEFAULT);
        nm.createNotificationChannel(channel);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "1")
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle("Your Order!!")
                .setContentText("Cảm ơn bạn đã đặt hàng thành công!!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        android.app.Notification notification = mBuilder.build();
        nm.notify(id, notification);
    }



}