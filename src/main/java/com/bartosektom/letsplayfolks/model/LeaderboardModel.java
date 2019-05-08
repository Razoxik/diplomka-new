package com.bartosektom.letsplayfolks.model;

import java.io.Serializable;

public class LeaderboardModel implements Serializable {

    private static final long serialVersionUID = 5704216032402542904L;

    private long gameId;
    private String gameName;
    private long userId;
    private String userName;
    private long numberOfGames;
    private long numberOfWins;
    private long numberOfLooses;
    private long numberOfTies;
    private long winRateRatio;
    private long rating;

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(long numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public long getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(long numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public long getNumberOfLooses() {
        return numberOfLooses;
    }

    public void setNumberOfLooses(long numberOfLooses) {
        this.numberOfLooses = numberOfLooses;
    }

    public long getNumberOfTies() {
        return numberOfTies;
    }

    public void setNumberOfTies(long numberOfTies) {
        this.numberOfTies = numberOfTies;
    }

    public long getWinRateRatio() {
        return winRateRatio;
    }

    public void setWinRateRatio(long winRateRatio) {
        this.winRateRatio = winRateRatio;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }
}
