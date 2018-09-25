package cz.upce.diplomovaprace.model;

public class ChallengeModel {
    String latCoords;
    String lngCoords;
    int gameId;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
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
}
