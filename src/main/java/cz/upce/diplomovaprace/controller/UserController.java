package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.constants.ActiveTabConstants;
import cz.upce.diplomovaprace.exception.EntityNotFoundException;
import cz.upce.diplomovaprace.model.UserModel;
import cz.upce.diplomovaprace.service.FriendService;
import cz.upce.diplomovaprace.service.UserService;
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
@RequestMapping("/user")
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
@PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR', 'USER')")
public class UserController {

    private static final String DETAIL_VIEW_NAME = "/user/detail";

    private static final String USER_ID_REQUEST_PARAM = "userId";

    private static final String USER_MODEL_KEY = "userModel";
    private static final String USER_RATINGS_MODEL_KEY = "userRatingModels";
    private static final String IS_OWNER_OF_PROFILE_MODEL_KEY = "isOwnerOfProfile";
    private static final String CAN_BE_ADDED_TO_FRIENDS_MODEL_KEY = "canBeAddedToFriends";

    @Autowired
    UserService userService;

    @Autowired
    FriendService friendService;

    // posilat to userId rovnou z JSPcek a nedavat to jako required = false a pak si to tahat ze sessiony muzes to tahat
    // ze sessiony uz v JSP a posilat to jako param rovnou tam zejo
    @GetMapping("/detail")
    public ModelAndView userDetail(@RequestParam(value = USER_ID_REQUEST_PARAM) Integer userId,
                                   Map<String, Object> model) throws EntityNotFoundException {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.USER_PROFILE);
        model.put(USER_MODEL_KEY, userService.prepareUserModel(userId));
        model.put(USER_RATINGS_MODEL_KEY, userService.prepareUserRatingModels(userId));
        model.put(IS_OWNER_OF_PROFILE_MODEL_KEY, userService.isOwnerOfProfile(userId));
        model.put(CAN_BE_ADDED_TO_FRIENDS_MODEL_KEY, friendService.canBeAddedToFriends(userId));

        return new ModelAndView(DETAIL_VIEW_NAME, model);
    }

    //TADYTO TED - UDELAT FORM V USER DETAIL NA UPRAVU DAT PRO UZIVATELE KTERYMU NALEZI, ZBYTEK BUTTONU A PREKLADY
    @PostMapping("/updateProfile")
    public ModelAndView updateProfile(@RequestParam(value = USER_ID_REQUEST_PARAM) Integer userId,
                                      @ModelAttribute(USER_MODEL_KEY) UserModel userModel,
                                      RedirectAttributes redirectAttributes) throws EntityNotFoundException {
        userService.saveUserModel(userId, userModel);

        redirectAttributes.addAttribute(USER_ID_REQUEST_PARAM, userId);
        return new ModelAndView("redirect:/user/detail");
    }
}
