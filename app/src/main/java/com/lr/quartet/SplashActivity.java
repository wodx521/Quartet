package com.lr.quartet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

public class SplashActivity extends AppCompatActivity {

    private CountDownTimer countDownTimer;
    private RxPermissions rxPermissions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rxPermissions = new RxPermissions(this);
        countDownTimer = new CountDownTimer(3 * 1000 + 1500, 1000) {
            @SuppressLint("CheckResult")
            @Override
            public void onTick(long millisUntilFinished) {
                long remainingSecond = millisUntilFinished / 1000 - 1;
                if (remainingSecond == 0) {
                    //剩余0秒时,及时结束,调用结束方法
                    onFinish();
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
//                    rxPermissions
//                            .request(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                            .subscribe(granted -> {
//                                if (granted) {
//                                    // 同意权限
//                                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                } else {
//                                    // 权限被拒绝
//                                    Toast.makeText(SplashActivity.this, "权限被拒绝，无法使用", Toast.LENGTH_SHORT).show();
//                                    finish();
//                                }
//                            });
                }
            }

            @Override
            public void onFinish() {
                cancel();
            }
        };
        countDownTimer.start();
    }

}
