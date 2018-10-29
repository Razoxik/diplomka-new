package cz.upce.diplomovaprace.dto;

import cz.upce.diplomovaprace.entity.Challenge;
import cz.upce.diplomovaprace.entity.Game;
import cz.upce.diplomovaprace.entity.User;

import java.util.List;

public class ChallengeDto {
    private Game game;
    private User host;
    private int rating;
    private String start;
    private String end;
    private Challenge challenge;
    private List<User> listOfPlayers;
    private int maxPlayers;

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public List<User> getListOfPlayers() {
        return listOfPlayers;
    }

    public void setListOfPlayers(List<User> listOfPlayers) {
        this.listOfPlayers = listOfPlayers;
    }
}
