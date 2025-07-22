package com.example.a1520game;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
public class LeftHandActivity extends AppCompatActivity {
    private Button leftHandPaper, leftHandRock;
    private Intent intent;
    GameDataModel gameDataModel = GameDataModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_hand);

        leftHandPaper = findViewById(R.id.btnLeftPaper);
        leftHandRock = findViewById(R.id.btnLeftRock);

        leftHandPaper.setOnClickListener(v -> {
            gameDataModel.setUserLeftHand(5);
            intent = new Intent(LeftHandActivity.this, RightHandActivity.class);
            startActivity(intent);
        });

        leftHandRock.setOnClickListener(v -> {
            gameDataModel.setUserLeftHand(0);
            intent = new Intent(LeftHandActivity.this, RightHandActivity.class);
            startActivity(intent);
        });
    }
}
