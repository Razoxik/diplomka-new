package com.bartosektom.letsplayfolks.controller;

import com.bartosektom.letsplayfolks.constants.ActiveTabConstants;
import com.bartosektom.letsplayfolks.constants.CommonConstants;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import com.bartosektom.letsplayfolks.exception.GameAlreadyExistException;
import com.bartosektom.letsplayfolks.model.GameModel;
import com.bartosektom.letsplayfolks.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/game")
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
@PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR', 'USER')")
public class GameController {

    private static final String APPROVAL_REQUEST_PARAM = "approval";
    private static final String GAME_ID_REQUEST_PARAM = "gameId";

    private static final String GAME_MODELS_MODEL_KEY = "gameModels";
    private static final String GAME_MODEL_MODEL_KEY = "gameModel";
    private static final String APPROVAL_MODEL_KEY = "approval";

    private static final String GAME_APPROVAL_VIEW_NAME = "game/approval";
    private static final String GAME_CREATE_VIEW_NAME = "game/create";

    @Autowired
    GameService gameService;

    @GetMapping("/approval")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView approval(@RequestParam(value = CommonConstants.SUCCESS_MESSAGE, required = false) String successMessage,
                                 Map<String, Object> model) throws EntityNotFoundException {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.GAME_APPROVAL);
        model.put(GAME_MODELS_MODEL_KEY, gameService.prepareGameModels());
        model.put(CommonConstants.SUCCESS_MESSAGE, successMessage);

        return new ModelAndView(GAME_APPROVAL_VIEW_NAME, model);
    }

    @GetMapping("/create")
    public ModelAndView createGame(@RequestParam(value = CommonConstants.SUCCESS_MESSAGE, required = false) String successMessage,
                                   @RequestParam(value = APPROVAL_REQUEST_PARAM, required = false) boolean approval,
                                   @RequestParam(value = GAME_ID_REQUEST_PARAM, required = false) Integer gameId,
                                   Map<String, Object> model) throws EntityNotFoundException {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.GAME);
        model.put(GAME_MODEL_MODEL_KEY, gameService.prepareGameModel(gameId));
        model.put(CommonConstants.SUCCESS_MESSAGE, successMessage);
        model.put(APPROVAL_MODEL_KEY, approval);

        return new ModelAndView(GAME_CREATE_VIEW_NAME, model);
    }

    @PostMapping("/create")
    public ModelAndView createGameSubmit(@ModelAttribute(GAME_MODEL_MODEL_KEY) GameModel gameModel,
                                         Map<String, Object> model) throws EntityNotFoundException {
        try {
            gameService.createGame(gameModel);
            model.put(CommonConstants.SUCCESS_MESSAGE, "info.message.createGame.send");
        } catch (GameAlreadyExistException e) {
            model.put(CommonConstants.ERROR_MESSAGE, "info.message.createGame.already.exists");
        }
        return new ModelAndView(GAME_CREATE_VIEW_NAME, model);
    }

    @PostMapping("/approval")
    public ModelAndView createGameApproval(@ModelAttribute(GAME_MODEL_MODEL_KEY) GameModel gameModel,
                                           RedirectAttributes redirectAttributes) throws EntityNotFoundException {
        gameService.approveGame(gameModel);

        redirectAttributes.addAttribute(CommonConstants.SUCCESS_MESSAGE, "info.message.createGame.approved");
        return new ModelAndView("redirect:/game/approval");
    }

    @PostMapping("/decline")
    public ModelAndView createGameDecline(@ModelAttribute(GAME_MODEL_MODEL_KEY) GameModel gameModel,
                                          RedirectAttributes redirectAttributes) throws EntityNotFoundException {
        gameService.declineGame(gameModel);
        redirectAttributes.addAttribute(CommonConstants.SUCCESS_MESSAGE, "info.message.createGame.declined");
        return new ModelAndView("redirect:/game/approval");
    }
}
