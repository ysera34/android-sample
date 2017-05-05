package com.example.android.socialkakao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;
import com.kakao.util.helper.log.Logger;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                requestShareToKakao();
                break;
        }
    }

    private void requestShareToKakao() {

        Map<String, String> templateArgs = new HashMap<>();
        templateArgs.put("${title}", "프로방스 자동차 여행");
        templateArgs.put("${description}", "매년 7~8월에 프로방스 발랑솔을 중심으로 라벤더가 만개한다. 이 길을 라벤더로드라고 하며 라벤더와 해바라기 밭이 가득찬 풍경을 어디서나 볼 수 있다.");
        templateArgs.put("${comment_count}", "123");
        templateArgs.put("${like_count}", "345");
        templateArgs.put("${view_count}", "789");
        KakaoLinkService.getInstance().sendCustom(this, "3813", templateArgs, new ResponseCallback<KakaoLinkResponse>() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Logger.e(errorResult.toString());
                Toast.makeText(getApplicationContext(), errorResult.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(KakaoLinkResponse result) {
            }
        });

    }

}
