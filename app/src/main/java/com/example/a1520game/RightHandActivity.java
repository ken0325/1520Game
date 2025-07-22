package com.example.a1520game;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RightHandActivity extends AppCompatActivity {
    private Button rightHandPaper, rightHandRock;
    private Intent intent;
    GameDataModel gameDataModel = GameDataModel.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_hand);

        rightHandPaper = findViewById(R.id.btnRightPaper);
        rightHandRock = findViewById(R.id.btnRightRock);


        rightHandPaper.setOnClickListener(v -> {
            gameDataModel.setUserRightHand(5);
            intent = new Intent(RightHandActivity.this, gameDataModel.getWhoGuess().equals("aiGuess") ? Result3Activity.class : GuessActivity.class);
            startActivity(intent);
        });

        rightHandRock.setOnClickListener(v -> {
            gameDataModel.setUserRightHand(0);
            intent = new Intent(RightHandActivity.this, gameDataModel.getWhoGuess().equals("aiGuess") ? Result3Activity.class : GuessActivity.class);
            startActivity(intent);
        });
    }
}
