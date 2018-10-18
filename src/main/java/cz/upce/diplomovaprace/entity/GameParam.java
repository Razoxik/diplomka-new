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
public class GameParam {
    private int gameParamId;
    private String name;
    private Timestamp created;
    private String value;

    private Game gameByGameGameId;

    @Id
    @Column(name = "game_param_id", nullable = false)
    public int getGameParamId() {
        return gameParamId;
    }

    public void setGameParamId(int gameParamId) {
        this.gameParamId = gameParamId;
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
    @Column(name = "created", nullable = false)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "value", nullable = true, length = 255)
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
        return gameParamId == gameParam.gameParamId &&
                 Objects.equals(name, gameParam.name) &&
                Objects.equals(created, gameParam.created) &&
                Objects.equals(value, gameParam.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(gameParamId, name, created, value );
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
