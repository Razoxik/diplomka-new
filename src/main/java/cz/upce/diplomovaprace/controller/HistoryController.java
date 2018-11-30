package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.constants.ActiveTabConstants;
import cz.upce.diplomovaprace.entity.Challenge;
import cz.upce.diplomovaprace.entity.ChallengeResult;
import cz.upce.diplomovaprace.entity.Game;
import cz.upce.diplomovaprace.entity.ResultState;
import cz.upce.diplomovaprace.entity.User;
import cz.upce.diplomovaprace.exception.EntityNotFoundException;
import cz.upce.diplomovaprace.model.HistoryModel;
import cz.upce.diplomovaprace.repository.ChallengeResultRepository;
import cz.upce.diplomovaprace.repository.UserRepository;
import cz.upce.diplomovaprace.tools.LocalDateTimeJPAConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    @GetMapping("/list")
    public ModelAndView historyList(@RequestParam(value = "userId") Integer userId,
                                    Map<String, Object> model) throws EntityNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        List<ChallengeResult> challengeResults = challengeResultRepository.findByUserByUserId(user);

        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.HISTORY);
        model.put(HISTORY_MODELS_KEY, prepareHistoryModels(challengeResults));

        return new ModelAndView(HISTORY_LIST_VIEW_NAME, model);
    }

    private HistoryModel prepareHistoryModel(ChallengeResult challengeResult) {
        Challenge challenge = challengeResult.getChallengeByChallengeId();
        Game game = challenge.getGameByGameId();
        ResultState resultState = challengeResult.getResultStateByResultStateId();

        Integer challengeId = challenge.getId();
        String gameName = game.getName();
        LocalDateTimeJPAConverter converter = new LocalDateTimeJPAConverter();
        LocalDateTime startDate = converter.convertToEntityAttribute(challenge.getStart());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String formattedStartDate = startDate.format(formatter);
        String result = resultState.getState();
        Integer scoreWinner = challengeResult.getScoreWinner();
        Integer scoreLooser = challengeResult.getScoreDefeated();

        HistoryModel historyModel = new HistoryModel();
        historyModel.setChallengeId(challengeId);
        historyModel.setStart(formattedStartDate);
        historyModel.setGameName(gameName);
        historyModel.setResultState(result);
        historyModel.setScoreWinner(scoreWinner);
        historyModel.setScoreLooser(scoreLooser);

        return historyModel;
    }

    private List<HistoryModel> prepareHistoryModels(List<ChallengeResult> challengeResults) {
        List<HistoryModel> historyModels = new ArrayList<>();
        for (ChallengeResult challengeResult : challengeResults) {
            HistoryModel historyModel = prepareHistoryModel(challengeResult);
            historyModels.add(historyModel);
        }
        return historyModels;
    }
}
