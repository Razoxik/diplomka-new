package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.constants.ActiveTabConstants;
import cz.upce.diplomovaprace.model.ChallengeModel;
import cz.upce.diplomovaprace.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
public class LeaderboardController {
    @Autowired
    GameRepository gameRepository;

    @GetMapping("/leaderboard")
    public ModelAndView renderMap(@ModelAttribute("challengeModel") ChallengeModel challengeModel, Map<String, Object> model) {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.LEADERBOARD);
        model.put("games", gameRepository.findAll());
        return new ModelAndView("leaderBoard", model);
    }

    @PostMapping("/leaderboard")
    public ModelAndView renderLeaderboard(/*@ModelAttribute("challengeModel") ChallengeModel challengeModel,*/ Map<String, Object> model) {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.LEADERBOARD);
        model.put("games", gameRepository.findAll());
        return new ModelAndView("leaderBoard", model);
    }
}
