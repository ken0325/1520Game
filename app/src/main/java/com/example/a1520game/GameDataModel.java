package com.example.a1520game;

public class GameDataModel {
    private static GameDataModel instance;
    private String opponentName;
    private Integer round;
    private String whoGuess;
    private Integer userLeftHand;
    private Integer userRightHand;

    private Integer userGuess;

    public GameDataModel() {

    }

    public static GameDataModel getInstance() {
        if (instance == null) {
            instance = new GameDataModel();
        }
        return instance;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public String getWhoGuess() {
        return whoGuess;
    }

    public void setWhoGuess(String whoGuess) {
        this.whoGuess = whoGuess;
    }

    public Integer getUserLeftHand() {
        return userLeftHand;
    }

    public void setUserLeftHand(Integer userLeftHand) {
        this.userLeftHand = userLeftHand;
    }

    public Integer getUserRightHand() {
        return userRightHand;
    }

    public void setUserRightHand(Integer userRightHand) {
        this.userRightHand = userRightHand;
    }

    public Integer getUserGuess() {
        return userGuess;
    }

    public void setUserGuess(Integer userGuess) {
        this.userGuess = userGuess;
    }
}