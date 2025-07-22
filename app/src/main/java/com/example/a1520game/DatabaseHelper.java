package com.example.a1520game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "GamesLog.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_GAMESLOG = "GamesLog";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_GAME_DATE = "gameDate";
    private static final String COLUMN_GAME_TIME = "gameTime";
    private static final String COLUMN_OPPONENT_NAME = "opponentName";
    private static final String COLUMN_WIN_OR_LOST = "winOrLost";
    private static final String COLUMN_ROUNDS = "rounds";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GAMESLOG_TABLE = "CREATE TABLE " + TABLE_GAMESLOG + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_GAME_DATE + " TEXT NOT NULL," +
                COLUMN_GAME_TIME + " TEXT NOT NULL," +
                COLUMN_OPPONENT_NAME + " TEXT NOT NULL," +
                COLUMN_WIN_OR_LOST + " TEXT NOT NULL," +
                COLUMN_ROUNDS + " INTEGER NOT NULL" +
                ")";
        db.execSQL(CREATE_GAMESLOG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMESLOG);
        onCreate(db);
    }

    // Method to add a game log entry
    public void addGameLog(String date, String time, String opponent, String result, int rounds) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_GAME_DATE, date);
        values.put(COLUMN_GAME_TIME, time);
        values.put(COLUMN_OPPONENT_NAME, opponent);
        values.put(COLUMN_WIN_OR_LOST, result);
        values.put(COLUMN_ROUNDS, rounds);
        db.insert(TABLE_GAMESLOG, null, values);
        db.close();
    }

    // Method to get all game logs
    public List<String> getAllGameLogs() {
        List<String> gameLogs = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_GAMESLOG, null);

        if (cursor.moveToFirst()) {
            do {
                int dateIndex = cursor.getColumnIndex(COLUMN_GAME_DATE);
                int timeIndex = cursor.getColumnIndex(COLUMN_GAME_TIME);
                int opponentIndex = cursor.getColumnIndex(COLUMN_OPPONENT_NAME);
                int resultIndex = cursor.getColumnIndex(COLUMN_WIN_OR_LOST);
                int roundsIndex = cursor.getColumnIndex(COLUMN_ROUNDS);

                // Check if column index is valid
                if (dateIndex != -1 && timeIndex != -1 && opponentIndex != -1 && resultIndex != -1 && roundsIndex != -1) {
                    String log = cursor.getString(dateIndex) + ", " +
                            cursor.getString(timeIndex) + ", " +
                            cursor.getString(opponentIndex) + ", " +
                            cursor.getString(resultIndex) + " at round " +
                            cursor.getInt(roundsIndex);
                    gameLogs.add(log);
                } else {
                    // Handle the case where the column index is invalid
                    // You might log an error or handle it in a way that suits your needs
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return gameLogs;
    }
}