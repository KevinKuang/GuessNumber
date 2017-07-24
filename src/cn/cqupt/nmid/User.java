package cn.cqupt.nmid;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/20.
 */
public class User {
    private String userName;//用户名
    private int bestScore=0;//最佳次数
    private int totalGame;//总游戏次数
    private int totalGuess;//总猜数字次数
    private double averageGuess;//平均猜数字次数
    private ArrayList<Integer> historyGuess = new ArrayList<>();//历史猜题次数

    public User(String userName){
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getBestScore() {
        return bestScore;
    }

    public int getTotalGame() {
        return totalGame;
    }

    public double getAverageGuess() {
        return averageGuess;
    }

    public void completeGame (int guessTime){
        if(bestScore> guessTime||bestScore==0){
            bestScore= guessTime;
        }
        totalGame++;
        totalGuess+=guessTime;
        averageGuess = 1.0*totalGuess/totalGame;
        historyGuess.add(guessTime);
    }

    public void printHistoryGuessTime (){
        int i = 0;
        int sizeOfHistory = historyGuess.size();
        int lineNum = sizeOfHistory/10+1;
        int lineStart,lineEnd,nowLine=0;
        System.out.print(this.userName+"的历史成绩如下");
        for (int guessTime:historyGuess) {
            if(i%10==0){
                System.out.println();
                nowLine++;
                lineStart = nowLine*10-9;
                if(nowLine==lineNum){
                    lineEnd = sizeOfHistory;
                }else {
                    lineEnd = nowLine*10;
                }

                if(lineEnd == lineStart){
                    System.out.print("第"+lineEnd+"次：");
                }else {
                    System.out.print("第"+lineStart+"次到第"+lineEnd+"次：");
                }
            }
            System.out.print("\t"+guessTime);
            i++;
        }
        System.out.println();
    }
}
