package com.bartosektom.letsplayfolks.model;

import java.io.Serializable;

public class ReportModel implements Serializable {

    private static final long serialVersionUID = 5318286257420620135L;

    private Integer userId;
    private String userName;
    private Integer reportReasonId;
    private String description;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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
