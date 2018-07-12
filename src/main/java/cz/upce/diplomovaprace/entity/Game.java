package cz.upce.diplomovaprace.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * Created by to068466 on 29.10.2017.
 */
@Entity
public class Game {
    private int gameId;
    private String gameName;
    private String gameDescription;
    private Collection<GameParam> gameParamsByGameId;
    private Collection<League> leaguesByGameId;

    @Id
    @Column(name = "game_id")
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Basic
    @Column(name = "game_name")
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Basic
    @Column(name = "game_description")
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

        if (gameId != game.gameId) return false;
        if (gameName != null ? !gameName.equals(game.gameName) : game.gameName != null) return false;
        if (gameDescription != null ? !gameDescription.equals(game.gameDescription) : game.gameDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gameId;
        result = 31 * result + (gameName != null ? gameName.hashCode() : 0);
        result = 31 * result + (gameDescription != null ? gameDescription.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "gameByGameGameId")
    public Collection<GameParam> getGameParamsByGameId() {
        return gameParamsByGameId;
    }

    public void setGameParamsByGameId(Collection<GameParam> gameParamsByGameId) {
        this.gameParamsByGameId = gameParamsByGameId;
    }

    @OneToMany(mappedBy = "gameByGameGameId")
    public Collection<League> getLeaguesByGameId() {
        return leaguesByGameId;
    }

    public void setLeaguesByGameId(Collection<League> leaguesByGameId) {
        this.leaguesByGameId = leaguesByGameId;
    }
}
