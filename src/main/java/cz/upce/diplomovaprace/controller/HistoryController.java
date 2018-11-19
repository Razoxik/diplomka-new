package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.constants.ActiveTabConstants;
import cz.upce.diplomovaprace.entity.User;
import cz.upce.diplomovaprace.exception.EntityNotFoundException;
import cz.upce.diplomovaprace.manager.SessionManager;
import cz.upce.diplomovaprace.repository.ChallengeRepository;
import cz.upce.diplomovaprace.repository.ChallengeResultRepository;
import cz.upce.diplomovaprace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
public class HistoryController {

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChallengeResultRepository challengeResultRepository;

    @Autowired
    SessionManager sessionManager;

    @GetMapping("/history")
    public ModelAndView renderMap(Map<String, Object> model) throws EntityNotFoundException {
        User user = userRepository.findById(sessionManager.getUserId()).orElseThrow(EntityNotFoundException::new);
        model.put("challengeResults", challengeResultRepository.findByUserByUserId(user));
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.HISTORY);
        return new ModelAndView("history", model);
    }
}
