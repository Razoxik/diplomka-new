package com.bartosektom.letsplayfolks.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Game {

    private Integer id;
    private Timestamp created;
    private String name;
    private String description;
    private Integer approved; // TODO: add to diagram
    private Collection<Challenge> challengesById;
    private Collection<GameParam> gameParamsById;
    private Collection<Rating> ratingsById;

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
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "approved")
    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) &&
                Objects.equals(created, game.created) &&
                Objects.equals(name, game.name) &&
                Objects.equals(description, game.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, name, description);
    }

    @OneToMany(mappedBy = "gameByGameId")
    public Collection<Challenge> getChallengesById() {
        return challengesById;
    }

    public void setChallengesById(Collection<Challenge> challengesById) {
        this.challengesById = challengesById;
    }

    @OneToMany(mappedBy = "gameByGameId")
    public Collection<GameParam> getGameParamsById() {
        return gameParamsById;
    }

    public void setGameParamsById(Collection<GameParam> gameParamsById) {
        this.gameParamsById = gameParamsById;
    }

    @OneToMany(mappedBy = "gameByGameId")
    public Collection<Rating> getRatingsById() {
        return ratingsById;
    }

    public void setRatingsById(Collection<Rating> ratingsById) {
        this.ratingsById = ratingsById;
    }
}
