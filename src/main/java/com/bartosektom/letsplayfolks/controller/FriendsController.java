package com.bartosektom.letsplayfolks.controller;

import com.bartosektom.letsplayfolks.constants.ActiveTabConstants;
import com.bartosektom.letsplayfolks.constants.CommonConstants;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import com.bartosektom.letsplayfolks.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/friend")
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
@PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR', 'USER')")
public class FriendsController {

    private static final String FRIENDS_MODEL_MODEL_KEY = "friendModels";

    private static final String FRIEND_LIST_VIEW_NAME = "friend/list";

    @Autowired
    FriendService friendService;

    @GetMapping("/list")
    public ModelAndView friendList(Map<String, Object> model) throws EntityNotFoundException {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.FRIENDS);
        model.put(FRIENDS_MODEL_MODEL_KEY, friendService.prepareFriendModels());

        return new ModelAndView(FRIEND_LIST_VIEW_NAME, model);
    }

    @GetMapping("/addToFriends")
    public ModelAndView addToFriend(@RequestParam(CommonConstants.USER_ID) Integer userId, HttpServletRequest request,
                                    RedirectAttributes redirectAttributes) throws EntityNotFoundException {
        friendService.addToFriend(userId);

        redirectAttributes.addAttribute(CommonConstants.SUCCESS_MESSAGE, "friendAdded");
        return new ModelAndView("redirect:" + request.getHeader("Referer"));
    }
}
