package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.dto.ChallengeDetailDto;
import cz.upce.diplomovaprace.dto.UserDto;
import cz.upce.diplomovaprace.entity.Challenge;
import cz.upce.diplomovaprace.entity.ChallengeResult;
import cz.upce.diplomovaprace.entity.ChallengeState;
import cz.upce.diplomovaprace.entity.Game;
import cz.upce.diplomovaprace.entity.ResultState;
import cz.upce.diplomovaprace.entity.User;
import cz.upce.diplomovaprace.enums.ActiveTabConstants;
import cz.upce.diplomovaprace.enums.ChallengeStateConstants;
import cz.upce.diplomovaprace.enums.GameParamConstants;
import cz.upce.diplomovaprace.enums.ResultStateConstants;
import cz.upce.diplomovaprace.exception.EntityNotFoundException;
import cz.upce.diplomovaprace.manager.SessionManager;
import cz.upce.diplomovaprace.model.ChallengeModel;
import cz.upce.diplomovaprace.model.ChallengeResultModel;
import cz.upce.diplomovaprace.repository.ChallengeRepository;
import cz.upce.diplomovaprace.repository.ChallengeResultRepository;
import cz.upce.diplomovaprace.repository.ChallengeStateRepository;
import cz.upce.diplomovaprace.repository.GameParamRepository;
import cz.upce.diplomovaprace.repository.GameRepository;
import cz.upce.diplomovaprace.repository.RatingRepository;
import cz.upce.diplomovaprace.repository.ResultStateRepository;
import cz.upce.diplomovaprace.repository.UserRepository;
import cz.upce.diplomovaprace.service.ChallengeService;
import io.micrometer.core.lang.NonNull;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private static final String CHALLENGE_DETAIL_DTO_MODEL_KEY = "challengeDetailDto";

    private static final String CHALLENGE_RESULT_VIEW_NAME = "challengeResult";
    private static final String CHALLENGE_DETAIL_VIEW_NAME = "challengeDetail";
    private static final String CHALLENGE_CREATE_VIEW_NAME = "challengeCreate";

    private static final String IS_USER_ALREADY_IN_CHALLENGE_MODEL_KEY = "isUserAlreadyInChallenge";
    private static final String IS_CHALLENGE_FINISHED_MODEL_KEY = "isChallengeFinished";

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

        Iterable<Game> games = gameRepository.findAll();
        model.put(GAMES_MODEL_KEY, games);
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.MAP);

        return new ModelAndView(CHALLENGE_CREATE_VIEW_NAME, model);
    }

    @GetMapping("/detail")
    public ModelAndView challengeDetail(@RequestParam(CHALLENGE_ID_REQUEST_PARAM) int challengeId,
                                        Map<String, Object> model) throws Exception {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(EntityNotFoundException::new);
        List<ChallengeResult> challengeResults = challengeResultRepository.findByChallengeByChallengeId(challenge);
        // Prepare teams
        List<User> users = challengeResults.stream().map(ChallengeResult::getUserByUserId).collect(Collectors.toList());
        Game game = challenge.getGameByGameId();

        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            int userId = user.getId();
            int rating = ratingRepository.findByUserByUserIdAndGameByGameId(user, game).getRating();
            String userName = user.getUserName();
            int numberOfWins = challengeResultRepository.findByUserByUserIdAndResultStateByResultStateId(
                    user, resultStateRepository.findByState(ResultStateConstants.WINNER)).size();
            int numberOfLosses = challengeResultRepository.findByUserByUserIdAndResultStateByResultStateId(
                    user, resultStateRepository.findByState(ResultStateConstants.DEFEATED)).size();
            int numberOfTies = challengeResultRepository.findByUserByUserIdAndResultStateByResultStateId(
                    user, resultStateRepository.findByState(ResultStateConstants.TIE)).size();
            int totalNumberOfGames = numberOfWins + numberOfLosses + numberOfTies;
            ChallengeResult result = challengeResultRepository.findByUserByUserIdAndChallengeByChallengeId(user, challenge);

            UserDto userDto = new UserDto();
            userDto.setId(userId);
            userDto.setRating(rating);
            userDto.setUserName(userName);
            userDto.setNumberOfWins(numberOfWins);
            userDto.setNumberOfLosses(numberOfLosses);
            userDto.setNumberOfTies(numberOfTies);
            userDto.setNumberOfGames(totalNumberOfGames);
            if (!ResultStateConstants.IN_PROGRESS.equals(result.getResultStateByResultStateId().getState())) {
                userDto.setWinningUserScore(result.getScoreWinner());
                userDto.setLossingUserScore(result.getScoreDefeated());
            } else {
                userDto.setChallengeResultState(ResultStateConstants.IN_PROGRESS);
            }
            userDtos.add(userDto);
        }
        // sort by rating
        userDtos.sort(Comparator.comparingInt(UserDto::getRating).reversed());
        // calculate teams
        List<UserDto> firstTeam = new ArrayList<>();
        List<UserDto> secondTeam = new ArrayList<>();
        for (int i = 0; i < userDtos.size(); i++) {
            if (i % 2 == 0) {
                firstTeam.add(userDtos.get(i));
            } else {
                secondTeam.add(userDtos.get(i));
            }
        }
        // set ChallengeDetailDto
        ChallengeDetailDto challengeDetailDto = new ChallengeDetailDto();
        challengeDetailDto.setUserDtos(userDtos);
        challengeDetailDto.setFirstTeam(firstTeam);
        challengeDetailDto.setSecondTeam(secondTeam);
        int maxPlayers = Integer.parseInt(gameParamRepository.findByGameByGameIdAndName(
                game, GameParamConstants.NUMBER_OF_PLAYERS).getValue());
        challengeDetailDto.setMaxPlayers(maxPlayers);

        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.MAP);
        model.put(CHALLENGE_DETAIL_DTO_MODEL_KEY, challengeDetailDto);
        model.put(CHALLENGE_MODEL_KEY, challenge);
        model.put(IS_USER_ALREADY_IN_CHALLENGE_MODEL_KEY, challengeService.isUserAlreadyInChallenge(challengeId));
        model.put(IS_CHALLENGE_FINISHED_MODEL_KEY, challengeService.isChallengeFinished(challenge));

        return new ModelAndView(CHALLENGE_DETAIL_VIEW_NAME, model);
    }


    @GetMapping("/enterResult")
    public ModelAndView challengeEnterResult(@RequestParam(CHALLENGE_ID_REQUEST_PARAM) int challengeId,
                                             @ModelAttribute(CHALLENGE_RESULT_MODEL_ATTRIBUTE) ChallengeResultModel challengeResultModel,
                                             Map<String, Object> model) throws EntityNotFoundException {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(EntityNotFoundException::new);
        model.put(CHALLENGE_MODEL_KEY, challenge);
        return new ModelAndView(CHALLENGE_RESULT_VIEW_NAME, model);
    }

    @PostMapping("/submitResult")
    public ModelAndView challengeSubmitResult(@RequestParam(CHALLENGE_ID_REQUEST_PARAM) int challengeId,
                                              @ModelAttribute(CHALLENGE_RESULT_MODEL_ATTRIBUTE) ChallengeResultModel challengeResultModel,
                                              BindingResult bindingResult, RedirectAttributes redirectAttributes) throws EntityNotFoundException {
        if (bindingResult.hasErrors()) {
            // TODO: Validation
        }

        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(sessionManager.getUserId()).orElseThrow(EntityNotFoundException::new);
        ChallengeResult challengeResult = challengeResultRepository.findByUserByUserIdAndChallengeByChallengeId(user, challenge);

        String resultState = null;
        int winnerScore = challengeResultModel.getScoreTeam1();
        int looserScore = challengeResultModel.getScoreTeam2();
        switch (challengeResultModel.getResultState()) {
            case "WINNER":
                resultState = ResultStateConstants.WINNER;
                winnerScore = challengeResultModel.getScoreTeam1();
                looserScore = challengeResultModel.getScoreTeam2();
                break;
            case "DEFEATED":
                resultState = ResultStateConstants.DEFEATED;
                winnerScore = challengeResultModel.getScoreTeam2();
                looserScore = challengeResultModel.getScoreTeam1();
                break;
            case "TIE":
                resultState = ResultStateConstants.TIE;
                break;
            default:
                break;
        }

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
        }
