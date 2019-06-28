package com.example.faceshop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
    private TextView txtSocial,txtAnim;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        txtSocial=findViewById(R.id.txtSocial);
        txtAnim=findViewById(R.id.txtAnim);

        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.push_right);
        txtSocial.setAnimation(animation);

        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.push_left);
        txtAnim.setAnimation(animation);

        Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
                finish();
            }
        },3000);

    }
}
