package com.bcajans.app.satiyorum;


import androidx.appcompat.app.AppCompatActivity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView quoteText;
    private TextView emojiHappy, emojiSad, emojiAngry, emojiSleepy, emojiExcited;
    private Button copyBtn, shareBtn;

    private String[] happyQuotes = {
            "Keep smiling, it suits you!",
            "Happiness looks good on you.",
            "Today is a great day to be happy.",
            "Your positivity is contagious."
    };

    private String[] sadQuotes = {
            "It's okay to feel sad sometimes.",
            "Tough times don't last forever.",
            "Allow yourself to feel and heal.",
            "Rainbows come after storms."
    };

    private String[] angryQuotes = {
            "Take a deep breath, calm will follow.",
            "Don't let anger control you.",
            "Channel your energy into something positive.",
            "Anger is temporary, peace is eternal."
    };

    private String[] sleepyQuotes = {
            "Rest is important for a clear mind.",
            "A short nap can recharge your energy.",
            "Sleep well, dream big.",
            "Even superheroes need sleep."
    };

    private String[] excitedQuotes = {
            "Your energy is inspiring!",
            "Great things are coming your way.",
            "Keep shining and spreading joy.",
            "Excitement is the spark of life."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteText = findViewById(R.id.quoteText);
        emojiHappy = findViewById(R.id.emojiHappy);
        emojiSad = findViewById(R.id.emojiSad);
        emojiAngry = findViewById(R.id.emojiAngry);
        emojiSleepy = findViewById(R.id.emojiSleepy);
        emojiExcited = findViewById(R.id.emojiExcited);
        copyBtn = findViewById(R.id.copyBtn);
        shareBtn = findViewById(R.id.shareBtn);

        Random random = new Random();

        // Emoji click listeners
        emojiHappy.setOnClickListener(v -> quoteText.setText(happyQuotes[random.nextInt(happyQuotes.length)]));
        emojiSad.setOnClickListener(v -> quoteText.setText(sadQuotes[random.nextInt(sadQuotes.length)]));
        emojiAngry.setOnClickListener(v -> quoteText.setText(angryQuotes[random.nextInt(angryQuotes.length)]));
        emojiSleepy.setOnClickListener(v -> quoteText.setText(sleepyQuotes[random.nextInt(sleepyQuotes.length)]));
        emojiExcited.setOnClickListener(v -> quoteText.setText(excitedQuotes[random.nextInt(excitedQuotes.length)]));

        // Copy button
        copyBtn.setOnClickListener(v -> {
            String quote = quoteText.getText().toString();
            if (quote.isEmpty() || quote.equals("Select an emoji to see a quote")) {
                Toast.makeText(MainActivity.this, "Nothing to copy", Toast.LENGTH_SHORT).show();
                return;
            }
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Mood Quote", quote);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(MainActivity.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
        });

        // Share button
        shareBtn.setOnClickListener(v -> {
            String quote = quoteText.getText().toString();
            if (quote.isEmpty() || quote.equals("Select an emoji to see a quote")) {
                Toast.makeText(MainActivity.this, "Nothing to share", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, quote + "\n\n— Moodify");
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });
    }
}
