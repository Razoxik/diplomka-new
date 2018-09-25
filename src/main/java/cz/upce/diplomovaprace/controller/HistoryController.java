package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.repository.ChallengeDao;
import cz.upce.diplomovaprace.repository.TeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class HistoryController {

    @Autowired
    ChallengeDao challengeDao;


    @Autowired
    TeamDao teamDao;


    @GetMapping("/history")
    public ModelAndView renderMap(Map<String, Object> model) {
        model.put("challenges", challengeDao.findChallengeByTeamByChallengerTeamId(teamDao.findById(1)));
        model.put("activeTab", "History");
        return new ModelAndView("history", model);
    }
}
