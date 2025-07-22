package com.example.a1520game;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Result3Activity extends AppCompatActivity {
    private Intent intent;
    private Button btn;
    private TextView roundTextView, textView1, userLeftHand, userRightHand, textView2, opponentLeftHand, opponentRightHand, msgTextView;
    private DatabaseHelper databaseHelper;
    GameDataModel gameDataModel = GameDataModel.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result3);

        roundTextView = findViewById(R.id.roundText);
        textView1 = findViewById(R.id.textView1);
        userLeftHand = findViewById(R.id.userLeftHand);
        userRightHand = findViewById(R.id.userRightHand);

        textView2 = findViewById(R.id.textView2);
        opponentLeftHand = findViewById(R.id.opponentLeftHand);
        opponentRightHand = findViewById(R.id.opponentRightHand);

        msgTextView = findViewById(R.id.msgTextView);
        btn = findViewById(R.id.button2);

        roundTextView.setText("Round " + String.valueOf(gameDataModel.getRound()));

        userLeftHand.setText(String.valueOf(gameDataModel.getUserLeftHand()));
        userRightHand.setText(String.valueOf(gameDataModel.getUserRightHand()));

        fetchOpponentData();

        databaseHelper = new DatabaseHelper(this);
    }

    private void fetchOpponentData() {
        ApiController apiController = new ApiController();
        apiController.start();

        new Thread(() -> {
            try {
                apiController.join();
                runOnUiThread(() -> {
                    opponentLeftHand.setText(String.valueOf(ApiController.dataModel.getOpponentLeft()));
                    opponentRightHand.setText(String.valueOf(ApiController.dataModel.getOpponentRight()));

                    if (gameDataModel.getWhoGuess().equals("userGuess")) {
                        textView1.setText("Yours Hands, Your Guess: " + String.valueOf(gameDataModel.getUserGuess()));
                        textView2.setText(gameDataModel.getOpponentName() + " Hands");
                        if (gameDataModel.getUserGuess() == gameDataModel.getUserLeftHand() + gameDataModel.getUserRightHand() + ApiController.dataModel.getOpponentLeft() + ApiController.dataModel.getOpponentRight()) {
                            msgTextView.setText("You Win at round " + String.valueOf(gameDataModel.getRound()) + "!");
                            btn.setText("Finish!");
                            insertData("Player", "Win", gameDataModel.getRound());
                            btn.setOnClickListener(v -> {
                                intent = new Intent(Result3Activity.this, MainActivity.class);
                                startActivity(intent);
                            });
                            gameDataModel.setRound(gameDataModel.getRound() + 1);
                        } else {
                            msgTextView.setText("You Lose at round " + String.valueOf(gameDataModel.getRound()) + "!");
                            btn.setText("Continue");
                            insertData("Player", "Lose", gameDataModel.getRound());
                            btn.setOnClickListener(v -> {
                                intent = new Intent(Result3Activity.this, LeftHandActivity.class);
                                startActivity(intent);
                            });
                            gameDataModel.setWhoGuess("aiGuess");
                            gameDataModel.setRound(gameDataModel.getRound() + 1);
                        }
                    } else {
                        textView1.setText("Your Hands");
                        textView2.setText(gameDataModel.getOpponentName() + " Hands, " + gameDataModel.getOpponentName() + " Guess: " + ApiController.dataModel.getOpponentGuess());
                        if (ApiController.dataModel.getOpponentGuess() == ApiController.dataModel.getOpponentLeft() + ApiController.dataModel.getOpponentRight() + gameDataModel.getUserLeftHand() + gameDataModel.getUserRightHand()) {
                            msgTextView.setText(gameDataModel.getOpponentName() + " Win at round " + String.valueOf(gameDataModel.getRound()) + "!");
                            btn.setText("Finish!");
                            insertData(gameDataModel.getOpponentName(), "Win", gameDataModel.getRound());
                            btn.setOnClickListener(v -> {
                                intent = new Intent(Result3Activity.this, MainActivity.class);
                                startActivity(intent);
                            });
                            gameDataModel.setRound(gameDataModel.getRound() + 1);

                        } else {
                            msgTextView.setText(gameDataModel.getOpponentName() + " Lose at round " + String.valueOf(gameDataModel.getRound()) + "!");
                            btn.setText("Continue");
                            insertData(gameDataModel.getOpponentName(), "Lose", gameDataModel.getRound());
                            btn.setOnClickListener(v -> {
                                intent = new Intent(Result3Activity.this, LeftHandActivity.class);
                                startActivity(intent);
                            });
                            gameDataModel.setWhoGuess("userGuess");
                            gameDataModel.setRound(gameDataModel.getRound() + 1);
                        }
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void insertData(String opponentName, String winOrLost, int rounds) {
        String gameDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String gameTime = new SimpleDateFormat("HH:mm").format(new Date());
        databaseHelper.addGameLog(gameDate, gameTime, opponentName, winOrLost, rounds);
    }
}
