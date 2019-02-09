package cz.upce.diplomovaprace.model;

//TODO mit userName v DB uniqie
public class MapModel {

    private int challengeId;
    private int hostId;
    private String hostName;
    private String gameName;
    private int rating;
    private String start;
    private String end;
    private String latCoords;
    private String lngCoords;
    private int numberOfPlayers;
    private int maxPlayers;
    private String description;

    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
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

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
