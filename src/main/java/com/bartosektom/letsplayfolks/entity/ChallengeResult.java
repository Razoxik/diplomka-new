package com.bartosektom.letsplayfolks.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class ChallengeResult {

    private Integer id;
    private Timestamp created;
    private Integer scoreWinner;
    private Integer scoreDefeated;
    private Integer teamNumber; // TODO:UPRAVIT DATA MODEL!
    private String description;
    private Challenge challengeByChallengeId;
    private User userByUserId;
    private ResultState resultStateByResultStateId;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "created", nullable = false)
    @CreationTimestamp
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "scoreWinner")
    public Integer getScoreWinner() {
        return scoreWinner;
    }

    public void setScoreWinner(Integer scoreWinner) {
        this.scoreWinner = scoreWinner;
    }

    @Basic
    @Column(name = "teamNumber")
    public Integer getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(Integer teamNumber) {
        this.teamNumber = teamNumber;
    }

    @Basic
    @Column(name = "scoreDefeated")
    public Integer getScoreDefeated() {
        return scoreDefeated;
    }

    public void setScoreDefeated(Integer scoreDefeated) {
        this.scoreDefeated = scoreDefeated;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChallengeResult that = (ChallengeResult) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(created, that.created) &&
                Objects.equals(scoreWinner, that.scoreWinner) &&
                Objects.equals(scoreDefeated, that.scoreDefeated) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, scoreWinner, scoreDefeated, description);
    }

    @ManyToOne
    @JoinColumn(name = "challengeId", referencedColumnName = "id", nullable = false)
    public Challenge getChallengeByChallengeId() {
        return challengeByChallengeId;
    }

    public void setChallengeByChallengeId(Challenge challengeByChallengeId) {
        this.challengeByChallengeId = challengeByChallengeId;
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "resultStateId", referencedColumnName = "id", nullable = false)
    public ResultState getResultStateByResultStateId() {
        return resultStateByResultStateId;
    }

    public void setResultStateByResultStateId(ResultState resultStateByResultStateId) {
        this.resultStateByResultStateId = resultStateByResultStateId;
    }
}