// ROLE OPERATOR, BUDE PAK ROZHODOVAT SPORNY CHALLENGE POPR. NATO SRAT A POUZE POSILAT A BANOVAT LIDI CO MAJ HODNE REPORTU?
        // bude mit zas link na detajl challenge a tam bude moct zmenit skore lidem, tlacitko vedle pridat do pratel na zadat vysledek a upravit to, to zni fajn

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

        return redirectToChallengeDetail(challengeId, redirectAttributes);
    }

    @GetMapping("/logout")
    public ModelAndView challengeLogout(@RequestParam(CHALLENGE_ID_REQUEST_PARAM) int challengeId,
                                        RedirectAttributes redirectAttributes) throws EntityNotFoundException {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(sessionManager.getUserId()).orElseThrow(EntityNotFoundException::new);

        ChallengeResult challengeResult = challengeResultRepository.findByUserByUserIdAndChallengeByChallengeId(user, challenge);
        challengeResultRepository.delete(challengeResult);

        return redirectToChallengeDetail(challengeId, redirectAttributes);
    }

    private ModelAndView redirectToChallengeDetail(@NonNull int challengeId, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(CHALLENGE_ID_REQUEST_PARAM, challengeId);
        return new ModelAndView("redirect:/challenge/detail");
    }
}
