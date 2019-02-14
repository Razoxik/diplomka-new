package com.bartosektom.letsplayfolks.controller;

import com.bartosektom.letsplayfolks.ChallengeResultValidator;
import com.bartosektom.letsplayfolks.constants.GameConstants;
import com.bartosektom.letsplayfolks.exception.UnexceptedChallengeException;
import com.bartosektom.letsplayfolks.manager.SessionManager;
import com.bartosektom.letsplayfolks.model.ChallengeDetailModel;
import com.bartosektom.letsplayfolks.model.ChallengeDetailUserModel;
import com.bartosektom.letsplayfolks.model.ChallengeResultModel;
import com.bartosektom.letsplayfolks.repository.ChallengeStateRepository;
import com.bartosektom.letsplayfolks.repository.ResultStateRepository;
import com.bartosektom.letsplayfolks.repository.UserRepository;
import com.bartosektom.letsplayfolks.constants.ActiveTabConstants;
import com.bartosektom.letsplayfolks.constants.ChallengeStateConstants;
import com.bartosektom.letsplayfolks.constants.GameParamConstants;
import com.bartosektom.letsplayfolks.constants.ResultStateConstants;
import com.bartosektom.letsplayfolks.entity.Challenge;
import com.bartosektom.letsplayfolks.entity.ChallengeResult;
import com.bartosektom.letsplayfolks.entity.ChallengeState;
import com.bartosektom.letsplayfolks.entity.Game;
import com.bartosektom.letsplayfolks.entity.ResultState;
import com.bartosektom.letsplayfolks.entity.User;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import com.bartosektom.letsplayfolks.model.ChallengeModel;
import com.bartosektom.letsplayfolks.repository.ChallengeRepository;
import com.bartosektom.letsplayfolks.repository.ChallengeResultRepository;
import com.bartosektom.letsplayfolks.repository.GameParamRepository;
import com.bartosektom.letsplayfolks.repository.GameRepository;
import com.bartosektom.letsplayfolks.repository.RatingRepository;
import com.bartosektom.letsplayfolks.service.ChallengeService;
import io.micrometer.core.lang.NonNull;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/challenge")
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
public class ChallengeController {

    private static final String CHALLENGE_ID_REQUEST_PARAM = "challengeId";
    private static final String LAT_COORDS_REQUEST_PARAM = "latCoords";
    private static final String LNG_COORDS_REQUEST_PARAM = "lngCoords";

    private static final String CHALLENGE_MODEL_ATTRIBUTE = "challengeModel";
    private static final String CHALLENGE_RESULT_MODEL_ATTRIBUTE = "challengeResultModel";

    private static final String CHALLENGE_MODEL_KEY = "challenge";
    private static final String GAMES_MODEL_KEY = "games";
    private static final String CHALLENGE_DETAIL_MODEL_KEY = "challengeDetailModel";
    private static final String QUESTIONABLE_CHALLENGES_MODEL_KEY = "questionableChallenges";

    private static final String CHALLENGE_RESULT_VIEW_NAME = "challenge/result";
    private static final String CHALLENGE_DETAIL_VIEW_NAME = "challenge/detail";
    private static final String CHALLENGE_CREATE_VIEW_NAME = "challenge/create";
    private static final String CHALLENGE_QUESTIONABLE_LIST_VIEW_NAME = "challenge/questionable/list";

    private static final String IS_USER_ALREADY_IN_CHALLENGE_MODEL_KEY = "isUserAlreadyInChallenge";
    private static final String IS_CHALLENGE_FINISHED_MODEL_KEY = "isChallengeFinished";

    @Autowired
    private ChallengeResultValidator challengeResultValidator;

