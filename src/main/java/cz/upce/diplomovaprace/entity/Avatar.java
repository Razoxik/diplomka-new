package cz.upce.diplomovaprace.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by to068466 on 29.10.2017.
 */
@Entity
public class Avatar {
    private int avatarId;
    private Timestamp created;
    private String avatarImage;
    private String avatarName;
    private Collection<User> usersByAvatarId;

    @Id
    @Column(name = "avatar_id")
    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
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
    @Column(name = "avatar_image")
    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }

    @Basic
    @Column(name = "avatar_name")
    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatar avatar = (Avatar) o;
        return avatarId == avatar.avatarId &&
                Objects.equals(created, avatar.created) &&
                Objects.equals(avatarImage, avatar.avatarImage) &&
                Objects.equals(avatarName, avatar.avatarName) &&
                Objects.equals(usersByAvatarId, avatar.usersByAvatarId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(avatarId, created, avatarImage, avatarName, usersByAvatarId);
    }

    @OneToMany(mappedBy = "avatarByAvatarsAvatarId")
    public Collection<User> getUsersByAvatarId() {
        return usersByAvatarId;
    }

    public void setUsersByAvatarId(Collection<User> usersByAvatarId) {
        this.usersByAvatarId = usersByAvatarId;
    }
}
