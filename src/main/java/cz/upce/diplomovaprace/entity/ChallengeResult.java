package cz.upce.diplomovaprace.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table//(name = "challenge_result", schema = "leagueofchallenges", catalog = "")
public class ChallengeResult {
    private int challengeResultId;
    private int challengesChallengeId;
    private String score;
    private String description;
    private Timestamp created;
    private int winnerTeamId;
    private Byte draw;
    private Challenge challengeByChallengesChallengeId;

    @Id
    @Column(name = "challenge_result_id", nullable = false)
    public int getChallengeResultId() {
        return challengeResultId;
    }

    public void setChallengeResultId(int challengeResultId) {
        this.challengeResultId = challengeResultId;
    }

    @Basic
    @Column(name = "Challenges_challenge_id", nullable = false,insertable = false, updatable = false)
    public int getChallengesChallengeId() {
        return challengesChallengeId;
    }

    public void setChallengesChallengeId(int challengesChallengeId) {
        this.challengesChallengeId = challengesChallengeId;
    }

    @Basic
    @Column(name = "score", nullable = false, length = 45)
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 45)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "created", nullable = true)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "winner_team_id", nullable = false,insertable = false, updatable = false)
    public int getWinnerTeamId() {
        return winnerTeamId;
    }

    public void setWinnerTeamId(int winnerTeamId) {
        this.winnerTeamId = winnerTeamId;
    }

    @Basic
    @Column(name = "draw", nullable = true)
    public Byte getDraw() {
        return draw;
    }

    public void setDraw(Byte draw) {
        this.draw = draw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChallengeResult that = (ChallengeResult) o;
        return challengeResultId == that.challengeResultId &&
                challengesChallengeId == that.challengesChallengeId &&
                winnerTeamId == that.winnerTeamId &&
                Objects.equals(score, that.score) &&
                Objects.equals(description, that.description) &&
                Objects.equals(created, that.created) &&
                Objects.equals(draw, that.draw);
    }

    @Override
    public int hashCode() {

        return Objects.hash(challengeResultId, challengesChallengeId, score, description, created, winnerTeamId, draw);
    }

    @ManyToOne
    @JoinColumn(name = "Challenges_challenge_id", referencedColumnName = "challenge_id", nullable = false)
    public Challenge getChallengeByChallengesChallengeId() {
        return challengeByChallengesChallengeId;
    }

    public void setChallengeByChallengesChallengeId(Challenge challengeByChallengesChallengeId) {
        this.challengeByChallengesChallengeId = challengeByChallengesChallengeId;
    }
}
