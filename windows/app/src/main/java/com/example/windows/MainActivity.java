package com.example.windows;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.provider.Settings;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener,View.OnClickListener {

    private static final String TAG = "ljq";

    private Button mCreateWndBtn ,mRmvWinBtn;

    private ImageView mImageView;
    private WindowManager.LayoutParams layoutParams;
    private WindowManager windowManager;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            startActivityForResult(intent,100);

        }else {
            initView();
        }

    }

    private void initView() {
        mCreateWndBtn = (Button) findViewById(R.id.add_btn);
        mRmvWinBtn = (Button) findViewById(R.id.rm_btn);
        mCreateWndBtn.setOnClickListener(this);
        mRmvWinBtn.setOnClickListener(this);
        windowManager = (WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.add_btn){
            mImageView = new ImageView(this);
            mImageView.setBackgroundResource(R.mipmap.ic_launcher);
            layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,2099,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL|
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED, PixelFormat.TRANSPARENT);
            layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
            layoutParams.gravity = Gravity.TOP | Gravity.LEFT;
            layoutParams.x = 0;
            layoutParams.y = 300;

            mImageView.setOnTouchListener(this);
            windowManager.addView(mImageView,layoutParams);
        }else if (v.getId()== R.id.rm_btn){
            windowManager.removeViewImmediate(mImageView);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int rawX = (int)event.getRawX();
        int rawY = (int)event.getRawY();

        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE :
            {
                layoutParams.x = rawX;
                layoutParams.y = rawY;
                windowManager.updateViewLayout(mImageView,layoutParams);
                break;
            }

            default:
                break;
        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100){
            initView();
        }
    }
}
