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
public class Challenge {
    private int challengeId;
    private Timestamp challengeStart;
    private Timestamp challengeEnd;
    private String text;
    private Team teamByChallengerTeamId;
    private Team teamByOponnentTeamId;
    private Collection<ChallengeResult> challengeResultsByChallengeId;
    private String coordsLat;
    private String coordsLng;

    @Id
    @Column(name = "challenge_id")
    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    @Basic
    @Column(name = "Challenge_start")
    public Timestamp getChallengeStart() {
        return challengeStart;
    }

    public void setChallengeStart(Timestamp challengeStart) {
        this.challengeStart = challengeStart;
    }

    @Basic
    @Column(name = "Challenge_end")
    public Timestamp getChallengeEnd() {
        return challengeEnd;
    }

    public void setChallengeEnd(Timestamp challengeEnd) {
        this.challengeEnd = challengeEnd;
    }

    @Basic
    @Column(name = "Text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "coordsLat")
    public String getCoordsLat() {
        return coordsLat;
    }

    public void setCoordsLat(String coordsLat) {
        this.coordsLat = coordsLat;
    }

    @Basic
    @Column(name = "coordsLng")
    public String getCoordsLng() {
        return coordsLng;
    }

    public void setCoordsLng(String coordsLng) {
        this.coordsLng = coordsLng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Challenge challenge = (Challenge) o;

        if (challengeId != challenge.challengeId) return false;
        if (coordsLat != null ? !coordsLat.equals(challenge.coordsLat) : challenge.coordsLat != null) return false;
        if (coordsLng != null ? !coordsLng.equals(challenge.coordsLng) : challenge.coordsLng != null) return false;
        if (challengeStart != null ? !challengeStart.equals(challenge.challengeStart) : challenge.challengeStart != null)
            return false;
        if (challengeEnd != null ? !challengeEnd.equals(challenge.challengeEnd) : challenge.challengeEnd != null)
            return false;
        if (text != null ? !text.equals(challenge.text) : challenge.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = challengeId;
        result = 31 * result + (coordsLat != null ? coordsLat.hashCode() : 0);
        result = 31 * result + (coordsLng != null ? coordsLng.hashCode() : 0);
        result = 31 * result + (challengeStart != null ? challengeStart.hashCode() : 0);
        result = 31 * result + (challengeEnd != null ? challengeEnd.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "challenger_team_id", referencedColumnName = "team_id", nullable = false)
    public Team getTeamByChallengerTeamId() {
        return teamByChallengerTeamId;
    }

    public void setTeamByChallengerTeamId(Team teamByChallengerTeamId) {
        this.teamByChallengerTeamId = teamByChallengerTeamId;
    }

    @ManyToOne
    @JoinColumn(name = "oponnent_team_id", referencedColumnName = "team_id")
    public Team getTeamByOponnentTeamId() {
        return teamByOponnentTeamId;
    }

    public void setTeamByOponnentTeamId(Team teamByOponnentTeamId) {
        this.teamByOponnentTeamId = teamByOponnentTeamId;
    }

    @OneToMany(mappedBy = "challengeByChallengesChallengeId")
    public Collection<ChallengeResult> getChallengeResultsByChallengeId() {
        return challengeResultsByChallengeId;
    }

    public void setChallengeResultsByChallengeId(Collection<ChallengeResult> challengeResultsByChallengeId) {
        this.challengeResultsByChallengeId = challengeResultsByChallengeId;
    }
}
