package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.enums.ActiveTab;
import cz.upce.diplomovaprace.repository.ActivityDao;
import cz.upce.diplomovaprace.repository.TeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class MapController {

    @Autowired
    ActivityDao activityDao;

    @Autowired
    TeamDao teamDao;

    @GetMapping("/map")
    public ModelAndView renderMap(Map<String, Object> model) {
        model.put("activities", activityDao.findAll());
        model.put("activeTab", ActiveTab.MAP.toString());
        return new ModelAndView("map", model);
    }
}
