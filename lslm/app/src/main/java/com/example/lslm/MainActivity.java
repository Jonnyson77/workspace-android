package com.example.lslm;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Utils.Utils;
import com.example.net.ConnectApi;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {
    private String TAG = "MainActivity";
    private TextView mForgetPassword;
    private TextView mGetMessageCode;
    private Button mRegisterBtn;
    private ImageButton mCloseBtn;
    private TextView mHelpView;
    private ImageView mDisplayView;
    private EditText mLoginPassword;
    private EditText mLoginPhone;
    private Button mLoginBtn;
    private ImageView mRadioView;
    private static boolean  isChange = false;
    private static boolean  isAgree = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeTextColor();
        Log.d(TAG, "onCreate: ljq ---------- ");
        // 初始化view组件
        mForgetPassword = (TextView)findViewById(R.id.forgetPassword);
        mGetMessageCode = (TextView) findViewById(R.id.getMessageCode);
        mRegisterBtn = (Button)findViewById(R.id.register_btn);
        mCloseBtn = (ImageButton) findViewById(R.id.close_btn);
        mHelpView = (TextView) findViewById(R.id.helpView);
        mDisplayView = (ImageView) findViewById(R.id.display);
        mLoginPassword = (EditText)findViewById(R.id.loginPassword);
        mLoginBtn =(Button) findViewById(R.id.loginBtn);
        mLoginPhone= (EditText)findViewById(R.id.phoneNumber);
        mRadioView =(ImageView)findViewById(R.id.radioView);


        // 设置监听事件
        mForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FindPassword.class);
                startActivity(intent);
            }
        });

        mGetMessageCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginPhone.class);
                startActivity(intent);
                finish();
            }
        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Register.class);
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
                Toast.makeText(MainActivity.this,"正在开发",Toast.LENGTH_SHORT).show();
            }
        });
        mDisplayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onCreate: ljq -----------isChange  " + mLoginPassword.getInputType());
                if (!isChange ){
                   mLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                   mDisplayView.setImageResource(R.drawable.hide);
                    isChange  =  true ;
                }else {
                    mLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mDisplayView.setImageResource((R.drawable.display));
                    isChange  =  false ;
                }
            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String password = mLoginPassword.getText().toString().replace(" ", "");
               String phone = mLoginPhone.getText().toString().replace(" ", "");

                Log.d(TAG,"ljq   password " + password.length());
                Log.d(TAG,"ljq   phone " + phone.length());

                Log.d(TAG, "onResume:  llll  ljq  ");
                callNet();
                Log.d(TAG, "onResume:  2222   ljq ");

               if (phone.length()==0 || password.length() ==0){
                   Toast.makeText(MainActivity.this,"手机号码不能为空 或者 密码不能为空",Toast.LENGTH_SHORT).show();
                   return;
               }

               if (Utils.isStringNull(phone)){
                   Toast.makeText(MainActivity.this,"手机号码不能为空",Toast.LENGTH_SHORT).show();
                   return;
               }
               else {
                   if (Utils.isStringNull(password)) {
                       Toast.makeText(MainActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                       return;
                   }else{
                       // 开始判断账户密码是否有效
                       if (getUser(phone,password)){
                           if (getAgreement()){
                               Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(MainActivity.this,PageMainActivity.class);
                               startActivity(intent);
                           }else {
                               Toast.makeText(MainActivity.this, "请同意登录协议", Toast.LENGTH_SHORT).show();
                           }
                       }else{
                           Toast.makeText(MainActivity.this, "账户或密码错误", Toast.LENGTH_SHORT).show();
                       }
                   }
               }
            }
        });

        mRadioView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( isAgree){
                    mRadioView.setImageResource(R.drawable.radio_on);
                    isAgree =  false ;
                }else {
                    mRadioView.setImageResource(R.drawable.radio);
                    isAgree =  true ;
                }
            }
        });
    }

    public boolean  getUser(String phoneNumber,String password){
        Log.d(TAG,"ljq getUser:  phone  " + phoneNumber);
        Log.d(TAG,"ljq getUser:  password   " + password);
        return true;
    }
    public boolean getAgreement(){
        return  isAgree ;
    }

    public void  changeTextColor(){
        String str="登录即代表您已同意立刷服务协议";
        SpannableStringBuilder style=new SpannableStringBuilder(str);
        ClickableSpan clickableSpan = new ClickableSpan() {

            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "请不要点我", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);//设置颜色
                ds.setUnderlineText(false);//去掉下划线
            }
        };
        style.setSpan(clickableSpan, 9,15, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        TextView tvColor=(TextView) findViewById(R.id.gree);
        tvColor.setText(style);
        tvColor.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean callNet(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.baidu.com")
                .addConverterFactory(GsonConverterFactory.create())//通过Gson的格式来解析数据
                .build();
        ConnectApi connectApi = retrofit.create(ConnectApi.class);
        connectApi.getUserInfo().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String result = null;
                try {
                    result = response.body().string();
                    Log.d(TAG,"ljq getUser: ljq 11111 ");
                    Log.d(TAG,"ljq result : ljq result " + result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG,"ljq getUser: ljq 2222222 ");
                Toast.makeText(MainActivity.this,"失败",Toast.LENGTH_LONG).show();
            }
        });
        return  true;
    }
}