    @InitBinder("challengeResultModel")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(challengeResultValidator);
    }

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    SessionManager sessionManager;

    @Autowired
    ChallengeStateRepository challengeStateRepository;

    @Autowired
    ResultStateRepository resultStateRepository;

    @Autowired
    ChallengeResultRepository challengeResultRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    GameParamRepository gameParamRepository;

    @Autowired
    ChallengeService challengeService;


    @GetMapping("/create")
    public ModelAndView challengeCreate(@RequestParam(LAT_COORDS_REQUEST_PARAM) String latCoords,
                                        @RequestParam(LNG_COORDS_REQUEST_PARAM) String lngCoords,
                                        @ModelAttribute(CHALLENGE_MODEL_ATTRIBUTE) ChallengeModel challengeModel,
                                        Map<String, Object> model) {
        challengeModel.setLatCoords(latCoords);
        challengeModel.setLngCoords(lngCoords);

        List<Game> games = gameRepository.findByApproved(GameConstants.GAME_APPROVED);
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.MAP);
        model.put(GAMES_MODEL_KEY, games);

        return new ModelAndView(CHALLENGE_CREATE_VIEW_NAME, model);
    }

    //Controller calls service. Service returns an object (be it a DTO, domain model or something else)
    @GetMapping("/detail")
    public ModelAndView challengeDetail(@RequestParam(CHALLENGE_ID_REQUEST_PARAM) int challengeId,
                                        @RequestParam(value = "infoMessage", required = false) String infoMessage,
                                        Map<String, Object> model) throws EntityNotFoundException {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(EntityNotFoundException::new);

        ChallengeDetailModel challengeDetailModel = prepareChallengeDetailDto(challenge);
        boolean isUserAlreadyInChallenge = challengeService.isUserAlreadyInChallenge(challenge);
        boolean isChallengeFinished = challengeService.isChallengeFinished(challenge);
        boolean canUserEnterResult = challengeService.canUserEnterResult(challenge);

        model.put(CHALLENGE_DETAIL_MODEL_KEY, challengeDetailModel);
        model.put(CHALLENGE_MODEL_KEY, challenge);
        model.put(IS_USER_ALREADY_IN_CHALLENGE_MODEL_KEY, isUserAlreadyInChallenge);
        model.put(IS_CHALLENGE_FINISHED_MODEL_KEY, isChallengeFinished);
        model.put("canUserEnterResult", canUserEnterResult);
        model.put("infoMessage", infoMessage);
        // AND CHALLENGE IS NOT FULL!!! NA JOIN/ODHLASIT SE!!!

        return new ModelAndView(CHALLENGE_DETAIL_VIEW_NAME, model);
    }


    @GetMapping("/enterResult")
    public ModelAndView challengeEnterResult(@RequestParam(value = "infoMessage", required = false) String infoMessage,
                                             @RequestParam(CHALLENGE_ID_REQUEST_PARAM) int challengeId,
                                             @RequestParam(value = "challengeUserId", required = false) Integer challengeUserId,
                                             @ModelAttribute(CHALLENGE_RESULT_MODEL_ATTRIBUTE) ChallengeResultModel challengeResultModel,
                                             Map<String, Object> model) throws EntityNotFoundException {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(EntityNotFoundException::new);
        model.put(CHALLENGE_MODEL_KEY, challenge);
        model.put("challengeUserId", challengeUserId);
        model.put("infoMessage", infoMessage);

        return new ModelAndView(CHALLENGE_RESULT_VIEW_NAME, model);
    }

    // kontrola na BE pres bindingResult.hasErrors()?
    @PostMapping("/submitResult")
    public ModelAndView challengeSubmitResult(@RequestParam(CHALLENGE_ID_REQUEST_PARAM) int challengeId,
                                              @ModelAttribute(CHALLENGE_RESULT_MODEL_ATTRIBUTE) @Validated ChallengeResultModel challengeResultModel, BindingResult bindingResult,
                                              RedirectAttributes redirectAttributes,
                                              HttpServletRequest request) throws EntityNotFoundException, UnexceptedChallengeException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addAttribute("infoMessage", bindingResult.getGlobalErrors().get(0).getDefaultMessage());
            redirectAttributes.addAttribute("challengeId", challengeId);
            redirectAttributes.addAttribute("challengeUserId", challengeResultModel.getChallengeUserId());
            return new ModelAndView("redirect:/challenge/enterResult");
        }

        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(EntityNotFoundException::new);
        int userId = challengeResultModel.getChallengeUserId() == null ? sessionManager.getUserId() : challengeResultModel.getChallengeUserId();
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        //ChallengeResult challengeResult = challengeResultRepository.findByUserByUserIdAndChallengeByChallengeId(user, challenge);

        challengeService.finishChallenge(user, challenge, challengeResultModel);
