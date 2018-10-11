package cz.upce.diplomovaprace.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Game {
    private int gameId;
    private String gameName;
    private String gameDescription;
    private Collection<Challenge> challengesByGameId;
    private Collection<GameParam> gameParamsByGameId;
    private Collection<Rating> ratingsByGameId;

    @Id
    @Column(name = "game_id", nullable = false)
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Basic
    @Column(name = "game_name", nullable = false, length = 45)
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Basic
    @Column(name = "game_description", nullable = false, length = 100)
    public String getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return gameId == game.gameId &&
                Objects.equals(gameName, game.gameName) &&
                Objects.equals(gameDescription, game.gameDescription);
    }

    @Override
    public int hashCode() {

        return Objects.hash(gameId, gameName, gameDescription);
    }

    @OneToMany(mappedBy = "gameByGameGameId")
    public Collection<Challenge> getChallengesByGameId() {
        return challengesByGameId;
    }

    public void setChallengesByGameId(Collection<Challenge> challengesByGameId) {
        this.challengesByGameId = challengesByGameId;
    }

    @OneToMany(mappedBy = "gameByGameGameId")
    public Collection<GameParam> getGameParamsByGameId() {
        return gameParamsByGameId;
    }

    public void setGameParamsByGameId(Collection<GameParam> gameParamsByGameId) {
        this.gameParamsByGameId = gameParamsByGameId;
    }

    @OneToMany(mappedBy = "gameByGameGameId")
    public Collection<Rating> getRatingsByGameId() {
        return ratingsByGameId;
    }

    public void setRatingsByGameId(Collection<Rating> ratingsByGameId) {
        this.ratingsByGameId = ratingsByGameId;
    }
}
