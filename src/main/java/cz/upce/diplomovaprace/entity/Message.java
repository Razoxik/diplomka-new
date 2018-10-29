package cz.upce.diplomovaprace.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Message {
    private Integer id;
    private Timestamp created;
    private String subject;
    private String text;
    private User userByFromUserId;
    private User userByToUserId;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "subject", nullable = false, length = 45)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "text", nullable = true, length = 255)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) &&

                Objects.equals(created, message.created) &&
                Objects.equals(subject, message.subject) &&
                Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, created, subject, text);
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
