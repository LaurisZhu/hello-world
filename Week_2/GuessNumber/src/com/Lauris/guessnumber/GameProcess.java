package com.Lauris.guessnumber;


import java.util.Scanner;

/**
 * Created by Lawrence on 2017/7/21.
 */
public class GameProcess {
    public static void main(String[] args) {
        Player newPlayer = new Player();
        GuessNumber gm = GuessNumber.getGuessNumber();
        GameUI gameUI = GameUI.getGameUI();
        Scanner input = new Scanner(System.in);
        gameUI.show();
        //number控制游戏开关
        int number = input.nextInt();
        //开始游戏
        do {
            gameUI.gameOn(number);
            if (number == 1) {
              gm.hasNumber();
              int indexnumber = newPlayer.guessNumber();
              while (!gm.judge(indexnumber)) {
                  indexnumber = newPlayer.guessNumber();
              }
              newPlayer.setBestWork(gm.getGuessTimes());
           } else if (number == 2) {
              gm.hasNumber();
              int indexnumber = newPlayer.guessNumber();
              while (!gm.judge(indexnumber)) {
                  indexnumber = newPlayer.guessNumber();
              }
              newPlayer.setBestWork(gm.getGuessTimes());
            }
            gameUI.show();
            number = input.nextInt();
        } while (number != 3);
    }
}
