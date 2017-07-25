package cn.cqupt.nmid;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static cn.cqupt.nmid.ConstantMap.*;

public class Main {
    static Game game = Game.getGame();
    static RankList rankList = RankList.getRankList();
    static Map<String,User> userMap= new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("游戏运行");
        while(true){
            if(!GAME_START.equals(gameLoadUser())){continue;}
            if(GAME_IS_NOT_RUNNING.equals(userGuessNumber())){continue;}
            if(EXIT_GAME.equals(printList())){break;}
        }
        System.out.print("游戏结束");
    }

    public static String  gameLoadUser () {
        String userName;
        User gameUser;
        do{
            System.out.println("请下方输入用户名：");
            userName = scanner.next();
            if(userName==null||"".equals(userName.trim())){
                System.out.println("您输入的用户名为空");
                continue;
            }
            break;
        }while (true);

        if(userMap.containsKey(userName)){
            gameUser=userMap.get(userName);
        }else{
            gameUser = new User(userName);
            userMap.put(userName,gameUser);
        }
        return game.freshGame(gameUser);
    }

    public static String userGuessNumber (){
        String outputMessage ;
        int inputNum = 0;
        do{
            System.out.println("请输入您猜测的数字，数字范围为：0 ~ "+RANDOM_BAND);
            try{
                inputNum = scanner.nextInt();
                outputMessage = game.CompareNumber(inputNum);
                System.out.println(outputMessage);
            }catch (InputMismatchException e){
                System.out.println("输入异常，请输入正整数");
                inputNum = RANDOM_BAND+1;
                outputMessage = game.CompareNumber(inputNum);
                scanner = new Scanner(System.in);
            }
            if(GAME_IS_NOT_RUNNING.equals(outputMessage)){
                return GAME_IS_NOT_RUNNING;
            }
        }while(!RIGHT_ANSWER.equals(outputMessage));
        rankList.insertNewScore(game.getUser());
        return  outputMessage;
    }

    public static String printList (){
        String inputControllerString;
        print : while(true){
            System.out.println("查看最好成绩排名请输入："+PRINT_BEST_RANK_LIST +
                    "\t查看平均成绩排名请输入："+PRINT_AVERAGE_RANK_LIST+
                    "\t查看游戏次数排名请输入："+PRINT_TOTAL_RANK_LIST+
                    "\n查看历史游戏成绩请输入："+PRINT_HISTORY_GUESS+
                    "\n输入 "+EXIT_GAME+" 结束游戏程序"+
                    "\n输入其他字符退出查看排行，并进行下一轮游戏");
            inputControllerString = scanner.next();
            switch (inputControllerString){
                case PRINT_BEST_RANK_LIST : rankList.printBestList();break ;
                case PRINT_AVERAGE_RANK_LIST : rankList.printAverageList();break ;
                case PRINT_TOTAL_RANK_LIST : rankList.printTotalGameList();break ;
                case PRINT_HISTORY_GUESS : game.getUser().printHistoryGuessTime();break;
                case EXIT_GAME :break print;
                default: System.out.println("此轮结束"); break print;
            }
        }
        return inputControllerString;
    }



}
