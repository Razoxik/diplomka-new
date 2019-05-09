package com.bartosektom.letsplayfolks.controller;

import com.bartosektom.letsplayfolks.constants.ActiveTabConstants;
import com.bartosektom.letsplayfolks.constants.CommonConstants;
import com.bartosektom.letsplayfolks.constants.GameConstants;
import com.bartosektom.letsplayfolks.entity.Challenge;
import com.bartosektom.letsplayfolks.entity.Game;
import com.bartosektom.letsplayfolks.entity.User;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import com.bartosektom.letsplayfolks.exception.UnexpectedChallengeException;
import com.bartosektom.letsplayfolks.manager.SessionManager;
import com.bartosektom.letsplayfolks.model.ChallengeDetailModel;
import com.bartosektom.letsplayfolks.model.ChallengeModel;
import com.bartosektom.letsplayfolks.model.ChallengeResultModel;
import com.bartosektom.letsplayfolks.repository.ChallengeRepository;
import com.bartosektom.letsplayfolks.repository.GameRepository;
import com.bartosektom.letsplayfolks.repository.UserRepository;
import com.bartosektom.letsplayfolks.service.ChallengeService;
import com.bartosektom.letsplayfolks.validator.ChallengeResultValidator;
import com.bartosektom.letsplayfolks.validator.ChallengeValidator;
import io.micrometer.core.lang.NonNull;
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

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/challenge")
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
@PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR', 'USER')")
public class ChallengeController {

    private static final String CHALLENGE_USER_ID_REQUEST_PARAM = "challengeUserId";
    private static final String CHALLENGE_ID_REQUEST_PARAM = "challengeId";
    private static final String LAT_COORDS_REQUEST_PARAM = "latCoords";
    private static final String LNG_COORDS_REQUEST_PARAM = "lngCoords";

    private static final String CHALLENGE_RESULT_MODEL_ATTRIBUTE = "challengeResultModel";
    private static final String CHALLENGE_MODEL_ATTRIBUTE = "challengeModel";

    private static final String QUESTIONABLE_CHALLENGES_MODEL_KEY = "questionableChallenges";
    private static final String CHALLENGE_DETAIL_MODEL_KEY = "challengeDetailModel";
    private static final String CHALLENGE_USER_ID_MODEL_KEY = "challengeUserId";
    private static final String CHALLENGE_MODEL_KEY = "challenge";
    private static final String IS_USER_ALREADY_IN_CHALLENGE_MODEL_KEY = "isUserAlreadyInChallenge";
    private static final String IS_CHALLENGE_FINISHED_MODEL_KEY = "isChallengeFinished";
    private static final String IS_CHALLENGE_FULL_MODEL_KEY = "isChallengeFull";
    private static final String CAN_USER_ENTER_RESULT_MODEL_KEY = "canUserEnterResult";
    private static final String GAMES_MODEL_KEY = "games";

    private static final String CHALLENGE_QUESTIONABLE_LIST_VIEW_NAME = "challenge/questionable/list";
    private static final String CHALLENGE_RESULT_VIEW_NAME = "challenge/result";
    private static final String CHALLENGE_DETAIL_VIEW_NAME = "challenge/detail";
    private static final String CHALLENGE_CREATE_VIEW_NAME = "challenge/create";

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private ChallengeResultValidator challengeResultValidator;

    @Autowired
    private ChallengeValidator challengeValidator;

    @InitBinder("challengeResultModel")
    protected void initChallengeResultModelBinder(WebDataBinder binder) {
        binder.addValidators(challengeResultValidator);
    }

    @InitBinder("challengeModel")
    protected void initChallengeModelBinder(WebDataBinder binder) {
        binder.addValidators(challengeValidator);
    }

    @GetMapping("/create")
    public ModelAndView challengeCreate(@RequestParam(value = CommonConstants.ERROR_MESSAGE, required = false) String errorMessage,
                                        @RequestParam(LAT_COORDS_REQUEST_PARAM) String latCoords,
                                        @RequestParam(LNG_COORDS_REQUEST_PARAM) String lngCoords,
                                        @ModelAttribute(CHALLENGE_MODEL_ATTRIBUTE) ChallengeModel challengeModel,
                                        Map<String, Object> model) {
        challengeModel.setLatCoords(latCoords);
        challengeModel.setLngCoords(lngCoords);
        List<Game> games = gameRepository.findByApproved(GameConstants.GAME_APPROVED);

        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.MAP);
        model.put(GAMES_MODEL_KEY, games);
        model.put(CommonConstants.ERROR_MESSAGE, errorMessage);

