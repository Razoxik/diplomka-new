package cz.upce.diplomovaprace.entity;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by to068466 on 29.10.2017.
 */
@Entity
public class Team {
    private int teamId;
    private Timestamp created;
    private String name;
    private String description;
    private Integer rating;
    private Collection<Challenge> challengesByTeamId;
    private Collection<Challenge> challengesByTeamId_0;
    private Collection<ChallengeResult> challengeResultsByTeamId;
    private League leagueByLeagueLeaguId;
    private Collection<TeamUser> teamUsersByTeamId;

    @Id
    @Column(name = "team_id")
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
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
    @Column(name = "name")
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
    @Column(name = "rating")
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (teamId != team.teamId) return false;
        if (created != null ? !created.equals(team.created) : team.created != null) return false;
        if (name != null ? !name.equals(team.name) : team.name != null) return false;
        if (description != null ? !description.equals(team.description) : team.description != null) return false;
        if (rating != null ? !rating.equals(team.rating) : team.rating != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamId;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "teamByChallengerTeamId")
    public Collection<Challenge> getChallengesByTeamId() {
        return challengesByTeamId;
    }

    public void setChallengesByTeamId(Collection<Challenge> challengesByTeamId) {
        this.challengesByTeamId = challengesByTeamId;
    }

    @OneToMany(mappedBy = "teamByOponnentTeamId")
    public Collection<Challenge> getChallengesByTeamId_0() {
        return challengesByTeamId_0;
    }

    public void setChallengesByTeamId_0(Collection<Challenge> challengesByTeamId_0) {
        this.challengesByTeamId_0 = challengesByTeamId_0;
    }

    @OneToMany(mappedBy = "teamByWinnerTeamId")
    public Collection<ChallengeResult> getChallengeResultsByTeamId() {
        return challengeResultsByTeamId;
    }

    public void setChallengeResultsByTeamId(Collection<ChallengeResult> challengeResultsByTeamId) {
        this.challengeResultsByTeamId = challengeResultsByTeamId;
    }

    @ManyToOne
    @JoinColumn(name = "League_leagu_id", referencedColumnName = "leagu_id", nullable = false)
    public League getLeagueByLeagueLeaguId() {
        return leagueByLeagueLeaguId;
    }

    public void setLeagueByLeagueLeaguId(League leagueByLeagueLeaguId) {
        this.leagueByLeagueLeaguId = leagueByLeagueLeaguId;
    }

    @OneToMany(mappedBy = "teamByTeamTeamId")
    public Collection<TeamUser> getTeamUsersByTeamId() {
        return teamUsersByTeamId;
    }

    public void setTeamUsersByTeamId(Collection<TeamUser> teamUsersByTeamId) {
        this.teamUsersByTeamId = teamUsersByTeamId;
    }
}
