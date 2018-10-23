package cz.upce.diplomovaprace.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Challenge {
    private Integer id;
    private Timestamp created;
    private Timestamp start;
    private Timestamp end;
    private String coordsLat;
    private String coordsLng;
    private String description;
    private String password;
    private Game gameByGameId;
    private ChallengeState challengeStateByChallengeStateId;
    private Collection<ChallengeResult> challengeResultsById;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "start", nullable = false)
    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    @Basic
    @Column(name = "end", nullable = false)
    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    @Basic
    @Column(name = "coordsLat", nullable = false, length = 255)
    public String getCoordsLat() {
        return coordsLat;
    }

    public void setCoordsLat(String coordsLat) {
        this.coordsLat = coordsLat;
    }

    @Basic
    @Column(name = "coordsLng", nullable = false, length = 255)
    public String getCoordsLng() {
        return coordsLng;
    }

    public void setCoordsLng(String coordsLng) {
        this.coordsLng = coordsLng;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Challenge challenge = (Challenge) o;
        return Objects.equals(id, challenge.id) &&

                Objects.equals(created, challenge.created) &&
                Objects.equals(start, challenge.start) &&
                Objects.equals(end, challenge.end) &&
                Objects.equals(coordsLat, challenge.coordsLat) &&
                Objects.equals(coordsLng, challenge.coordsLng) &&
                Objects.equals(description, challenge.description) &&
                Objects.equals(password, challenge.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, created, start, end, coordsLat, coordsLng, description, password);
    }

    @ManyToOne
    @JoinColumn(name = "gameId", referencedColumnName = "id", nullable = false)
    public Game getGameByGameId() {
        return gameByGameId;
    }

    public void setGameByGameId(Game gameByGameId) {
        this.gameByGameId = gameByGameId;
    }

    @ManyToOne
    @JoinColumn(name = "challengeStateId", referencedColumnName = "id", nullable = false)
    public ChallengeState getChallengeStateByChallengeStateId() {
        return challengeStateByChallengeStateId;
    }

    public void setChallengeStateByChallengeStateId(ChallengeState challengeStateByChallengeStateId) {
        this.challengeStateByChallengeStateId = challengeStateByChallengeStateId;
    }

    @OneToMany(mappedBy = "challengeByChallengeId")
    public Collection<ChallengeResult> getChallengeResultsById() {
        return challengeResultsById;
    }

    public void setChallengeResultsById(Collection<ChallengeResult> challengeResultsById) {
        this.challengeResultsById = challengeResultsById;
    }
}
