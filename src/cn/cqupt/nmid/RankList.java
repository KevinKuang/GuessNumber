package cn.cqupt.nmid;

import java.util.LinkedList;

/**
 * Created by Administrator on 2017/7/21.
 */
public class RankList {
    private LinkedList<User> bestList = new LinkedList<>() ;
    private LinkedList<User> averageList = new LinkedList<>() ;
    private LinkedList<User> totalGameList = new LinkedList<>() ;
    private static final RankList rankList = new RankList();

    private RankList(){}

    public static RankList getRankList (){
        return rankList;
    }

    public LinkedList<User> getBestList() {
        return bestList;
    }

    public LinkedList<User> getAverageList() {
        return averageList;
    }

    public LinkedList<User> getTotalGameList() {
        return totalGameList;
    }

    public void insertNewScore (User user){
        changeBestList(user);
        changeAverageList(user);
        changeTotalList(user);
    }

    private void changeBestList (User user){
        int index = 0;
        if(bestList.contains(user)){
            bestList.remove(user);
        }
        if(bestList.size()==0){
            bestList.add(user);
        }else {
            for (User userInList : bestList) {
                if (user.getBestScore() < userInList.getBestScore()) {
                    break;
                }
                index++;
            }
            bestList.add(index, user);
        }
    }

    private void changeAverageList (User user){
        int index = 0;
        if(averageList.contains(user)){
            averageList.remove(user);
        }
        if(averageList.size()==0){
            averageList.add(user);
        }else {
            for (User userInList : averageList) {
                if (user.getAverageGuess() < userInList.getAverageGuess()) {
                    break;
                }
                index++;
            }
            averageList.add(index, user);
        }
    }

    private void changeTotalList (User user){
        int index = 0;
        if(totalGameList.contains(user)){
            totalGameList.remove(user);
        }
        if(totalGameList.size()==0){
            totalGameList.add(user);
        }else {
            for (User userInList : totalGameList) {
                if (user.getTotalGame() > userInList.getTotalGame()) {
                    break;
                }
                index++;
            }
            totalGameList.add(index, user);
        }
    }

    public void printBestList (){
        int index = 1;
        System.out.println("名次\t用户名\t最好成绩");
        for (User user:bestList){
            System.out.println(index+"\t\t"+user.getUserName()+"\t\t"+user.getBestScore());
            index++;
        }
    }

    public void printAverageList (){
        int index = 1;
        System.out.println("名次\t用户名\t平均成绩");
        for (User user:averageList){
            System.out.println(index+"\t\t"+user.getUserName()+"\t\t"+user.getAverageGuess());
            index++;
        }
    }

    public void printTotalGameList (){
        int index = 1;
        System.out.println("名次\t用户名\t游戏场次");
        for (User user:totalGameList){
            System.out.println(index+"\t\t"+user.getUserName()+"\t\t"+user.getTotalGame());
            index++;
        }
    }



}
