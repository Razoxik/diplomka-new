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
import java.util.Objects;

@Entity
public class Challenge {
    private int challengeId;
    private Timestamp challengeStart;
    private Timestamp challengeEnd;
    private String text;
    private String coordsLat;
    private String coordsLng;
    @Column(insertable = false, updatable = false)
    private int gameGameId;
    private int challengerUserId;
    private int oponnentUserId;
    private String password;
    @Column(insertable = false, updatable = false)
    private Game gameByGameGameId;
    private User userByChallengerUserId;
    private User userByOponnentUserId;
    private Collection<ChallengeResult> challengeResultsByChallengeId;

    @Id
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
    @Column(name = "Game_game_id", nullable = false,insertable = false, updatable = false)
    public int getGameGameId() {
        return gameGameId;
    }

    public void setGameGameId(int gameGameId) {
        this.gameGameId = gameGameId;
    }

    @Basic
    @Column(name = "challenger_user_id", nullable = false,insertable = false, updatable = false)
    public int getChallengerUserId() {
        return challengerUserId;
    }

    public void setChallengerUserId(int challengerUserId) {
        this.challengerUserId = challengerUserId;
    }

    @Basic
    @Column(name = "oponnent_user_id", nullable = false,insertable = false, updatable = false)
    public int getOponnentUserId() {
        return oponnentUserId;
    }

    public void setOponnentUserId(int oponnentUserId) {
        this.oponnentUserId = oponnentUserId;
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
        return challengeId == challenge.challengeId &&
                gameGameId == challenge.gameGameId &&
                challengerUserId == challenge.challengerUserId &&
                oponnentUserId == challenge.oponnentUserId &&
                Objects.equals(challengeStart, challenge.challengeStart) &&
                Objects.equals(challengeEnd, challenge.challengeEnd) &&
                Objects.equals(text, challenge.text) &&
                Objects.equals(coordsLat, challenge.coordsLat) &&
                Objects.equals(coordsLng, challenge.coordsLng) &&
                Objects.equals(password, challenge.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(challengeId, challengeStart, challengeEnd, text, coordsLat, coordsLng, gameGameId, challengerUserId, oponnentUserId, password);
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
    @JoinColumn(name = "oponnent_user_id", referencedColumnName = "user_id", nullable = false)
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
