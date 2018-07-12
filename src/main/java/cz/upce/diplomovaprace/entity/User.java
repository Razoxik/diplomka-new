package cz.upce.diplomovaprace.entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by to068466 on 29.10.2017.
 */
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
    private int userRating;
    private Collection<Friend> friendsByUserId;
    private Collection<Friend> friendsByUserId_0;
    private Collection<Message> messagesOutbox;
    private Collection<Message> messagesInbox;
    private Collection<Report> reportsByUserId;
    private Collection<Report> reportsByUserId_0;
    private Collection<TeamUser> teamUsersByUserId;
    private Avatar avatarByAvatarsAvatarId;
    private Role roleByRoleRoleId;


    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "created", insertable = false)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "last_login")
    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Basic
    @Column(name = "user_rating")
    public int getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (created != null ? !created.equals(user.created) : user.created != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (nickname != null ? !nickname.equals(user.nickname) : user.nickname != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (lastLogin != null ? !lastLogin.equals(user.lastLogin) : user.lastLogin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Friend> getFriendsByUserId() {
        return friendsByUserId;
    }

    public void setFriendsByUserId(Collection<Friend> friendsByUserId) {
        this.friendsByUserId = friendsByUserId;
    }

    @OneToMany(mappedBy = "userByUserFriendId")
    public Collection<Friend> getFriendsByUserId_0() {
        return friendsByUserId_0;
    }

    public void setFriendsByUserId_0(Collection<Friend> friendsByUserId_0) {
        this.friendsByUserId_0 = friendsByUserId_0;
    }

    @OneToMany(mappedBy = "userByFromUserId")
    public Collection<Message> getMessagesOutbox() {
        return messagesOutbox;
    }

    public void setMessagesOutbox(Collection<Message> messagesOutbox) {
        this.messagesOutbox = messagesOutbox;
    }

    @OneToMany(mappedBy = "userByToUserId")
    public Collection<Message> getMessagesInbox() {
        return messagesInbox;
    }

    public void setMessagesInbox(Collection<Message> messagesInbox) {
        this.messagesInbox = messagesInbox;
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

    @OneToMany(mappedBy = "userByUserUserId")
    public Collection<TeamUser> getTeamUsersByUserId() {
        return teamUsersByUserId;
    }

    public void setTeamUsersByUserId(Collection<TeamUser> teamUsersByUserId) {
        this.teamUsersByUserId = teamUsersByUserId;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Avatars_avatar_id", referencedColumnName = "avatar_id", nullable = false)
    public Avatar getAvatarByAvatarsAvatarId() {
        return avatarByAvatarsAvatarId;
    }

    public void setAvatarByAvatarsAvatarId(Avatar avatarByAvatarsAvatarId) {
        this.avatarByAvatarsAvatarId = avatarByAvatarsAvatarId;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Role_role_id", referencedColumnName = "role_id", nullable = false)
    public Role getRoleByRoleRoleId() {
        return roleByRoleRoleId;
    }

    public void setRoleByRoleRoleId(Role roleByRoleRoleId) {
        this.roleByRoleRoleId = roleByRoleRoleId;
    }
}
