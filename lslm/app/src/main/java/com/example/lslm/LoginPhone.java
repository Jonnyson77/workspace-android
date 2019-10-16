package com.example.lslm;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private static boolean  isAgree = true;
    private static  int mClickTimes; // 点击次数
    private static boolean mStartSendMessage = false;
    private long mClickTime; // 点击时间
    private Handler mHandler;
    private TimeCount time;


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
        mHandler = new Handler();
        time = new TimeCount(60000, 1000);


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
                finish();
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
                Intent intent = new Intent(LoginPhone.this,PageMainActivity.class);
                startActivity(intent);
            }
        });

        mRadioView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAgree){
                    mRadioView.setImageResource(R.drawable.radio_on);
                    isAgree =  false ;
                }else {
                    mRadioView.setImageResource(R.drawable.radio);
                    isAgree =  true ;
                }
            }
        });

        mGetVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickTime = System.currentTimeMillis();
                mClickTimes = mClickTimes + 1 ;
                time.start();

//                Log.d(TAG, "onClick: mClickTimes " + mClickTimes);
//                Log.d(TAG, "onClick: mClickTime " + mClickTime );
//                if (mClickTimes <= 2){
//                    Toast.makeText(LoginPhone.this,"验证码已发送至手机,请稍等...",Toast.LENGTH_SHORT).show();
//                }else {
//                    if ((System.currentTimeMillis() - mClickTime) <= 60000){
//                        Toast.makeText(LoginPhone.this,"操作频繁,请稍等....",Toast.LENGTH_SHORT).show();
//                        Runnable mRunnable =  new Runnable() {
//                            @Override
//                            public void run() {
//                                //在这里执行定时需要的操作
//                                mClickTimes = 0;
//                                mStartSendMessage = false;
//                                mGetVerifyCode.setBackground(getDrawable(R.drawable.shape));
//                            }
//                        };
//                        if (!mStartSendMessage){
//                            mHandler.postDelayed(mRunnable, 60000);
//                            mStartSendMessage = true;
//                            mGetVerifyCode.setBackground(getDrawable(R.drawable.shape_pressed));
//                        }
//                    }
//                }
            }
        });
    }

    public void  changeTextColor(){
        String str="登录即代表您已同意立刷服务协议";
        SpannableStringBuilder style=new SpannableStringBuilder(str);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginPhone.this, "请不要点我", Toast.LENGTH_SHORT).show();
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
    public boolean getAgreement(){
        return  isAgree ;
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


    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mGetVerifyCode.setBackground(getDrawable(R.drawable.shape_pressed));
            mGetVerifyCode.setClickable(false);
            mGetVerifyCode.setText("("+millisUntilFinished / 1000 +") 秒重新发送");
        }

        @Override
        public void onFinish() {
            mGetVerifyCode.setText("重新获取验证码");
            mGetVerifyCode.setClickable(true);
            mGetVerifyCode.setBackground(getDrawable(R.drawable.shape));

        }
    }
}
