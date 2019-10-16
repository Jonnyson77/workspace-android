package com.example.lslm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FindPassword extends Activity {

     Button nextStep ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_password);
        nextStep = findViewById(R.id.next_step);

        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindPassword.this,ChangePassword.class);
                startActivity(intent);
            }
        });
    }
}
