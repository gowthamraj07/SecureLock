package com.gowtham.securelock;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private HomeKeyLocker mHomeKeyLocker;

    private static final String PASS_KEY = "1111";
    private static final String MSG_KEY = "22";
    private static final String PHONE_NOS[] = {"9003443254"};

    private static String enteredKey = "";

    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHomeKeyLocker = new HomeKeyLocker();

        initBtns();

    }

    private void initBtns() {
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //mHomeKeyLocker.lock(this);
        hideSystemUIBars();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mHomeKeyLocker.unlock();
        mHomeKeyLocker = null;
    }

    private void hideSystemUIBars() {


        int mUIFlag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

        getWindow().getDecorView().setSystemUiVisibility(mUIFlag);
    }

    @Override
    public void onClick(View v) {
        if(v instanceof Button) {
            Button b = (Button)v;
            enteredKey += b.getText();

            if(MSG_KEY.equals(enteredKey)) {
                Toast.makeText(this, "Sending Message...", Toast.LENGTH_SHORT).show();
                sendSMS();
            } else if(PASS_KEY.equals(enteredKey)) {
                finish();
                System.exit(0);
            } else if(enteredKey.length() >= PASS_KEY.length()) {
                Toast.makeText(this, "Invalid Passkey", Toast.LENGTH_SHORT).show();
                enteredKey = "";
            }
        }
    }

    private void sendSMS(){
        SmsManager sms = SmsManager.getDefault();
        for(String phoneNumber : PHONE_NOS) {
            sms.sendTextMessage(phoneNumber, null, "Emergency...", null, null);
        }
    }
}
