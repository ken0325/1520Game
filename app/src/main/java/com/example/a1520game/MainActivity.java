package com.example.a1520game;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playButton = findViewById(R.id.button_play);
        playButton.setOnClickListener(v -> startGame());

        Button recordsButton = findViewById(R.id.button_records);
        recordsButton.setOnClickListener(v -> viewRecords());

        Button closeButton = findViewById(R.id.button_close);
        closeButton.setOnClickListener(v -> finishAffinity());
    }

    private void startGame() {
        Intent intent = new Intent(this, OpponentActivity.class);
        startActivity(intent);
    }

    private void viewRecords() {
        Intent intent = new Intent(this, GamesLogActivity.class);
        startActivity(intent);
    }
}
