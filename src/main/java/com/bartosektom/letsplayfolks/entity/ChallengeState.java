package com.bartosektom.letsplayfolks.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Objects;

@Entity
public class ChallengeState {

    private Integer id;
    private String state;
    private Collection<Challenge> challengesById;

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
    @Column(name = "state", nullable = false, length = 45)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChallengeState that = (ChallengeState) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state);
    }

    @OneToMany(mappedBy = "challengeStateByChallengeStateId")
    public Collection<Challenge> getChallengesById() {
        return challengesById;
    }

    public void setChallengesById(Collection<Challenge> challengesById) {
        this.challengesById = challengesById;
    }
}
