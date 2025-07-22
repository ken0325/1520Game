package com.example.a1520game;

public class DataModel {
    private Integer opponentLeft;
    private Integer opponentRight;
    private Integer opponentGuess;

    public DataModel() {

    }

    public Integer getOpponentLeft() {
        return opponentLeft;
    }

    public void setOpponentLeft(Integer opponentLeft) {
        this.opponentLeft = opponentLeft;
    }

    public Integer getOpponentRight() {
        return opponentRight;
    }

    public void setOpponentRight(Integer opponentRight) {
        this.opponentRight = opponentRight;
    }

    public Integer getOpponentGuess() {
        return opponentGuess;
    }

    public void setOpponentGuess(Integer opponentGuess) {
        this.opponentGuess = opponentGuess;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "opponentLeft='" + opponentLeft + '\'' +
                ", opponentRight='" + opponentRight + '\'' +
                ", opponentGuess='" + opponentGuess + '\'' +
                '}';
    }
}