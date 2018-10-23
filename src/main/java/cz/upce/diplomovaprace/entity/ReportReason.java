package cz.upce.diplomovaprace.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Objects;

@Entity
public class ReportReason {
    private Integer id;
    private String reason;
    private Collection<Report> reportsById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "reason", nullable = false, length = 45)
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportReason that = (ReportReason) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, reason);
    }

    @OneToMany(mappedBy = "reportReasonByReportReasonId")
    public Collection<Report> getReportsById() {
        return reportsById;
    }

    public void setReportsById(Collection<Report> reportsById) {
        this.reportsById = reportsById;
    }
}
