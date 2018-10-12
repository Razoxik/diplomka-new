package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.entity.Challenge;
import cz.upce.diplomovaprace.enums.ActiveTabConstants;
import cz.upce.diplomovaprace.model.ChallengeModel;
import cz.upce.diplomovaprace.repository.ChallengeDao;
import cz.upce.diplomovaprace.repository.GameDao;
import cz.upce.diplomovaprace.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/challenge")
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
public class ChallengeController {

    @Autowired
    ChallengeDao challengeDao;

    @Autowired
    UserDao userDao;

    @Autowired
    GameDao gameDao;

    @GetMapping("/create")
    public ModelAndView renderMap(@RequestParam("latCoords") String latCoords, @RequestParam("lngCoords") String lngCoords,
                                  @ModelAttribute("challengeModel") ChallengeModel challengeModel, BindingResult bindingResult, Map<String, Object> model) {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.MAP);
        challengeModel.setLatCoords(latCoords);
        challengeModel.setLngCoords(lngCoords);
        model.put("games", gameDao.findAll());
        return new ModelAndView("createChallenge", model);
    }

    @PostMapping("/create")
    public ModelAndView renderMaps(@ModelAttribute("challengeModel") ChallengeModel challengeModel, BindingResult bindingResult,
                                   Map<String, Object> model) {
        Challenge challenge = new Challenge();
        Random random = new Random();
        challenge.setChallengeId(random.nextInt(900) + 100);
        challenge.setChallengeStart(Timestamp.valueOf("1992-04-10 10:10:11"));
        challenge.setChallengeEnd(Timestamp.valueOf("1992-04-10 10:10:11"));
        challenge.setCoordsLat(challengeModel.getLatCoords());
        challenge.setCoordsLng(challengeModel.getLngCoords());
        challenge.setUserByChallengerUserId(userDao.findById(1).get());
        challenge.setUserByOponnentUserId(userDao.findById(1).get());
        challenge.setText("asd");
        challengeDao.save(challenge);
        return new ModelAndView("redirect:/map", model);
    }
}
