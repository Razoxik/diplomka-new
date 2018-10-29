package cz.upce.diplomovaprace.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ChallengeModel {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime start;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime end;
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
