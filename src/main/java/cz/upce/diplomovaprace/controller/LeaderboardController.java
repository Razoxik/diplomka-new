package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.constants.ActiveTabConstants;
import cz.upce.diplomovaprace.exception.EntityNotFoundException;
import cz.upce.diplomovaprace.repository.GameRepository;
import cz.upce.diplomovaprace.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/leaderboard")
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
public class LeaderboardController {

    private static final String LEADERBOARD_LIST_VIEW_NAME = "/leaderboard/list";

    private static final String GAME_ID_REQUEST_PARAM = "gameId";

    private static final String GAMES_MODEL_KEY = "games";
    private static final String LEADERBOARD_MODELS_MODEL_KEY = "leaderboardModels";

    @Autowired
    GameRepository gameRepository;

    @Autowired
    LeaderboardService leaderboardService;

    @GetMapping("/list")
    public ModelAndView leaderboardList(@RequestParam(value = GAME_ID_REQUEST_PARAM, defaultValue = "1") Integer gameId,
                                        Map<String, Object> model) throws EntityNotFoundException {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.LEADERBOARD);
        model.put(LEADERBOARD_MODELS_MODEL_KEY, leaderboardService.prepareLeaderboardModels(gameId));
        model.put(GAMES_MODEL_KEY, gameRepository.findAll());

        return new ModelAndView(LEADERBOARD_LIST_VIEW_NAME, model);
    }
}
