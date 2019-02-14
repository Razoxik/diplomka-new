package com.bartosektom.letsplayfolks.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Report {
    private Integer id;
    private Timestamp created;
    private String description;
    private User userByFromUserId;
    private User userByToUserId;
    private ReportReason reportReasonByReportReasonId;

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
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id) &&

                Objects.equals(created, report.created) &&
                Objects.equals(description, report.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, created, description);
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

    @ManyToOne
    @JoinColumn(name = "reportReasonId", referencedColumnName = "id", nullable = false)
    public ReportReason getReportReasonByReportReasonId() {
        return reportReasonByReportReasonId;
    }

    public void setReportReasonByReportReasonId(ReportReason reportReasonByReportReasonId) {
        this.reportReasonByReportReasonId = reportReasonByReportReasonId;
    }
}
