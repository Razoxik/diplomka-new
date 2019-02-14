package com.bartosektom.letsplayfolks.model;

import java.io.Serializable;

public class ChallengeDetailUserModel implements Serializable {

    private static final long serialVersionUID = -1391279349534153713L;

    private int id;
    private int rating;
    private String userName;
    private int numberOfGames;
    private int numberOfWins;
    private int numberOfLosses;
    private int numberOfTies;
    private int winningUserScore;
    private int lossingUserScore;
    private boolean canBeAddToFriends;

    public String getChallengeResultState() {
        return challengeResultState;
    }

    public void setChallengeResultState(String challengeResultState) {
        this.challengeResultState = challengeResultState;
    }

    private String challengeResultState;


    public int getWinningUserScore() {
        return winningUserScore;
    }

    public void setWinningUserScore(int winningUserScore) {
        this.winningUserScore = winningUserScore;
    }

    public int getLossingUserScore() {
        return lossingUserScore;
    }

    public void setLossingUserScore(int lossingUserScore) {
        this.lossingUserScore = lossingUserScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public int getNumberOfLosses() {
        return numberOfLosses;
    }

    public void setNumberOfLosses(int numberOfLosses) {
        this.numberOfLosses = numberOfLosses;
    }

    public int getNumberOfTies() {
        return numberOfTies;
    }

    public void setNumberOfTies(int numberOfTies) {
        this.numberOfTies = numberOfTies;
    }

    public boolean isCanBeAddToFriends() {
        return canBeAddToFriends;
    }

    public void setCanBeAddToFriends(boolean canBeAddToFriends) {
        this.canBeAddToFriends = canBeAddToFriends;
    }
}