/*
        // RECALCULATE RATING OF EVERY PLAYER IN CHALLENGE
        List<ChallengeResult> challengeResults = challengeResultRepository.findByChallengeByChallengeId(challenge);
        List<User> users = challengeResults.stream().map(ChallengeResult::getUserByUserId).collect(Collectors.toList());
        Game game = challenge.getGameByGameId();
        int firstTeamRating = 0;
        int secondTeamRating = 0;
        for (int i = 0; i < users.size(); i++) {
            if (i % 2 == 0) {
                firstTeamRating += ratingRepository.findByUserByUserIdAndGameByGameId(user, game).getRating();
            } else {
                secondTeamRating += ratingRepository.findByUserByUserIdAndGameByGameId(user, game).getRating();
            }
        }
        String resultState = null;
        int winnerScore = challengeResultModel.getWinnerScore();
        int looserScore = challengeResultModel.getLoserScore();
        Rating rating = ratingRepository.findByUserByUserIdAndGameByGameId(user, game);
        switch (challengeResultModel.getResultState()) {
            case "WINNER":
                resultState = ResultStateConstants.WINNER;
                winnerScore = challengeResultModel.getWinnerScore();
                looserScore = challengeResultModel.getLoserScore();
                rating.setRating(rating.getRating() + Math.abs((rating.getRating() - secondTeamRating - 18))); // magic constant
                break;
            case "DEFEATED":
                resultState = ResultStateConstants.DEFEATED;
                winnerScore = challengeResultModel.getLoserScore();
                looserScore = challengeResultModel.getWinnerScore();
                rating.setRating(rating.getRating() - Math.abs((rating.getRating() - secondTeamRating - 18))); // magic constant
                break;
            case "TIE":
                resultState = ResultStateConstants.TIE;
                // rating.setRating(rating.getRating() - Math.abs((rating.getRating() - secondTeamRating)));
                break;
            default:
                break;
        }
        ratingRepository.save(rating);
        // ten rating počítat až všichni zadaj STEJNY výsledek do té doby ne! To nějak vymyslet, to bude mít na starosti operátor
        // if every challenge result have same score return true, else false
        // if true, set rating; else nothing
        // operator uvidi ty kde ta metoda vrati false a bude tam moct rucne zadat vysledky a finishnout challenge

        challengeResult.setResultStateByResultStateId(resultStateRepository.findByState(resultState));
        challengeResult.setScoreWinner(winnerScore);
        challengeResult.setScoreDefeated(looserScore);
        challengeResultRepository.save(challengeResult);

        int maxPlayers = Integer.parseInt(gameParamRepository.findByGameByGameIdAndName(
                challenge.getGameByGameId(), GameParamConstants.NUMBER_OF_PLAYERS).getValue());
        if (challengeResultRepository.findByChallengeByChallengeId(challenge)
                .stream().filter(challengeResult1 -> !challengeResult1.getResultStateByResultStateId().getState()
                        .equals(ResultStateConstants.IN_PROGRESS)).count() == maxPlayers) {
            challenge.setChallengeStateByChallengeStateId(challengeStateRepository.findByState(ChallengeStateConstants.FINISHED));
            challengeRepository.save(challenge);
            // RECALCULATE RATING OF EVERY PLAYER IN CHALLENGE
        }*/
        return redirectToChallengeDetail(challengeId, redirectAttributes);
    }

    @PostMapping("/create")
    public ModelAndView challengeCreate(@ModelAttribute(CHALLENGE_MODEL_ATTRIBUTE) ChallengeModel challengeModel,
                                        BindingResult bindingResult) throws EntityNotFoundException {
        if (bindingResult.hasErrors()) {
            // TODO: Validation
        }

        Challenge challenge = new Challenge();
        ChallengeState challengeState = challengeStateRepository.findByState(ChallengeStateConstants.CREATED);
        Game game = gameRepository.findById(challengeModel.getGameId()).orElseThrow(() -> new EntityNotFoundException("Game does not found"));

        challenge.setChallengeStateByChallengeStateId(challengeState);
        challenge.setGameByGameId(game);
        challenge.setStart(Timestamp.valueOf(challengeModel.getStart()));
        challenge.setEnd(Timestamp.valueOf(challengeModel.getEnd()));
        challenge.setCoordsLat(challengeModel.getLatCoords());
        challenge.setCoordsLng(challengeModel.getLngCoords());
        challenge.setDescription(challengeModel.getDescription());
        challenge.setPassword(challengeModel.getPassword());
        challengeRepository.save(challenge);

        ChallengeResult challengeResult = new ChallengeResult();
        User user = userRepository.findById(sessionManager.getUserId()).orElseThrow(EntityNotFoundException::new);
        ResultState resultState = resultStateRepository.findByState(ResultStateConstants.IN_PROGRESS);

        challengeResult.setChallengeByChallengeId(challenge);
        challengeResult.setUserByUserId(user);
        challengeResult.setResultStateByResultStateId(resultState);
        challengeResultRepository.save(challengeResult);

        return new ModelAndView("redirect:/map");
    }

    @GetMapping("/join")
    public ModelAndView challengeJoin(@RequestParam(CHALLENGE_ID_REQUEST_PARAM) int challengeId,
                                      RedirectAttributes redirectAttributes) throws EntityNotFoundException {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(sessionManager.getUserId()).orElseThrow(EntityNotFoundException::new);
        ResultState resultState = resultStateRepository.findByState(ResultStateConstants.IN_PROGRESS);

        ChallengeResult challengeResult = new ChallengeResult();
        challengeResult.setChallengeByChallengeId(challenge);
        challengeResult.setUserByUserId(user);
        challengeResult.setResultStateByResultStateId(resultState);
        challengeResultRepository.save(challengeResult);

        // IF number of people in game is ten počet max
        // IF je po START čase vyzvy
        challenge.setChallengeStateByChallengeStateId(challengeStateRepository.findByState(ChallengeStateConstants.IN_PROGRESS));
        challengeRepository.save(challenge);

        return redirectToChallengeDetail(challengeId, redirectAttributes);
    }

    @GetMapping("/logout")
    public ModelAndView challengeLogout(@RequestParam(CHALLENGE_ID_REQUEST_PARAM) int challengeId,
                                        RedirectAttributes redirectAttributes) throws EntityNotFoundException {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(sessionManager.getUserId()).orElseThrow(EntityNotFoundException::new);

        ChallengeResult challengeResult = challengeResultRepository.findByUserByUserIdAndChallengeByChallengeId(user, challenge);
        challengeResultRepository.delete(challengeResult);

        // Pokud jsem poslední na výzvě a odcházím tak smazat.
        if (challengeResultRepository.findByChallengeByChallengeId(challenge).isEmpty()) {
            challengeRepository.delete(challenge);
            return new ModelAndView("redirect:/map");
        }
        return redirectToChallengeDetail(challengeId, redirectAttributes);
    }

    @GetMapping("/questionable/list")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR')")
    public ModelAndView challengeQuestionableList(Map<String, Object> model) throws UnexceptedChallengeException {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.QUESTIONABLE_CHALLENGES);
        model.put(QUESTIONABLE_CHALLENGES_MODEL_KEY, challengeService.prepareQuestionableChallengeModels());

        return new ModelAndView(CHALLENGE_QUESTIONABLE_LIST_VIEW_NAME, model);
    }

    private ModelAndView redirectToChallengeDetail(@NonNull int challengeId, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(CHALLENGE_ID_REQUEST_PARAM, challengeId);
        return new ModelAndView("redirect:/challenge/detail");
    }

    private ChallengeDetailModel prepareChallengeDetailDto(Challenge challenge) throws EntityNotFoundException {
        Game game = challenge.getGameByGameId();

        ChallengeDetailModel challengeDetailModel = new ChallengeDetailModel();
        Pair<List<ChallengeDetailUserModel>, List<ChallengeDetailUserModel>> teamsDtos = challengeService.prepareTeamsDtos(challenge);
        int maxPlayers = Integer.parseInt(gameParamRepository.findByGameByGameIdAndName(
                game, GameParamConstants.NUMBER_OF_PLAYERS).getValue());
        challengeDetailModel.setFirstTeam(teamsDtos.getLeft());
        challengeDetailModel.setSecondTeam(teamsDtos.getRight());
        challengeDetailModel.setMaxPlayers(maxPlayers);

        return challengeDetailModel;
    }
}
