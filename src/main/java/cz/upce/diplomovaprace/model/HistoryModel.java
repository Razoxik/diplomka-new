package cz.upce.diplomovaprace.model;

public class HistoryModel {

    private Integer challengeId;
    private String gameName;
    private String resultState;
    private String start;
    private Integer scoreWinner;
    private Integer scoreLooser;

    public Integer getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Integer challengeId) {
        this.challengeId = challengeId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getResultState() {
        return resultState;
    }

    public void setResultState(String resultState) {
        this.resultState = resultState;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public Integer getScoreWinner() {
        return scoreWinner;
    }

    public void setScoreWinner(Integer scoreWinner) {
        this.scoreWinner = scoreWinner;
    }

    public Integer getScoreLooser() {
        return scoreLooser;
    }

    public void setScoreLooser(Integer scoreLooser) {
        this.scoreLooser = scoreLooser;
    }
}
