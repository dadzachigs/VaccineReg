package co.zw.trigonsolutes.vaccinereg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import co.zw.trigonsolutes.vaccinereg.userAuth.SignUp;

public class SplashScreen extends AppCompatActivity {

    private ImageView bgImage;
    Animation sideAnimation, topAnimation;
    private static int SCREEN_TIME = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sideAnimation = AnimationUtils.loadAnimation(this, R.anim.splash);


        bgImage = findViewById(R.id.splash);
        bgImage.setAnimation(sideAnimation);


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {


                Intent i = new Intent(getApplicationContext(), SignUp.class);
                startActivity(i);


                finish();
            }


        }, SCREEN_TIME);
    }
}