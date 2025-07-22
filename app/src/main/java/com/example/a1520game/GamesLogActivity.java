package com.example.a1520game;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class GamesLogActivity extends AppCompatActivity {
    private ListView gamesListView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_log);

        gamesListView = findViewById(R.id.gamesListView);
        databaseHelper = new DatabaseHelper(this);

        loadGameLogs();
    }

    private void loadGameLogs() {
        List<String> gameLogs = databaseHelper.getAllGameLogs();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gameLogs);
        gamesListView.setAdapter(adapter);
    }
}