package com.example.a1520game;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
public class OpponentActivity extends AppCompatActivity {
    GameDataModel gameDataModel = GameDataModel.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opponent);

        ListView opponentsListView = findViewById(R.id.list_view_opponents);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new String[]{"Peter", "John", "Mary", "David", "Alan"});
        opponentsListView.setAdapter(adapter);

        opponentsListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedOpponent = (String) parent.getItemAtPosition(position);

            gameDataModel.setOpponentName(selectedOpponent);
            gameDataModel.setWhoGuess("userGuess");
            gameDataModel.setRound(1);

            Intent intent = new Intent(OpponentActivity.this, LeftHandActivity.class);
            startActivity(intent);
        });
    }
}
