package com.bartosektom.letsplayfolks.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MessageModel implements Serializable {

    private static final long serialVersionUID = -3370995219897368060L;

    private Integer id;
    private String author;
    private String subject;
    private String text;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime sentDate;
    private String formattedSentDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    public String getFormattedSentDate() {
        return formattedSentDate;
    }

    public void setFormattedSentDate(String formattedSentDate) {
        this.formattedSentDate = formattedSentDate;
    }
}
