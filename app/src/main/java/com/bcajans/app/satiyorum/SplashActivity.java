package com.bcajans.app.satiyorum;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final String APP_NAME = "Moodify";
    private TextView appNameText;
    private int textIndex = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appNameText = findViewById(R.id.appNameText);

        // Typing animation for app name (system font, big and bold)
        handler.postDelayed(typingRunnable, 120);

        // Proceed to MainActivity after 2 seconds + typing duration
        handler.postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, 2000 + APP_NAME.length() * 80);
    }

    private Runnable typingRunnable = new Runnable() {
        @Override
        public void run() {
            if (textIndex <= APP_NAME.length()) {
                appNameText.setText(APP_NAME.substring(0, textIndex));
                textIndex++;
                handler.postDelayed(this, 80); // Typing speed
            }
        }
    };
}