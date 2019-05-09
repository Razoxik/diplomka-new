package com.bartosektom.letsplayfolks.controller;

import com.bartosektom.letsplayfolks.constants.ActiveTabConstants;
import com.bartosektom.letsplayfolks.constants.CommonConstants;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import com.bartosektom.letsplayfolks.model.ReportModel;
import com.bartosektom.letsplayfolks.repository.ReportReasonRepository;
import com.bartosektom.letsplayfolks.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/report")
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
@PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR', 'USER')")
public class ReportController {

    private static final String REPORT_REASONS_MODEL_KEY = "reportReasons";
    private static final String REPORT_MODEL_KEY = "reportModel";

    private static final String REPORT_VIEW_NAME = "/report/report";

    @Autowired
    ReportReasonRepository reportReasonRepository;

    @Autowired
    ReportService reportService;

    @GetMapping("/report")
    public ModelAndView reportUser(@RequestParam(value = CommonConstants.INFO_MESSAGE, required = false) String infoMessage,
                                   @RequestParam(CommonConstants.USER_ID) Integer userId,
                                   Map<String, Object> model) throws EntityNotFoundException {
        model.put(REPORT_MODEL_KEY, reportService.prepareReportModel(userId));
        model.put(REPORT_REASONS_MODEL_KEY, reportReasonRepository.findAll());
        model.put(CommonConstants.INFO_MESSAGE, infoMessage);

        return new ModelAndView(REPORT_VIEW_NAME, model);
    }

    @PostMapping("/report")
    public ModelAndView submitReportUser(@ModelAttribute(REPORT_MODEL_KEY) ReportModel reportModel,
                                         RedirectAttributes redirectAttributes) throws EntityNotFoundException {
        reportService.createReportFromModel(reportModel);

        redirectAttributes.addAttribute(CommonConstants.USER_ID, reportModel.getUserId());
        redirectAttributes.addAttribute(CommonConstants.INFO_MESSAGE, "info.message.userReported");
        return new ModelAndView("redirect:/report/report");
    }
}
