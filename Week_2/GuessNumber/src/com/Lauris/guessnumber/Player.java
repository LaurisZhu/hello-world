package com.Lauris.guessnumber;

import java.util.Scanner;

/**
 * Created by Lawrence on 2017/7/21.
 */
public class Player implements Comparable {
    private String playerName = "Player";
    private int bestWork;

    public Player() {}

    public Player(String name, int score) {
        setPlayerName(name);
        setBestWork(score);
    }

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

    //本来想使用TreeSet作为排行榜的容器，因为可以自动排名，若是出现重名，应该复写equals方法。
    //后来改用简单的LinkedList作为容器，此方法保留未删除。
    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Player))
            throw new RuntimeException("比较的不是玩家！");
        Player otherPlayer = (Player)o;
        if (this.bestWork == otherPlayer.bestWork)
            return 0;
        else if (this.bestWork > otherPlayer.bestWork)
            return 1;
        else return -1;
    }
}
