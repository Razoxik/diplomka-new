package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.enums.ActiveTabConstants;
import cz.upce.diplomovaprace.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/admin")
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
public class AdminController {

    @Autowired
    GameRepository gameRepository;

    @GetMapping("/gameApproval")
    public ModelAndView renderOperatorChallenges(Map<String, Object> model) {

        model.put("games", gameRepository.findByApproved(0));
        return new ModelAndView("admin/gameApproval", model);
    }

}
