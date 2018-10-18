package cz.upce.diplomovaprace.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Report {
    private int reportId;
    private Timestamp created;
    private String reason;
    private String reasonText;

    private User userByReportingUserId;
    private User userByReportedUserId;

    @Id
    @Column(name = "report_id", nullable = false)
    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
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
    @Column(name = "reason", nullable = false, length = 45)
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Basic
    @Column(name = "reason_text", nullable = true, length = 255)
    public String getReasonText() {
        return reasonText;
    }

    public void setReasonText(String reasonText) {
        this.reasonText = reasonText;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return reportId == report.reportId &&
              Objects.equals(created, report.created) &&
                Objects.equals(reason, report.reason) &&
                Objects.equals(reasonText, report.reasonText);
    }

    @Override
    public int hashCode() {

        return Objects.hash(reportId, created, reason, reasonText );
    }

    @ManyToOne
    @JoinColumn(name = "reporting_user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByReportingUserId() {
        return userByReportingUserId;
    }

    public void setUserByReportingUserId(User userByReportingUserId) {
        this.userByReportingUserId = userByReportingUserId;
    }

    @ManyToOne
    @JoinColumn(name = "reported_user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByReportedUserId() {
        return userByReportedUserId;
    }

    public void setUserByReportedUserId(User userByReportedUserId) {
        this.userByReportedUserId = userByReportedUserId;
    }
}
