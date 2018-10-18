package cz.upce.diplomovaprace.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Rating {
    private int ratingId;
    private Integer rating;

    private User userByUserUserId;
    private Game gameByGameGameId;

    @Id
    @Column(name = "rating_id", nullable = false)
    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    @Basic
    @Column(name = "rating", nullable = true)
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
        Rating rating1 = (Rating) o;
        return ratingId == rating1.ratingId &&

                Objects.equals(rating, rating1.rating);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ratingId, rating );
    }

    @ManyToOne
    @JoinColumn(name = "User_user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByUserUserId() {
        return userByUserUserId;
    }

    public void setUserByUserUserId(User userByUserUserId) {
        this.userByUserUserId = userByUserUserId;
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
