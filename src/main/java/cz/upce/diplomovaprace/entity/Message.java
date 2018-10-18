package cz.upce.diplomovaprace.entity;

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
    private int messageId;
    private Timestamp created;
    private String text;
    private String subject;


    private User userByFromUserId;
    private User userByToUserId;

    @Id
    @Column(name = "message_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
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
    @Column(name = "text", nullable = false, length = 500)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "subject", nullable = false, length = 45)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return messageId == message.messageId &&

                Objects.equals(created, message.created) &&
                Objects.equals(text, message.text) &&
                Objects.equals(subject, message.subject);
    }

    @Override
    public int hashCode() {

        return Objects.hash(messageId, created, text, subject );
    }

    @ManyToOne
    @JoinColumn(name = "from_user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByFromUserId() {
        return userByFromUserId;
    }

    public void setUserByFromUserId(User userByFromUserId) {
        this.userByFromUserId = userByFromUserId;
    }

    @ManyToOne
    @JoinColumn(name = "to_user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByToUserId() {
        return userByToUserId;
    }

    public void setUserByToUserId(User userByToUserId) {
        this.userByToUserId = userByToUserId;
    }
}
