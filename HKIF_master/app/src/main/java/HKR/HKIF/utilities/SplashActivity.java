package HKR.HKIF.utilities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import HKR.HKIF.MainActivity;
import HKR.HKIF.R;

public class SplashActivity extends AppCompatActivity {

    private static int splashTime = 7000;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                /*LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.animation_view);
                lottieAnimationView.setAnimation("data.json");
                lottieAnimationView.loop(true);
                lottieAnimationView.playAnimation();*/

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, splashTime);

        //Animation myAnimation = AnimationUtils.loadAnimation(R.anim.splashanimation);
        //logo.startAnimation(myAnimation);


    }
}
