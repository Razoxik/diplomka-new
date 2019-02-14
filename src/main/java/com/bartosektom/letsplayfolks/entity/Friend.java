package com.bartosektom.letsplayfolks.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Friend {
    private Integer id;
    private Timestamp created;
    private User userByFromUserId;
    private User userByToUserId;

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
    @CreationTimestamp
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
        return Objects.equals(id, friend.id) &&

                Objects.equals(created, friend.created);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, created);
    }

    @ManyToOne
    @JoinColumn(name = "fromUserId", referencedColumnName = "id", nullable = false)
    public User getUserByFromUserId() {
        return userByFromUserId;
    }

    public void setUserByFromUserId(User userByFromUserId) {
        this.userByFromUserId = userByFromUserId;
    }

    @ManyToOne
    @JoinColumn(name = "toUserId", referencedColumnName = "id", nullable = false)
    public User getUserByToUserId() {
        return userByToUserId;
    }

    public void setUserByToUserId(User userByToUserId) {
        this.userByToUserId = userByToUserId;
    }
}
