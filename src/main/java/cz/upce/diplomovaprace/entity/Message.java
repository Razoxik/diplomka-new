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
public class Message {
    private int messageId;
    private Timestamp created;
    private String text;
    private String subject;
    private User userByFromUserId;
    private User userByToUserId;

    @Id
    @Column(name = "message_id")
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
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
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "subject")
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

        if (messageId != message.messageId) return false;
        if (created != null ? !created.equals(message.created) : message.created != null) return false;
        if (text != null ? !text.equals(message.text) : message.text != null) return false;
        if (subject != null ? !subject.equals(message.subject) : message.subject != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = messageId;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        return result;
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
