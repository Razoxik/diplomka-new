package com.bartosektom.letsplayfolks.controller;

import com.bartosektom.letsplayfolks.model.GameModel;
import com.bartosektom.letsplayfolks.service.GameService;
import com.bartosektom.letsplayfolks.constants.ActiveTabConstants;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Controller
@RequestMapping("/game")
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
@PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR', 'USER')")
public class GameController {

    //Save the uploaded file to this folder
    private static final String UPLOADED_FOLDER = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\";
    //System.getProperty("user.dir")+"src\\main\\resources\\static\\img"

    private static final String GAME_CREATE_VIEW_NAME = "game/create";
    private static final String GAME_APPROVAL_VIEW_NAME = "game/approval";

    private static final String GAME_MODEL_MODEL_KEY = "gameModel";
    private static final String GAME_MODELS_MODEL_KEY = "gameModels";
    @Autowired
    GameService gameService;

    @GetMapping("/approval")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView approval(Map<String, Object> model) throws EntityNotFoundException {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.GAME_APPROVAL);
        model.put(GAME_MODELS_MODEL_KEY, gameService.prepareGameModels());

        return new ModelAndView(GAME_APPROVAL_VIEW_NAME, model);
    }

    @GetMapping("/create")
    public ModelAndView createGame(@RequestParam(value = "gameId", required = false) Integer gameId,
                                   Map<String, Object> model) throws EntityNotFoundException {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.GAME);
        model.put(GAME_MODEL_MODEL_KEY, gameService.prepareGameModel(gameId));

        return new ModelAndView(GAME_CREATE_VIEW_NAME, model);
    }

    @PostMapping("/create")
    public ModelAndView createGameSubmit(@ModelAttribute(GAME_MODEL_MODEL_KEY) GameModel gameModel,
                                         Map<String, Object> model) throws EntityNotFoundException {
        gameService.createGame(gameModel);
        return new ModelAndView(GAME_CREATE_VIEW_NAME, model);
    }

    @PostMapping("/approval")
    public ModelAndView createGameApproval(@ModelAttribute(GAME_MODEL_MODEL_KEY) GameModel gameModel,
                                         Map<String, Object> model) throws EntityNotFoundException {
        gameService.approveGame(gameModel);
        return new ModelAndView(GAME_CREATE_VIEW_NAME, model);
    }
    // TODO: odstranit ty hry z data.sql, protoze nemaj ranking pro hrace, nechat tam jen ejdnu testovaci
    // TODO: Defaultni obrazek?
    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/news";
    }
}
