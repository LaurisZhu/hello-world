package com.Lauris.guessnumber;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Lawrence on 2017/7/21.
 */
public class GuessNumber {
    private int trueNumber;
    private int guessTimes;
    private static ArrayList<Player> rankList;

    {
        rankList = new ArrayList<>();
    }

    /**
    * 单例模式的练习
    */
    private GuessNumber() {}

    private static GuessNumber gm = new GuessNumber();

    public static GuessNumber getGuessNumber() {
        return  gm;
    }

    public int cheat() {
        return getGuessNumber().trueNumber;
    }

    /**
    * 生成一个随机数1~100
    */
    public void hasNumber() {
        getGuessNumber().trueNumber = (int)(Math.random()*100 + 1);
    }

    public int getGuessTimes() {return gm.guessTimes;}

    public void clearGuessTimes() {
        gm.guessTimes = 0;
    }

    /**
     * 判断猜测情况，返回布尔值，功能核心
     */
    public boolean judge(int number) {
        getGuessNumber().guessTimes++;
        if (getGuessNumber().trueNumber == number) {
            System.out.println("正确！数字是：" + number);
            System.out.println("您的猜测次数为：" + getGuessNumber().guessTimes);
            return true;
        } else if (getGuessNumber().trueNumber < number) {
            System.out.println("错误！猜测数字过大！");
            return false;
        } else {
            System.out.println("错误！猜测数字过小！");
            return false;
        }
    }


    /**
     *排行榜添加，维持功能，先添加再排序。
     */
    public void addRank(Player aPlayer) {
        ArrayList<Player> rankList = GuessNumber.rankList;
        if (rankList.size() <= 10 ) {
            if (hasEqualPlayer(aPlayer)) {
                if (rankList.get(getEqualPlayer(aPlayer)).getBestWork() > aPlayer.getBestWork())
                rankList.set(getEqualPlayer(aPlayer),aPlayer);
            }
            else if (rankList.size() < 10)
                rankList.add(aPlayer);
        }
        rankSort();
    }
    /**
     * 为排行榜提供排序功能
     */
    private void rankSort() {
        for (int i = 0; i < rankList.size() - 1; i++) {
            for (int j = 1; j < rankList.size() - i; j++) {
                if (rankList.get(j-1).getBestWork() > rankList.get(j).getBestWork()) {
                    Player temPlayer = rankList.get(j-1);
                    rankList.set(j-1,rankList.get(j));
                    rankList.set(j,temPlayer);
                }
            }
        }
    }

    /**
     * 是否有相同玩家
     */
    private boolean hasEqualPlayer(Player aPlayer) {
        int temp = 0;
        Iterator<Player> it = rankList.iterator();
        while (it.hasNext()) {
            if (it.next().getPlayerName().equals(aPlayer.getPlayerName())) {
                temp++;
                break;
            }
        }
        return temp == 1;
    }

    /**
     * 排行榜是否有相同玩家
     * @param aPlayer 玩家
     * @return temp 返回相同玩家所在的索引
     */
    private int getEqualPlayer(Player aPlayer) {
        int temp = 0;
        Iterator<Player> it = rankList.iterator();
        while (it.hasNext()) {
            if (it.next().getPlayerName().equals(aPlayer.getPlayerName())) {
                break;
            }
            temp++;
        }
        return temp;
    }

    /**
     * 排行榜显示控制
     */
    public void showRank() {
        System.out.printf("排名\t玩家名称\t猜测次数\n");
        int rankNumber = 1;
        Iterator<Player> it = rankList.iterator();
        while (it.hasNext()) {
            Player tmpPlayer =it.next();
            System.out.printf("%d\t%s\t\t%d\n",rankNumber,tmpPlayer.getPlayerName(),tmpPlayer.getBestWork());
            rankNumber++;
        }
    }
}