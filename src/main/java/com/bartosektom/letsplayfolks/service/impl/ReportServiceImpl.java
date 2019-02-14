package com.bartosektom.letsplayfolks.service.impl;

import com.bartosektom.letsplayfolks.model.ReportModel;
import com.bartosektom.letsplayfolks.repository.UserRepository;
import com.bartosektom.letsplayfolks.service.ReportService;
import com.bartosektom.letsplayfolks.entity.Report;
import com.bartosektom.letsplayfolks.entity.ReportReason;
import com.bartosektom.letsplayfolks.entity.User;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import com.bartosektom.letsplayfolks.repository.ReportReasonRepository;
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
