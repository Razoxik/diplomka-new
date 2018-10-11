package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.enums.ActiveTab;
import cz.upce.diplomovaprace.model.ChallengeModel;
import cz.upce.diplomovaprace.repository.GameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class LeaderBoardController {
    @Autowired
    GameDao gameDao;

    @GetMapping("/leaderBoard")
    public ModelAndView renderMap(@ModelAttribute("challengeModel") ChallengeModel challengeModel, Map<String, Object> model) {
        model.put("activeTab", ActiveTab.LEADERBOARD.toString());
        model.put("games", gameDao.findAll());
        return new ModelAndView("leaderBoard", model);
    }
}
