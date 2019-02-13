package cz.upce.diplomovaprace.service;

import cz.upce.diplomovaprace.exception.EntityNotFoundException;
import cz.upce.diplomovaprace.model.ReportModel;

public interface ReportService {

    ReportModel prepareReportModel(Integer userId) throws EntityNotFoundException;

    void createReport(ReportModel reportModel);
}
