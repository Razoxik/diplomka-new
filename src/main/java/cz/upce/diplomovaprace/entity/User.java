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
public class User {
    private int userId;
    private Timestamp created;
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    private String password;
    private Timestamp lastLogin;
    private int avatarsAvatarId;
    private int roleRoleId;
    private Collection<Challenge> challengesByUserId;
    private Collection<Challenge> challengesByUserId_0;
    private Collection<Friend> friendsByUserId;
    private Collection<Friend> friendsByUserId_0;
    private Collection<Message> messagesByUserId;
    private Collection<Message> messagesByUserId_0;
    private Collection<Rating> ratingsByUserId;
    private Collection<Report> reportsByUserId;
    private Collection<Report> reportsByUserId_0;
    private Avatar avatarByAvatarsAvatarId;
    private Role roleByRoleRoleId;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
    @Column(name = "firstName", nullable = true, length = 45)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "lastName", nullable = true, length = 45)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "nickname", nullable = false, length = 45)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "last_login", nullable = false)
    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Basic
    @Column(name = "Avatars_avatar_id", nullable = false,insertable = false, updatable = false)
    public int getAvatarsAvatarId() {
        return avatarsAvatarId;
    }

    public void setAvatarsAvatarId(int avatarsAvatarId) {
        this.avatarsAvatarId = avatarsAvatarId;
    }

    @Basic
    @Column(name = "Role_role_id", nullable = false,insertable = false, updatable = false)
    public int getRoleRoleId() {
        return roleRoleId;
    }

    public void setRoleRoleId(int roleRoleId) {
        this.roleRoleId = roleRoleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                avatarsAvatarId == user.avatarsAvatarId &&
                roleRoleId == user.roleRoleId &&
                Objects.equals(created, user.created) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(nickname, user.nickname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(lastLogin, user.lastLogin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, created, firstName, lastName, nickname, email, password, lastLogin, avatarsAvatarId, roleRoleId);
    }

    @OneToMany(mappedBy = "userByChallengerUserId")
    public Collection<Challenge> getChallengesByUserId() {
        return challengesByUserId;
    }

    public void setChallengesByUserId(Collection<Challenge> challengesByUserId) {
        this.challengesByUserId = challengesByUserId;
    }

    @OneToMany(mappedBy = "userByOponnentUserId")
    public Collection<Challenge> getChallengesByUserId_0() {
        return challengesByUserId_0;
    }

    public void setChallengesByUserId_0(Collection<Challenge> challengesByUserId_0) {
        this.challengesByUserId_0 = challengesByUserId_0;
    }

    @OneToMany(mappedBy = "userByUserFriendId")
    public Collection<Friend> getFriendsByUserId() {
        return friendsByUserId;
    }

    public void setFriendsByUserId(Collection<Friend> friendsByUserId) {
        this.friendsByUserId = friendsByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Friend> getFriendsByUserId_0() {
        return friendsByUserId_0;
    }

    public void setFriendsByUserId_0(Collection<Friend> friendsByUserId_0) {
        this.friendsByUserId_0 = friendsByUserId_0;
    }

    @OneToMany(mappedBy = "userByFromUserId")
    public Collection<Message> getMessagesByUserId() {
        return messagesByUserId;
    }

    public void setMessagesByUserId(Collection<Message> messagesByUserId) {
        this.messagesByUserId = messagesByUserId;
    }

    @OneToMany(mappedBy = "userByToUserId")
    public Collection<Message> getMessagesByUserId_0() {
        return messagesByUserId_0;
    }

    public void setMessagesByUserId_0(Collection<Message> messagesByUserId_0) {
        this.messagesByUserId_0 = messagesByUserId_0;
    }

    @OneToMany(mappedBy = "userByUserUserId")
    public Collection<Rating> getRatingsByUserId() {
        return ratingsByUserId;
    }

    public void setRatingsByUserId(Collection<Rating> ratingsByUserId) {
        this.ratingsByUserId = ratingsByUserId;
    }

    @OneToMany(mappedBy = "userByReportingUserId")
    public Collection<Report> getReportsByUserId() {
        return reportsByUserId;
    }

    public void setReportsByUserId(Collection<Report> reportsByUserId) {
        this.reportsByUserId = reportsByUserId;
    }

    @OneToMany(mappedBy = "userByReportedUserId")
    public Collection<Report> getReportsByUserId_0() {
        return reportsByUserId_0;
    }

    public void setReportsByUserId_0(Collection<Report> reportsByUserId_0) {
        this.reportsByUserId_0 = reportsByUserId_0;
    }

    @ManyToOne
    @JoinColumn(name = "Avatars_avatar_id", referencedColumnName = "avatar_id", nullable = false)
    public Avatar getAvatarByAvatarsAvatarId() {
        return avatarByAvatarsAvatarId;
    }

    public void setAvatarByAvatarsAvatarId(Avatar avatarByAvatarsAvatarId) {
        this.avatarByAvatarsAvatarId = avatarByAvatarsAvatarId;
    }

    @ManyToOne
    @JoinColumn(name = "Role_role_id", referencedColumnName = "role_id", nullable = false)
    public Role getRoleByRoleRoleId() {
        return roleByRoleRoleId;
    }

    public void setRoleByRoleRoleId(Role roleByRoleRoleId) {
        this.roleByRoleRoleId = roleByRoleRoleId;
    }
}
