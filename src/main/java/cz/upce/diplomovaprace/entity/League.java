package cz.upce.diplomovaprace.entity;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * Created by to068466 on 29.10.2017.
 */
@Entity
public class League {
    private int leaguId;
    private String leagueName;
    private String leagueDescription;
    private Game gameByGameGameId;
    private Collection<Team> teamsByLeaguId;

    @Id
    @Column(name = "leagu_id")
    public int getLeaguId() {
        return leaguId;
    }

    public void setLeaguId(int leaguId) {
        this.leaguId = leaguId;
    }

    @Basic
    @Column(name = "league_name")
    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    @Basic
    @Column(name = "league_description")
    public String getLeagueDescription() {
        return leagueDescription;
    }

    public void setLeagueDescription(String leagueDescription) {
        this.leagueDescription = leagueDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        League league = (League) o;

        if (leaguId != league.leaguId) return false;
        if (leagueName != null ? !leagueName.equals(league.leagueName) : league.leagueName != null) return false;
        if (leagueDescription != null ? !leagueDescription.equals(league.leagueDescription) : league.leagueDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = leaguId;
        result = 31 * result + (leagueName != null ? leagueName.hashCode() : 0);
        result = 31 * result + (leagueDescription != null ? leagueDescription.hashCode() : 0);
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

    @OneToMany(mappedBy = "leagueByLeagueLeaguId")
    public Collection<Team> getTeamsByLeaguId() {
        return teamsByLeaguId;
    }

    public void setTeamsByLeaguId(Collection<Team> teamsByLeaguId) {
        this.teamsByLeaguId = teamsByLeaguId;
    }
}
