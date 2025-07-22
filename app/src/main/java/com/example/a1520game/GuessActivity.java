package com.example.a1520game;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
public class GuessActivity extends AppCompatActivity {
    GameDataModel gameDataModel = GameDataModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        ListView opponentsListView = findViewById(R.id.list_view_guess);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new Integer[]{0, 5, 10,15,20});
        opponentsListView.setAdapter(adapter);

        opponentsListView.setOnItemClickListener((parent, view, position, id) -> {
            Integer selectedGuess = (Integer) parent.getItemAtPosition(position);
            gameDataModel.setUserGuess(selectedGuess);
            Intent intent = new Intent(GuessActivity.this, Result3Activity.class);
            startActivity(intent);
        });
    }
}
