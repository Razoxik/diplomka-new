package cz.upce.diplomovaprace.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class User {
    private Integer id;
    private Timestamp created;
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String aboutMe;
    private Timestamp lastLogin;
    private Collection<ChallengeResult> challengeResultsById;
    private Collection<Friend> friendsByFromUserId;
    private Collection<Friend> friendsByToUserId;
    private Collection<Message> messagesByFromUserId;
    private Collection<Message> messagesByToUserId;
    private Collection<Rating> ratingsById;
    private Collection<Report> reportsByFromUserId;
    private Collection<Report> reportsByToUserId;
    private Avatar avatarByAvatarId;
    private Role roleByRoleId;

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

    @Basic
    @Column(name = "userName", nullable = false, length = 45)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    @Column(name = "aboutMe", nullable = true, length = 255)
    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    @Basic
    @Column(name = "last_login", nullable = true)
    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&

                Objects.equals(created, user.created) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(lastLogin, user.lastLogin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, created, userName, password, email, firstName, lastName, lastLogin);
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<ChallengeResult> getChallengeResultsById() {
        return challengeResultsById;
    }

    public void setChallengeResultsById(Collection<ChallengeResult> challengeResultsById) {
        this.challengeResultsById = challengeResultsById;
    }

    @OneToMany(mappedBy = "userByFromUserId")
    public Collection<Friend> getFriendsByFromUserId() {
        return friendsByFromUserId;
    }

    public void setFriendsByFromUserId(Collection<Friend> friendsByFromUserId) {
        this.friendsByFromUserId = friendsByFromUserId;
    }

    @OneToMany(mappedBy = "userByToUserId")
    public Collection<Friend> getFriendsByToUserId() {
        return friendsByToUserId;
    }

    public void setFriendsByToUserId(Collection<Friend> friendsByToUserId) {
        this.friendsByToUserId = friendsByToUserId;
    }

    @OneToMany(mappedBy = "userByFromUserId")
    public Collection<Message> getMessagesByFromUserId() {
        return messagesByFromUserId;
    }

    public void setMessagesByFromUserId(Collection<Message> messagesByFromUserId) {
        this.messagesByFromUserId = messagesByFromUserId;
    }

    @OneToMany(mappedBy = "userByToUserId")
    public Collection<Message> getMessagesByToUserId() {
        return messagesByToUserId;
    }

    public void setMessagesByToUserId(Collection<Message> messagesByToUserId) {
        this.messagesByToUserId = messagesByToUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Rating> getRatingsById() {
        return ratingsById;
    }

    public void setRatingsById(Collection<Rating> ratingsById) {
        this.ratingsById = ratingsById;
    }

    @OneToMany(mappedBy = "userByFromUserId")
    public Collection<Report> getReportsByFromUserId() {
        return reportsByFromUserId;
    }

    public void setReportsByFromUserId(Collection<Report> reportsByFromUserId) {
        this.reportsByFromUserId = reportsByFromUserId;
    }

    @OneToMany(mappedBy = "userByToUserId")
    public Collection<Report> getReportsByToUserId() {
        return reportsByToUserId;
    }

    public void setReportsByToUserId(Collection<Report> reportsByToUserId) {
        this.reportsByToUserId = reportsByToUserId;
    }

    @ManyToOne
    @JoinColumn(name = "avatarId", referencedColumnName = "id", nullable = false)
    public Avatar getAvatarByAvatarId() {
        return avatarByAvatarId;
    }

    public void setAvatarByAvatarId(Avatar avatarByAvatarId) {
        this.avatarByAvatarId = avatarByAvatarId;
    }

    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "id", nullable = false)
    public Role getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(Role roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }
}
