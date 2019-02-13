package cz.upce.diplomovaprace.service.impl;

import cz.upce.diplomovaprace.entity.Report;
import cz.upce.diplomovaprace.entity.ReportReason;
import cz.upce.diplomovaprace.entity.User;
import cz.upce.diplomovaprace.exception.EntityNotFoundException;
import cz.upce.diplomovaprace.model.ReportModel;
import cz.upce.diplomovaprace.repository.ReportReasonRepository;
import cz.upce.diplomovaprace.repository.UserRepository;
import cz.upce.diplomovaprace.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportReasonRepository reportReasonRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public ReportModel prepareReportModel(Integer userId) throws EntityNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Iterable<ReportReason> reportReasons = reportReasonRepository.findAll();
        ReportModel reportModel = new ReportModel();
        return reportModel;
    }

    @Override
    public void createReport(ReportModel reportModel) {
        Report report = new Report();


    }
}
