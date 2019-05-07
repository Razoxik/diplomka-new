package com.bartosektom.letsplayfolks.controller;

import com.bartosektom.letsplayfolks.constants.ActiveTabConstants;
import com.bartosektom.letsplayfolks.constants.CommonConstants;
import com.bartosektom.letsplayfolks.entity.ChallengeResult;
import com.bartosektom.letsplayfolks.entity.User;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import com.bartosektom.letsplayfolks.repository.ChallengeResultRepository;
import com.bartosektom.letsplayfolks.repository.UserRepository;
import com.bartosektom.letsplayfolks.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/history")
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
@PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR', 'USER')")
public class HistoryController {

    private static final String HISTORY_LIST_VIEW_NAME = "/history/list";

    private static final String HISTORY_MODELS_KEY = "historyModels";

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChallengeResultRepository challengeResultRepository;

    @Autowired
    HistoryService historyService;

    @GetMapping("/list")
    public ModelAndView historyList(@RequestParam(CommonConstants.USER_ID) Integer userId,
                                    Map<String, Object> model) throws EntityNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        List<ChallengeResult> challengeResults = challengeResultRepository.findByUserByUserId(user);

        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.HISTORY);
        model.put(HISTORY_MODELS_KEY, historyService.prepareHistoryModels(challengeResults));

        return new ModelAndView(HISTORY_LIST_VIEW_NAME, model);
    }
}
