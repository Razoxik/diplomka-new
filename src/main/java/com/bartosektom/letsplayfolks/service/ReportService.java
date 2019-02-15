package com.bartosektom.letsplayfolks.service;

import com.bartosektom.letsplayfolks.model.ReportModel;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;

public interface ReportService {

    ReportModel prepareReportModel(Integer userId) throws EntityNotFoundException;

    void createReportFromModel(ReportModel reportModel) throws EntityNotFoundException;
}
