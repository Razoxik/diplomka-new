package cz.upce.diplomovaprace.model;

public class ChallengeResultModel {
    private String resultState;
    private int winnerScore;
    private int loserScore;
    private Integer challengeUserId;
    private String description;

    public int getWinnerScore() {
        return winnerScore;
    }

    public void setWinnerScore(int winnerScore) {
        this.winnerScore = winnerScore;
    }

    public int getLoserScore() {
        return loserScore;
    }

    public void setLoserScore(int loserScore) {
        this.loserScore = loserScore;
    }

    public String getResultState() {
        return resultState;
    }

    public void setResultState(String resultState) {
        this.resultState = resultState;
    }

    public Integer getChallengeUserId() {
        return challengeUserId;
    }

    public void setChallengeUserId(Integer challengeUserId) {
        this.challengeUserId = challengeUserId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
