package com.bartosektom.letsplayfolks.service.impl;

import com.bartosektom.letsplayfolks.entity.Report;
import com.bartosektom.letsplayfolks.entity.User;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import com.bartosektom.letsplayfolks.manager.SessionManager;
import com.bartosektom.letsplayfolks.model.ReportModel;
import com.bartosektom.letsplayfolks.repository.ReportReasonRepository;
import com.bartosektom.letsplayfolks.repository.ReportRepository;
import com.bartosektom.letsplayfolks.repository.UserRepository;
import com.bartosektom.letsplayfolks.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    SessionManager sessionManager;

    @Autowired
    ReportReasonRepository reportReasonRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReportRepository reportRepository;

    @Override
    public ReportModel prepareReportModel(Integer userId) throws EntityNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        ReportModel reportModel = new ReportModel();
        reportModel.setUserId(userId);
        reportModel.setUserName(user.getUserName());

        return reportModel;
    }

    @Override
    public void createReportFromModel(ReportModel reportModel) throws EntityNotFoundException {
        Report report = new Report();
        report.setReportReasonByReportReasonId(reportReasonRepository.findById(reportModel.getReportReasonId()).orElseThrow(EntityNotFoundException::new));
        report.setDescription(reportModel.getDescription());
        User userFrom = userRepository.findById(sessionManager.getUserId()).orElseThrow(EntityNotFoundException::new);
        User userTo = userRepository.findById(reportModel.getUserId()).orElseThrow(EntityNotFoundException::new);
        report.setUserByFromUserId(userFrom);
        report.setUserByToUserId(userTo);
        reportRepository.save(report);
    }
}
