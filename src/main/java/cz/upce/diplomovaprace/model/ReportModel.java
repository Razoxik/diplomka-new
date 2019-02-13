package cz.upce.diplomovaprace.model;

// v DP popsat anotace jako creation timestamp a entitiy generate apod.
public class ReportModel {

    private String userId;
    private String userName;
    private Integer reportReasonId;
    private String description;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getReportReasonId() {
        return reportReasonId;
    }

    public void setReportReasonId(Integer reportReasonId) {
        this.reportReasonId = reportReasonId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
