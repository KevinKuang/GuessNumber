package cn.cqupt.nmid;

import java.util.Random;

import static cn.cqupt.nmid.ConstantMap.*;

/**
 * Created by Administrator on 2017/7/21.
 */
public class Game {
    private User user;
    private int currentGuessTime=0;
    private int rightNumber;
    private boolean newGameFlag = true;
    private boolean gameIsRun = false;
    private static final Game game = new Game();

    private Game(){}

    public static Game getGame () {
        return game;
    }

    public User getUser() {
        return user;
    }

    public int getCurrentGuessTime() {
        return currentGuessTime;
    }

    public int getRightNumber() {
        return rightNumber;
    }

    public boolean isNewGameFlag() {
        return newGameFlag;
    }

    public boolean isGameIsRun() {
        return gameIsRun;
    }

    public String CompareNumber (int userInputNumber) {
        if (gameIsRun&&user!=null) {
            currentGuessTime++;
            if (userInputNumber == rightNumber) {
                gameIsRun = false;
                user.completeGame(currentGuessTime);
                return RIGHT_ANSWER;
            }
            if (userInputNumber > rightNumber) {
                return BIGGER_ANSWER;
            } else {
                return SMALLER_ANSWER;
            }
        }else{
            gameIsRun = false;
            return GAME_IS_NOT_RUNNING;
        }
    }

    public String freshGame (User user){
        if(gameIsRun){
            return GAME_IS_RUNNING;
        }else if(user != null){
            this.user = user;
            currentGuessTime=0;
            gameIsRun=true;
            Random random = new Random();
            rightNumber = random.nextInt(RANDOM_BAND+1);
            return GAME_START;
        }else {
            return USER_IS_NULL;
        }
    }
}
