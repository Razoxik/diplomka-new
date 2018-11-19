package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.entity.Game;
import cz.upce.diplomovaprace.constants.ActiveTabConstants;
import cz.upce.diplomovaprace.constants.GameParamConstants;
import cz.upce.diplomovaprace.exception.EntityNotFoundException;
import cz.upce.diplomovaprace.model.GameModel;
import cz.upce.diplomovaprace.repository.GameParamRepository;
import cz.upce.diplomovaprace.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
public class GameController {

    //Save the uploaded file to this folder
    private static final String UPLOADED_FOLDER = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\";
    //System.getProperty("user.dir")+"src\\main\\resources\\static\\img"
    @Autowired
    GameRepository gameRepository;
    @Autowired
    GameParamRepository gameParamRepository;

    @GetMapping("/createGame")
    public ModelAndView renderOperatorChallenges(@RequestParam(value = "gameId", required = false) Integer gameId,
                                                 Map<String, Object> model) throws EntityNotFoundException {

        GameModel gameModel = new GameModel();
        if (gameId != null) {
            Game game = gameRepository.findById(gameId).orElseThrow(EntityNotFoundException::new);
            gameModel.setName(game.getName());
            int maxPlayers = Integer.parseInt(gameParamRepository.findByGameByGameIdAndName(
                    game, GameParamConstants.NUMBER_OF_PLAYERS).getValue());
            gameModel.setNumberOfPlayers(maxPlayers);
        }
        model.put("gameModel", gameModel);
        return new ModelAndView("createGame", model);
    }

    @PostMapping("/createGame")
    public ModelAndView renderOperatorChallenges2(@ModelAttribute("gameModel") GameModel gameModel,
                                                  Map<String, Object> model) {

        return new ModelAndView("createGame", model);
    }

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