        return new ModelAndView(CHALLENGE_CREATE_VIEW_NAME, model);
    }

    @GetMapping("/detail")
    public ModelAndView challengeDetail(@RequestParam(value = CommonConstants.SUCCESS_MESSAGE, required = false) String successMessage,
                                        @RequestParam(CHALLENGE_ID_REQUEST_PARAM) int challengeId,
                                        Map<String, Object> model) throws EntityNotFoundException {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(EntityNotFoundException::new);
        ChallengeDetailModel challengeDetailModel = challengeService.prepareChallengeDetailDto(challenge);
        boolean isUserAlreadyInChallenge = challengeService.isUserAlreadyInChallenge(challenge);
        boolean isChallengeFinished = challengeService.isChallengeFinished(challenge);
        boolean canUserEnterResult = challengeService.canUserEnterResult(challenge);
        boolean isChallengeFull = challengeService.isChallengeFull(challenge);

        model.put(CHALLENGE_DETAIL_MODEL_KEY, challengeDetailModel);
        model.put(CHALLENGE_MODEL_KEY, challenge);
        model.put(CAN_USER_ENTER_RESULT_MODEL_KEY, canUserEnterResult);
        model.put(IS_USER_ALREADY_IN_CHALLENGE_MODEL_KEY, isUserAlreadyInChallenge);
        model.put(IS_CHALLENGE_FINISHED_MODEL_KEY, isChallengeFinished);
        model.put(IS_CHALLENGE_FULL_MODEL_KEY, isChallengeFull);
        model.put(CommonConstants.SUCCESS_MESSAGE, successMessage);

        return new ModelAndView(CHALLENGE_DETAIL_VIEW_NAME, model);
    }


    @GetMapping("/enterResult")
    public ModelAndView challengeEnterResult(@RequestParam(value = CHALLENGE_USER_ID_REQUEST_PARAM, required = false) Integer challengeUserId,
                                             @RequestParam(value = CommonConstants.INFO_MESSAGE, required = false) String infoMessage,
                                             @RequestParam(CHALLENGE_ID_REQUEST_PARAM) int challengeId,
                                             @ModelAttribute(CHALLENGE_RESULT_MODEL_ATTRIBUTE) ChallengeResultModel challengeResultModel,
                                             Map<String, Object> model) throws EntityNotFoundException {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(EntityNotFoundException::new);

        model.put(CHALLENGE_MODEL_KEY, challenge);
        model.put(CHALLENGE_USER_ID_MODEL_KEY, challengeUserId);
        model.put(CommonConstants.INFO_MESSAGE, infoMessage);

        return new ModelAndView(CHALLENGE_RESULT_VIEW_NAME, model);
    }

    @PostMapping("/submitResult")
    public ModelAndView challengeSubmitResult(@RequestParam(CHALLENGE_ID_REQUEST_PARAM) int challengeId,
                                              @ModelAttribute(CHALLENGE_RESULT_MODEL_ATTRIBUTE) @Validated ChallengeResultModel challengeResultModel,
                                              BindingResult bindingResult, RedirectAttributes redirectAttributes) throws EntityNotFoundException, UnexpectedChallengeException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addAttribute(CommonConstants.INFO_MESSAGE, bindingResult.getGlobalErrors().get(0).getDefaultMessage());
            redirectAttributes.addAttribute(CHALLENGE_USER_ID_REQUEST_PARAM, challengeResultModel.getChallengeUserId());
            redirectAttributes.addAttribute(CHALLENGE_ID_REQUEST_PARAM, challengeId);
            return new ModelAndView("redirect:/challenge/enterResult");
        }
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(EntityNotFoundException::new);
        int userId = challengeResultModel.getChallengeUserId() == null ? sessionManager.getUserId() : challengeResultModel.getChallengeUserId();
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        challengeService.finishChallenge(user, challenge, challengeResultModel);

        return redirectToChallengeDetail(challengeId, redirectAttributes);
    }

    @PostMapping("/create")
    public ModelAndView challengeCreate(@ModelAttribute(CHALLENGE_MODEL_ATTRIBUTE) @Validated ChallengeModel challengeModel,
                                        BindingResult bindingResult, RedirectAttributes redirectAttributes) throws EntityNotFoundException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addAttribute(CommonConstants.ERROR_MESSAGE, bindingResult.getGlobalErrors().get(0).getDefaultMessage());
            redirectAttributes.addAttribute(LAT_COORDS_REQUEST_PARAM, challengeModel.getLatCoords());
            redirectAttributes.addAttribute(LNG_COORDS_REQUEST_PARAM, challengeModel.getLngCoords());
            return new ModelAndView("redirect:/challenge/create");
        }
        challengeService.createChallenge(challengeModel);

        return new ModelAndView("redirect:/map");
    }

    @GetMapping("/join")
    public ModelAndView challengeJoin(@RequestParam(CHALLENGE_ID_REQUEST_PARAM) int challengeId,
                                      RedirectAttributes redirectAttributes) throws EntityNotFoundException {
        challengeService.joinChallenge(challengeId);

        return redirectToChallengeDetail(challengeId, redirectAttributes);
    }

    @GetMapping("/logout")
    public ModelAndView challengeLogout(@RequestParam(CHALLENGE_ID_REQUEST_PARAM) int challengeId,
                                        RedirectAttributes redirectAttributes) throws EntityNotFoundException {
        if (!challengeService.leaveChallenge(challengeId)) {
            return new ModelAndView("redirect:/map");
        }
        return redirectToChallengeDetail(challengeId, redirectAttributes);
    }

    @GetMapping("/questionable/list")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR')")
    public ModelAndView challengeQuestionableList(Map<String, Object> model) throws UnexpectedChallengeException {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.QUESTIONABLE_CHALLENGES);
        model.put(QUESTIONABLE_CHALLENGES_MODEL_KEY, challengeService.prepareQuestionableChallengeModels());

        return new ModelAndView(CHALLENGE_QUESTIONABLE_LIST_VIEW_NAME, model);
    }

    private ModelAndView redirectToChallengeDetail(@NonNull int challengeId, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute(CHALLENGE_ID_REQUEST_PARAM, challengeId);
        return new ModelAndView("redirect:/challenge/detail");
    }
}
