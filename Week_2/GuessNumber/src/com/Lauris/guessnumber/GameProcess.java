package com.Lauris.guessnumber;


import java.util.Scanner;

/**
 * Created by Lawrence on 2017/7/21.
 */
public class GameProcess {
    public static void main(String[] args) {
        Player newPlayer = new Player();
        GameUI gameUI = GameUI.getGameUI();
        Scanner input = new Scanner(System.in);

        //number控制游戏开关
        int number;
        do {
            gameUI.show();
            number = input.nextInt();
            gameUI.gameOn(number);
            if (number == 1) {      //开始游戏
                oneGame(newPlayer);
                //假设每次游戏都为新玩家，当游戏结束输入用户名相同则判断为同一玩家
                newPlayer = new Player();
            } else if (number == 2) { //重新开始 重复开始游戏的代码
                oneGame(newPlayer);
                newPlayer = new Player();
            } else if (number == 4) {
                GuessNumber.getGuessNumber().showRank();
            }
        } while (number != 3);
    }

    /**
     *一次游戏过程的控制代码
     */
    private static void oneGame(Player aPlayer) {
        GuessNumber gm = GuessNumber.getGuessNumber();
        Scanner input = new Scanner(System.in);
        gm.hasNumber();
        //作弊，显示答案
        //System.out.println(gm.cheat());
        int indexNumber = aPlayer.guessNumber();
        while (!gm.judge(indexNumber)) {
            indexNumber = aPlayer.guessNumber();
        }
        aPlayer.setBestWork(gm.getGuessTimes());
        gm.clearGuessTimes();
        System.out.println("请输入您的昵称：");
        if (input.hasNext()) {
            aPlayer.setPlayerName(input.next());
        }
        gm.addRank(aPlayer);
    }
}
