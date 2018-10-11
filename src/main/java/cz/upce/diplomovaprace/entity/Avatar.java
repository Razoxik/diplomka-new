package cz.upce.diplomovaprace.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Avatar {
    private int avatarId;
    private Timestamp created;
    private String avatarImage;
    private String avatarName;
    private Collection<User> usersByAvatarId;

    @Id
    @Column(name = "avatar_id", nullable = false)
    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
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
    @Column(name = "avatar_image", nullable = false, length = 45)
    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }

    @Basic
    @Column(name = "avatar_name", nullable = false, length = 45)
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
                Objects.equals(avatarName, avatar.avatarName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(avatarId, created, avatarImage, avatarName);
    }

    @OneToMany(mappedBy = "avatarByAvatarsAvatarId")
    public Collection<User> getUsersByAvatarId() {
        return usersByAvatarId;
    }

    public void setUsersByAvatarId(Collection<User> usersByAvatarId) {
        this.usersByAvatarId = usersByAvatarId;
    }
}
