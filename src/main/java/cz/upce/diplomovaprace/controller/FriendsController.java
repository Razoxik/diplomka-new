package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.constants.ActiveTabConstants;
import cz.upce.diplomovaprace.exception.EntityNotFoundException;
import cz.upce.diplomovaprace.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/friend")
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
@PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR', 'USER')")
public class FriendsController {

    private static final String USER_ID_REQUEST_PARAM = "userId";

    private static final String FRIEND_LIST_VIEW_NAME = "friend/list";

    private static final String FRIENDS_MODEL_MODEL_KEY = "friendModels";

    @Autowired
    FriendService friendService;

    @GetMapping("/list")
    public ModelAndView friendList(Map<String, Object> model) throws EntityNotFoundException {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.FRIENDS);
        model.put(FRIENDS_MODEL_MODEL_KEY, friendService.prepareFriendModels());

        return new ModelAndView(FRIEND_LIST_VIEW_NAME, model);
    }

    @GetMapping("/addToFriends")
    public ModelAndView addToFriend(@RequestParam(value = USER_ID_REQUEST_PARAM) Integer userId,
                                    RedirectAttributes redirectAttributes) throws EntityNotFoundException {
        friendService.addToFriend(userId);

        redirectAttributes.addAttribute(USER_ID_REQUEST_PARAM, userId);
        return new ModelAndView("redirect:/user/detail");
    }
}
