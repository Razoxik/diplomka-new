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
    private int challengeId;
    private Timestamp challengeStart;
    private Timestamp challengeEnd;
    private String text;
    private String coordsLat;
    private String coordsLng;
    private String password;
    @Column(insertable = false, updatable = false)
    private Game gameByGameGameId;
    private User userByChallengerUserId;
    private User userByOponnentUserId;
    private Collection<ChallengeResult> challengeResultsByChallengeId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "challenge_id", nullable = false)
    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    @Basic
    @Column(name = "Challenge_start", nullable = false)
    public Timestamp getChallengeStart() {
        return challengeStart;
    }

    public void setChallengeStart(Timestamp challengeStart) {
        this.challengeStart = challengeStart;
    }

    @Basic
    @Column(name = "Challenge_end", nullable = false)
    public Timestamp getChallengeEnd() {
        return challengeEnd;
    }

    public void setChallengeEnd(Timestamp challengeEnd) {
        this.challengeEnd = challengeEnd;
    }

    @Basic
    @Column(name = "Text", nullable = true, length = 45)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "CoordsLat", nullable = false, length = 255)
    public String getCoordsLat() {
        return coordsLat;
    }

    public void setCoordsLat(String coordsLat) {
        this.coordsLat = coordsLat;
    }

    @Basic
    @Column(name = "CoordsLng", nullable = false, length = 255)
    public String getCoordsLng() {
        return coordsLng;
    }

    public void setCoordsLng(String coordsLng) {
        this.coordsLng = coordsLng;
    }



    @Basic
    @Column(name = "password", length = 45)
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
        return challengeId == challenge.challengeId &&

                Objects.equals(challengeStart, challenge.challengeStart) &&
                Objects.equals(challengeEnd, challenge.challengeEnd) &&
                Objects.equals(text, challenge.text) &&
                Objects.equals(coordsLat, challenge.coordsLat) &&
                Objects.equals(coordsLng, challenge.coordsLng) &&
                Objects.equals(password, challenge.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(challengeId, challengeStart, challengeEnd, text, coordsLat, coordsLng,      password);
    }

    @ManyToOne
    @JoinColumn(name = "Game_game_id", referencedColumnName = "game_id", nullable = false)
    public Game getGameByGameGameId() {
        return gameByGameGameId;
    }

    public void setGameByGameGameId(Game gameByGameGameId) {
        this.gameByGameGameId = gameByGameGameId;
    }

    @ManyToOne
    @JoinColumn(name = "challenger_user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByChallengerUserId() {
        return userByChallengerUserId;
    }

    public void setUserByChallengerUserId(User userByChallengerUserId) {
        this.userByChallengerUserId = userByChallengerUserId;
    }

    @ManyToOne
    @JoinColumn(name = "oponnent_user_id", referencedColumnName = "user_id")
    public User getUserByOponnentUserId() {
        return userByOponnentUserId;
    }

    public void setUserByOponnentUserId(User userByOponnentUserId) {
        this.userByOponnentUserId = userByOponnentUserId;
    }

    @OneToMany(mappedBy = "challengeByChallengesChallengeId")
    public Collection<ChallengeResult> getChallengeResultsByChallengeId() {
        return challengeResultsByChallengeId;
    }

    public void setChallengeResultsByChallengeId(Collection<ChallengeResult> challengeResultsByChallengeId) {
        this.challengeResultsByChallengeId = challengeResultsByChallengeId;
    }
}

/*
You shouldn't reference other entities by their ID, but by a direct reference to the entity.
 Remove the customerId field, it's useless. And do the same for productId. If you want the customer ID of a sale, you just need to do this:
 sale.getCustomer().getId()
https://stackoverflow.com/questions/15076463/another-repeated-column-in-mapping-for-entity-error/15076546
 */