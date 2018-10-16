package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.enums.ActiveTabConstants;
import cz.upce.diplomovaprace.repository.ActivityDao;
import cz.upce.diplomovaprace.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
public class MapController {

    @Autowired
    ActivityDao activityDao;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @GetMapping("/map")
    public ModelAndView renderMap(Map<String, Object> model) {
        model.put("activities", activityDao.findAll());
        model.put("activeTab", ActiveTabConstants.MAP);
        return new ModelAndView("map", model);
    }
}
