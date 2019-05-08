package com.bartosektom.letsplayfolks.model;

import java.io.Serializable;

public class UserRatingModel implements Serializable {

    private static final long serialVersionUID = -5927022982963906175L;

    private String game;
    private long rating;
    private long userId;

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
