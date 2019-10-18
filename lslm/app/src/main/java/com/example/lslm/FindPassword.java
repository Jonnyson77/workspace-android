package com.example.lslm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FindPassword extends Activity {
    String TAG = "FindPassword";
    private Button nextStep ;
    private ImageView mReback ;
    private TextView mGetVerifyCode;
    private static  int mClickTimes; // 点击次数
    private static boolean mStartSendMessage = false;
    private long mClickTime; // 点击时间
    private Handler mHandler;
    private TimeCount time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_password);
        nextStep = (Button) findViewById(R.id.next_step);
        mGetVerifyCode = (TextView) findViewById(R.id.getVerifyCode);
        mHandler = new Handler();
        mReback = (ImageView) findViewById(R.id.reback);
        time = new TimeCount(60000,1000);

        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindPassword.this,ChangePassword.class);
                startActivity(intent);
            }
        });

        mGetVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time.start();
            }
        });
        mReback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FindPassword.this,"返回上一页",Toast.LENGTH_SHORT).show();
                finish();
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
