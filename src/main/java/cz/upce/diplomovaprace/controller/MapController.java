package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.constants.ActiveTabConstants;
import cz.upce.diplomovaprace.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
@PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR', 'USER')")
public class MapController {

    private static final String MAP_MODELS_MODEL_KEY = "mapModels";

    private static final String MAP_VIEW_NAME = "map/show";

    @Autowired
    MapService mapService;

    @GetMapping("/map")
    public ModelAndView renderMap(Map<String, Object> model) {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.MAP);
        model.put(MAP_MODELS_MODEL_KEY, mapService.prepareMapModels());

        return new ModelAndView(MAP_VIEW_NAME, model);
    }
}
