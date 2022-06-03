package com.example.appcomidi.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.appcomidi.Fragment.User.CartFragment;
import com.example.appcomidi.Model.Config;
import com.example.appcomidi.Model.Food;
import com.example.appcomidi.Model.Order;
import com.example.appcomidi.R;
import com.example.appcomidi.ViewModel.CartViewModel;
import com.example.appcomidi.ViewModel.FoodInfoViewModel;
import com.example.appcomidi.ViewModel.OrderViewModel;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalPaymentDetails;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.math.BigDecimal;

public class PaymentmethodAcitivity extends AppCompatActivity {

    public static final int PAYPALCODE=7171;
    private static PayPalConfiguration config=new PayPalConfiguration().
            environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK).clientId(Config.PAYPALID);


    private TextView totalamount;
    private Button btnpaynow,home;
    private double tongtien;
    private View viewDT;
    private int orderid;
    private String money="";


    @Override
    protected void onDestroy() {
        stopService(new Intent(this,PayPalService.class));
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentmethod_acitivity);
        Intent intent=new Intent(this,PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        startService(intent);
        Anhxa();
        PaynowClick();
    }
    public void Anhxa()
    {
        Intent intent=getIntent();
        tongtien=intent.getDoubleExtra("OrderAmount",1.0);
        orderid=intent.getIntExtra("OrderId",1);
        totalamount=findViewById(R.id.totalamount);
        btnpaynow=findViewById(R.id.buttonPaynow);
        totalamount.setText(""+tongtien);
    }
    public void processPayment()
    {
        money= String.valueOf(tongtien);
        PayPalPayment payPalPayment=new PayPalPayment(new BigDecimal(money),"USD","Tổng tiền thanh toán :",
                PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent=new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
        startActivityForResult(intent,PAYPALCODE);
    }
    public void PaynowClick()
    {
        btnpaynow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                CartViewModel.Updatecart(tongtien,orderid);

                for (int i = 0; i < HomePageActivity.giohangArrayList.size(); i++) {
                    HomePageActivity.giohangArrayList.get(i).setCartid(orderid);
                    OrderViewModel.insertOrderDetail(HomePageActivity.giohangArrayList.get(i));
                }
                //showNotification(0);
                HomePageActivity.giohangArrayList.clear();
                CartFragment.Sumprice();
                processPayment();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYPALCODE) {
            if (resultCode == RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null) {
                            Intent intent=new Intent(PaymentmethodAcitivity.this,PaymentDetail.class);
                            startActivity(intent);
                }

            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Cancel", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Toast.makeText(this, "Invalid", Toast.LENGTH_LONG).show();
        }
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