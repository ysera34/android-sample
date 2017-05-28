package org.inframiner.lottie.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LottieAnimationView mLottieAnimationView1;
    private LottieAnimationView mLottieAnimationView2;
    private Button mAnimationStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mLottieAnimationView1 = (LottieAnimationView) findViewById(R.id.animation_view1);
//        mLottieAnimationView1.setAnimation("material_wave_loading.json");
        mLottieAnimationView2 = (LottieAnimationView) findViewById(R.id.animation_view2);
        mAnimationStartButton = (Button) findViewById(R.id.animation_start_button);
        mAnimationStartButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.animation_start_button:
                mLottieAnimationView2.setAnimation("favourite_app_icon.json");
                mLottieAnimationView2.loop(true);
                mLottieAnimationView2.playAnimation();
                break;
        }
    }
}
