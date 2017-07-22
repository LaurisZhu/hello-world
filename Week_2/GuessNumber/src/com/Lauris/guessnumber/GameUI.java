package com.Lauris.guessnumber;

/**
 * Created by Lawrence on 2017/7/21.
 */
public class GameUI extends Game {

    /**
    * 继承意义不大 仅为练习
    */
    @Override
    public void start(){
        System.out.println("开始游戏！ 猜数字：1~100");
    }

    /**
    * 单例模式
    */
    private GameUI() {}
    private static GameUI gameUI = new GameUI();
    public static GameUI getGameUI() {
        return gameUI;
    }

    /**
    * 游戏界面
    */
    public void show() {
        System.out.println("-------------------");
        System.out.println("开始游戏-----------输入1");
        System.out.println("再次开始-----------输入2");
        System.out.println("游戏结束-----------输入3");
        System.out.println("查看排行榜---------输入4");
    }

    public void gameOn(int number) {
        switch (number) {
            case 1 :
                this.start();break;
            case 2 :
                this.startAgain();break;
            case 3 :
                this.over();break;
            case 4 :
                break;
            default:
                System.out.println("输入错误!");
        }
    }

}
