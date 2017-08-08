package com.Lauris.guessnumber;

import java.util.*;

/**
 * Created by Lawrence on 2017/7/21.
 */
public class GuessNumber {
    private int trueNumber;
    private int guessTimes;
    /*private static ArrayList<Player> rankList;

    {
        rankList = new ArrayList<>();
    }*/

    //由于排行榜的增删使用比较多，应该使用LinkedList而不是ArrayList
    private LinkedList<Player> ranklist = new LinkedList<>();

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
     * 更新：之前对ArrayList的理解不够深刻，写了一些多余的代码，已经更正
     */
    public void addRank(Player aPlayer) {
        ranklist.add(aPlayer);
        rankSort();
    }

    /**
     * 为排行榜提供排序功能
     */
    private void rankSort() {
        for (int i = 0; i < ranklist.size() - 1; i++) {
            for (int j = 1; j < ranklist.size() - i; j++) {
                if (ranklist.get(j-1).getBestWork() > ranklist.get(j).getBestWork()) {
                    Player temPlayer = ranklist.get(j-1);
                    ranklist.set(j-1,ranklist.get(j));
                    ranklist.set(j,temPlayer);
                }
            }
        }
    }


    /**
     * 排行榜是否有相同玩家
     * 因为排行榜允许一个玩家占据多个排名，所以用数字存储同一玩家的所有索引
     * @param aPlayer 玩家
     * @return temp 返回相同玩家所在的所有索引
     */
    private int[] getEqualPlayer(Player aPlayer) {
        int index = 0;
        int i = 0;
        int[] temp = new int[10];
        Iterator<Player> it = ranklist.iterator();
        while (it.hasNext()) {
            if (it.next().getPlayerName().equals(aPlayer.getPlayerName())) {
                temp[i++] = index;
                break;
            }
            index++;
        }
        return temp;
    }

    /**
     * 排行榜显示控制
     * 更新：排行榜只显示10个的限制从addRank()更改到此方法。
     */
    public void showRank() {
        System.out.printf("排名\t玩家名称\t猜测次数\n");
        int rankNumber = 1;
        Iterator<Player> it = ranklist.iterator();
        while (it.hasNext()) {
            if (rankNumber <= 10) {
                Player tmpPlayer = it.next();
                System.out.printf("%d\t%s\t\t%d\n", rankNumber, tmpPlayer.getPlayerName(), tmpPlayer.getBestWork());
                rankNumber++;
            }
            else break;
        }
    }
}