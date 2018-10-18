package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.enums.ActiveTabConstants;
import cz.upce.diplomovaprace.repository.ChallengeRepository;
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

    @GetMapping("/history")
    public ModelAndView renderMap(Map<String, Object> model) {

        //model.put("challenges", challengeRepository.findChallengesByChallengerUserId(1).addAll(challengeRepository.findChallengesByOponnentUserId(1)));
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.HISTORY);
        return new ModelAndView("history", model);
    }
}
