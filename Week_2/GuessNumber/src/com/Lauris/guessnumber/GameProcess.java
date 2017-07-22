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
            } else if (number == 2) { //重新开始 重复开始游戏的代码
              oneGame(newPlayer);
            } else if (number == 4) {

            }
        } while (number != 3);
    }

    private static void oneGame(Player aPlayer) {
        GuessNumber gm = GuessNumber.getGuessNumber();
        Scanner input = new Scanner(System.in);
        gm.hasNumber();
        System.out.println(gm.cheat());
        int indexNumber = aPlayer.guessNumber();
        while (!gm.judge(indexNumber)) {
            indexNumber = aPlayer.guessNumber();
        }
        aPlayer.setBestWork(gm.getGuessTimes());
        System.out.println("请输入您的昵称：(英文)");
        if (input.hasNext())
            aPlayer.setPlayerName(input.next());
    }
}
