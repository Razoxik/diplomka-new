package com.bartosektom.letsplayfolks.controller;

import com.bartosektom.letsplayfolks.model.ReportModel;
import com.bartosektom.letsplayfolks.service.ReportService;
import com.bartosektom.letsplayfolks.constants.ActiveTabConstants;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import com.bartosektom.letsplayfolks.repository.ReportReasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
@PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR', 'USER')")
public class ReportController {

    private static final String USER_ID_REQUEST_PARAM = "userId";

    private static final String REPORT_VIEW_NAME = "/report/report";

    private static final String REPORT_REASONS_MODEL_KEY = "reportReasons";

    private static final String REPORT_MODEL_KEY = "reportModel";
    @Autowired
    ReportReasonRepository reportReasonRepository;

    @Autowired
    ReportService reportService;

    @GetMapping("/report")
    public ModelAndView reportUser(@RequestParam(value = USER_ID_REQUEST_PARAM) Integer userId, Map<String, Object> model,
                                   RedirectAttributes redirectAttributes, HttpServletRequest request) throws EntityNotFoundException {

        model.put(REPORT_REASONS_MODEL_KEY, reportReasonRepository.findAll());
        model.put(REPORT_MODEL_KEY, reportService.prepareReportModel(userId));

        return new ModelAndView(REPORT_VIEW_NAME, model);
    }

    @PostMapping("/report")
    public ModelAndView submitReportUser(@ModelAttribute(REPORT_MODEL_KEY) ReportModel reportModel, Map<String, Object> model,
                                         RedirectAttributes redirectAttributes, HttpServletRequest request) throws EntityNotFoundException {

        reportService.createReport(reportModel);

        return new ModelAndView(REPORT_VIEW_NAME, model);
    }
}
