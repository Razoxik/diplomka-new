package com.bartosektom.letsplayfolks.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ChallengeModel {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime start = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime end = LocalDateTime.now().plusHours(24).truncatedTo(ChronoUnit.MINUTES);
    private String latCoords;
    private String lngCoords;
    private int gameId;
    private String description;
    private String password;

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getLatCoords() {
        return latCoords;
    }

    public void setLatCoords(String latCoords) {
        this.latCoords = latCoords;
    }

    public String getLngCoords() {
        return lngCoords;
    }

    public void setLngCoords(String lngCoords) {
        this.lngCoords = lngCoords;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
