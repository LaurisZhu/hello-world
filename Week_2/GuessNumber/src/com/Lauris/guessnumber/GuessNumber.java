package com.Lauris.guessnumber;

/**
 * Created by Lawrence on 2017/7/21.
 */
public class GuessNumber {
    private int trueNumber;
    private int guessTimes;

    /*
    * 单例模式的练习
    * */
    private GuessNumber() {}

    private static GuessNumber gm = new GuessNumber();

    public static GuessNumber getGuessNumber() {
        return  gm;
    }

    /*
    * 生成一个随机数0~100
    * */
    public void hasNumber() {
        getGuessNumber().trueNumber = (int)(Math.random()*100);
    }

    public int getGuessTimes() {return gm.guessTimes;}

    /*
    * 判断猜测情况，返回布尔值，功能核心
    * */
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


}
