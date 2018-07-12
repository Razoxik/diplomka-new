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
public class Friend {
    private int friendId;
    private Timestamp created;
    private User userByUserFriendId;
    private User userByUserId;

    @Id
    @Column(name = "friend_id")
    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    @Basic
    @Column(name = "created", insertable = false)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Friend friend = (Friend) o;

        if (friendId != friend.friendId) return false;
        if (created != null ? !created.equals(friend.created) : friend.created != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = friendId;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
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
