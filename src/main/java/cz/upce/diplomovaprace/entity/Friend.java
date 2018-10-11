package cz.upce.diplomovaprace.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Friend {
    private int friendId;
    private Timestamp created;
    private int userFriendId;
    private int userId;
    private User userByUserFriendId;
    private User userByUserId;

    @Id
    @Column(name = "friend_id", nullable = false)
    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
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
    @Column(name = "user_friend_id", nullable = false,insertable = false, updatable = false)
    public int getUserFriendId() {
        return userFriendId;
    }

    public void setUserFriendId(int userFriendId) {
        this.userFriendId = userFriendId;
    }

    @Basic
    @Column(name = "user_id", nullable = false,insertable = false, updatable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friend friend = (Friend) o;
        return friendId == friend.friendId &&
                userFriendId == friend.userFriendId &&
                userId == friend.userId &&
                Objects.equals(created, friend.created);
    }

    @Override
    public int hashCode() {

        return Objects.hash(friendId, created, userFriendId, userId);
    }

    @ManyToOne
    @JoinColumn(name = "user_friend_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByUserFriendId() {
        return userByUserFriendId;
    }

    public void setUserByUserFriendId(User userByUserFriendId) {
        this.userByUserFriendId = userByUserFriendId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
