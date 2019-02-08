package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.constants.ActiveTabConstants;
import cz.upce.diplomovaprace.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private static final String GAME_APPROVAL_VIEW_NAME = "admin/gameApproval";
    private static final String ADMIN_CONSOLE_REDIRECT = "redirect:/h2-console";

    private static final String GAME_MODELS_MODEL_KEY = "gameModels";

    @Autowired
    GameService gameService;
//
//  @GetMapping("/gameApproval")
    //  public ModelAndView renderOperatorChallenges(Map<String, Object> model) throws EntityNotFoundException {
    //      model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.GAME_APPROVAL);
    //    model.put(GAME_MODELS_MODEL_KEY, gameService.prepareGameModels());
//
    //     return new ModelAndView(GAME_APPROVAL_VIEW_NAME, model);
    //   }

    @GetMapping("/console")
    public ModelAndView redirectToAdminConsole() {
        return new ModelAndView(ADMIN_CONSOLE_REDIRECT);
    }
}
