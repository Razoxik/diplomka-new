package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.constants.ActiveTabConstants;
import cz.upce.diplomovaprace.exception.EntityNotFoundException;
import cz.upce.diplomovaprace.model.UserModel;
import cz.upce.diplomovaprace.repository.RatingRepository;
import cz.upce.diplomovaprace.repository.UserRepository;
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


    @Autowired
    UserRepository userRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    UserService userService;

    @GetMapping("/detail")
    public ModelAndView userDetail(@RequestParam(value = "userId") Integer userId, // posilat to userId rovnou z JSPcek a nedavat to jako required = false a pak si to tahat ze sessiony muzes to tahat ze sessiony uz v JSP a posilat to jako param rovnou tam zejo
                                   Map<String, Object> model) throws EntityNotFoundException {


        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.USER_PROFILE);
        model.put("userModel", userService.prepareUserModel(userId));
        model.put("userRatingModels", userService.prepareUserRatingModels(userId));

        return new ModelAndView("user/detail", model);
    }

    @PostMapping("/updateProfile")
    public ModelAndView updateProfile(@ModelAttribute("userModel") UserModel userModel,
                                      RedirectAttributes redirectAttributes) {
//TADYTO TED - UDELAT FORM V USER DETAIL NA UPRAVU DAT PRO UZIVATELE KTERYMU NALEZI, ZBYTEK BUTTONU A PREKLADY
        redirectAttributes.addAttribute("userId", userModel.getUserId());

        return new ModelAndView("redirect:/user/detail");
    }

}
