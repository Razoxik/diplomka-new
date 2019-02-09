package cz.upce.diplomovaprace.model;

import cz.upce.diplomovaprace.constants.QuestionableChallengeReason;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class QuestionableChallengeModel implements Serializable {

    private static final long serialVersionUID = 2985470791387077323L;

    private long id;
    private String gameName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime created;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime end;
    private QuestionableChallengeReason reason;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public QuestionableChallengeReason getReason() {
        return reason;
    }

    public void setReason(QuestionableChallengeReason reason) {
        this.reason = reason;
    }
}
