package com.bartosektom.letsplayfolks.controller;

import com.bartosektom.letsplayfolks.constants.ActiveTabConstants;
import com.bartosektom.letsplayfolks.constants.CommonConstants;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import com.bartosektom.letsplayfolks.exception.UserAlreadyExistException;
import com.bartosektom.letsplayfolks.model.UserModel;
import com.bartosektom.letsplayfolks.service.FriendService;
import com.bartosektom.letsplayfolks.service.UserService;
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
public class UserController {

    private static final String CAN_BE_ADDED_TO_FRIENDS_MODEL_KEY = "canBeAddedToFriends";
    private static final String IS_OWNER_OF_PROFILE_MODEL_KEY = "isOwnerOfProfile";
    private static final String USER_RATINGS_MODEL_KEY = "userRatingModels";
    private static final String USER_MODEL_KEY = "userModel";

    private static final String REGISTRATION_VIEW_NAME = "/user/registration";
    private static final String DETAIL_VIEW_NAME = "/user/detail";

    @Autowired
    FriendService friendService;

    @Autowired
    UserService userService;

    @GetMapping("/detail")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR', 'USER')")
    public ModelAndView userDetail(@RequestParam(value = CommonConstants.SUCCESS_MESSAGE, required = false) String successMessage,
                                   @RequestParam(CommonConstants.USER_ID) Integer userId,
                                   Map<String, Object> model) throws EntityNotFoundException {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.USER_PROFILE);
        model.put(USER_MODEL_KEY, userService.prepareUserModel(userId));
        model.put(USER_RATINGS_MODEL_KEY, userService.prepareUserRatingModels(userId));
        model.put(IS_OWNER_OF_PROFILE_MODEL_KEY, userService.isOwnerOfProfile(userId));
        model.put(CAN_BE_ADDED_TO_FRIENDS_MODEL_KEY, friendService.canBeAddedToFriends(userId));
        model.put(CommonConstants.SUCCESS_MESSAGE, successMessage);

        return new ModelAndView(DETAIL_VIEW_NAME, model);
    }

    @PostMapping("/updateProfile")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR', 'USER')")
    public ModelAndView updateProfile(@RequestParam(CommonConstants.USER_ID) Integer userId,
                                      @ModelAttribute(USER_MODEL_KEY) UserModel userModel,
                                      RedirectAttributes redirectAttributes) throws EntityNotFoundException {
        userService.saveUserModel(userId, userModel);

        redirectAttributes.addAttribute(CommonConstants.SUCCESS_MESSAGE, "user.profile.updated");
        redirectAttributes.addAttribute(CommonConstants.USER_ID, userId);
        return new ModelAndView("redirect:/user/detail");
    }

    @GetMapping("/registration")
    public ModelAndView registration(@RequestParam(value = CommonConstants.ERROR_MESSAGE, required = false) String errorMessage,
                                     Map<String, Object> model) {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.REGISTRATION);
        model.put(CommonConstants.ERROR_MESSAGE, errorMessage);
        model.put(USER_MODEL_KEY, new UserModel());

        return new ModelAndView(REGISTRATION_VIEW_NAME, model);
    }

    @PostMapping("/registration")
    public ModelAndView submitRegistration(@ModelAttribute(USER_MODEL_KEY) UserModel userModel,
                                           RedirectAttributes redirectAttributes) throws EntityNotFoundException {
        try {
            userService.createUserFromModel(userModel);
        } catch (UserAlreadyExistException e) {
            redirectAttributes.addAttribute(CommonConstants.ERROR_MESSAGE, "info.message.register.userAlreadyExists");
            return new ModelAndView("redirect:/user/registration");
        }
        redirectAttributes.addAttribute(CommonConstants.SUCCESS_MESSAGE, "info.message.register.userRegistered");
        return new ModelAndView("redirect:/login");
    }
}
