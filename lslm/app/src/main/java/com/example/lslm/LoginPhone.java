package com.example.lslm;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PrivateKey;

public class LoginPhone extends Activity {
    private String TAG = "MainActivity";
    private TextView mForgetPassword;
    private TextView mGetMessageCode;
    private Button mRegisterBtn;
    private ImageButton mCloseBtn;
    private TextView mHelpView;
    private TextView mGetVerifyCode;
    private EditText mLoginPassword;
    private EditText mLoginPhone;
    private Button mLoginBtn;
    private ImageView mRadioView;
    private static boolean  isChanged = false;
    private static boolean  isGree = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_phone);
        changeTextColor();
        Log.d(TAG, "onCreate: ljq ---------- ");
        // 初始化view组件
        mForgetPassword = (TextView)findViewById(R.id.forgetPassword);
        mGetMessageCode = (TextView) findViewById(R.id.getMessageCode);
        mRegisterBtn = (Button)findViewById(R.id.register_btn);
        mCloseBtn = (ImageButton) findViewById(R.id.close_btn);
        mHelpView = (TextView) findViewById(R.id.helpView);

        mLoginPassword = (EditText)findViewById(R.id.loginPassword);
        mLoginBtn =(Button) findViewById(R.id.loginBtn);
        mLoginPhone= (EditText)findViewById(R.id.phoneNumber);
        mRadioView =(ImageView)findViewById(R.id.radioView);
        mGetVerifyCode = (TextView) findViewById(R.id.getVerifyCode);


        // 设置监听事件
        mForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPhone.this,FindPassword.class);
                startActivity(intent);
            }
        });

        mGetMessageCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPhone.this,MainActivity.class);
                startActivity(intent);
            }
        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPhone.this,Register.class);
                startActivity(intent);
            }
        });

        mCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onCreate: ljq -----------close");
                finish();
            }
        });
        mHelpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onCreate: ljq -----------help");
                Toast.makeText(LoginPhone.this,"正在开发",Toast.LENGTH_SHORT).show();
            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String password = mLoginPassword.getText().toString();
               String phone = mLoginPhone.getText().toString();
               Toast.makeText(LoginPhone.this,"login ...",Toast.LENGTH_SHORT).show();
            }
        });

        mRadioView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isChanged){
                    mRadioView.setImageResource(R.drawable.radio_on);
                    isChanged =  false ;
                }else {
                    mRadioView.setImageResource(R.drawable.radio);
                    isChanged =  true ;
                }
            }
        });

        mGetVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginPhone.this,"正在开发中",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void  changeTextColor(){
        String str="登录即代表您已同意立刷服务协议";
        int fstart=str.indexOf("立刷服务协议");
        int fend=fstart+"立刷服务协议".length();
        SpannableStringBuilder style=new SpannableStringBuilder(str);
        style.setSpan(new ForegroundColorSpan(Color.RED),fstart,fend,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        TextView tvColor=(TextView) findViewById(R.id.gree);
        tvColor.setText(style);
        tvColor.setText(style);
    }
}
