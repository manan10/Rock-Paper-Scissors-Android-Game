package com.example.rpsgame;

class Player {
    private int cscore;
    private int pscore;

    public Player() {
    }

    public Player(int cscore, int pscore) {
        this.cscore = cscore;
        this.pscore = pscore;
    }

    public int getCscore() {
        return cscore;
    }

    public void setCscore(int cscore) {
        this.cscore = cscore;
    }

    public int getPscore() {
        return pscore;
    }

    public void setPscore(int pscore) {
        this.pscore = pscore;
    }
}
