package com.Lauris.guessnumber;

import java.util.Scanner;

/**
 * Created by Lawrence on 2017/7/21.
 */
public class Player {
    private String playerName = "Player";
    private int bestWork;

    public void setPlayerName(String name) {
        this.playerName = name ;
    }

    public String getPlayerName() {return this.playerName;}

    public int getBestWork() {
        return this.bestWork;
    }

    public void setBestWork(int times) {
        this.bestWork = times;
    }

    public int guessNumber() {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

}
