package com.example.lslm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity {

    String TAG = "Register";
    private ImageView mReback;
    private EditText mPhoneNumber;
    private EditText mPassword;
    private EditText mInvitationcode;
    private EditText mVerificationcode;
    private  TextView mGetVerifyCode;
    private static  int mClickTimes; // 点击次数
    private static boolean mStartSendMessage = false;
    private long mClickTime; // 点击时间
    private Handler mHandler;
    private Button mNextStep;
    private TimeCount time;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mReback = (ImageView) findViewById(R.id.reback);
        mGetVerifyCode = (TextView) findViewById(R.id.getVerifyCode);
        mNextStep = (Button)  findViewById(R.id.next_step);

        mPhoneNumber = (EditText) findViewById(R.id.phoneNumber);
        mPassword = (EditText) findViewById(R.id.password);
        mInvitationcode = (EditText) findViewById(R.id.invitationcode);
        mVerificationcode = (EditText) findViewById(R.id.verificationcode);
        time = new TimeCount(60000, 1000);

        mHandler = new Handler();
        mReback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Register.this,"返回上一页",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        mGetVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time.start();
            }
        });

        mNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = mPhoneNumber.getText().toString().replace(" ", "");
                String password =  mPassword.getText().toString().replace(" ","");
                String invitationcode = mInvitationcode.getText().toString().replace(" ","");
                String verificationcode = mVerificationcode.getText().toString().replace(" ","");

                Toast.makeText(Register.this,"正在注册....",Toast.LENGTH_SHORT).show();

            }
        });
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
            mGetVerifyCode.setText(getText(R.string.reget_verifycode));
            mGetVerifyCode.setClickable(true);
            mGetVerifyCode.setBackground(getDrawable(R.drawable.shape));

        }
    }
}
