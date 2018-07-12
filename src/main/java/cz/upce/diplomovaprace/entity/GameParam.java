package cz.upce.diplomovaprace.entity;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

/**
 * Created by to068466 on 29.10.2017.
 */
@Entity
public class GameParam {
    private int gameParamId;
    private String name;
    private Timestamp created;
    private String value;
    private Game gameByGameGameId;

    @Id
    @Column(name = "game_param_id")
    public int getGameParamId() {
        return gameParamId;
    }

    public void setGameParamId(int gameParamId) {
        this.gameParamId = gameParamId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameParam gameParam = (GameParam) o;

        if (gameParamId != gameParam.gameParamId) return false;
        if (name != null ? !name.equals(gameParam.name) : gameParam.name != null) return false;
        if (created != null ? !created.equals(gameParam.created) : gameParam.created != null) return false;
        if (value != null ? !value.equals(gameParam.value) : gameParam.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gameParamId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Game_game_id", referencedColumnName = "game_id", nullable = false)
    public Game getGameByGameGameId() {
        return gameByGameGameId;
    }

    public void setGameByGameGameId(Game gameByGameGameId) {
        this.gameByGameGameId = gameByGameGameId;
    }
}
