package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.dto.ChallengeDto;
import cz.upce.diplomovaprace.entity.Challenge;
import cz.upce.diplomovaprace.entity.ChallengeResult;
import cz.upce.diplomovaprace.entity.Game;
import cz.upce.diplomovaprace.entity.User;
import cz.upce.diplomovaprace.enums.ActiveTabConstants;
import cz.upce.diplomovaprace.enums.GameParamConstants;
import cz.upce.diplomovaprace.repository.ChallengeRepository;
import cz.upce.diplomovaprace.repository.ChallengeResultRepository;
import cz.upce.diplomovaprace.repository.GameParamRepository;
import cz.upce.diplomovaprace.repository.RatingRepository;
import cz.upce.diplomovaprace.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
public class MapController {

    private static final String CHALLENGE_DTOS_MODEL_KEY = "challengeDtos";

    private static final String MAP_VIEW_NAME = "map";

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    GameParamRepository gameParamRepository;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    ChallengeResultRepository challengeResultRepository;

    @GetMapping("/map")
    public ModelAndView renderMap(Map<String, Object> model) {

        List<ChallengeDto> challengeDtos = new ArrayList<>();

        for (Challenge challenge : challengeRepository.findAll()) {
            ChallengeDto challengeDto = new ChallengeDto();
            Game game = challenge.getGameByGameId(); // PREMENOVAT CHALLENGERESULT NA CHALLENGE DETAIL!!!
            List<ChallengeResult> challengeResults = challengeResultRepository.findByChallengeByChallengeId(challenge);
            // players sou uz vsici v tom challengeResults
            User host = challengeResults.isEmpty() ? null : challengeResults.get(0).getUserByUserId();
            int rating = 0;
            for (ChallengeResult challengeResult : challengeResults) {
                User user = challengeResult.getUserByUserId();
                rating += ratingRepository.findByUserByUserIdAndGameByGameId(user, challenge.getGameByGameId()).getRating();
            }
            challengeDto.setGame(game);
            challengeDto.setHost(host);
            challengeDto.setRating(rating / challengeResults.size());
            // date
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("H:m d.M.yyyy");
            challengeDto.setStart(simpleDateFormat.format(challenge.getStart()));
            challengeDto.setEnd(simpleDateFormat.format(challenge.getEnd()));
            //https://stackoverflow.com/questions/7309259/get-list-of-attributes-of-an-object-in-an-list
            List<User> users = challengeResults.stream().map(ChallengeResult::getUserByUserId).collect(Collectors.toList());
            challengeDto.setListOfPlayers(users);
            challengeDto.setChallenge(challenge);
            int maxPlayers = Integer.parseInt(gameParamRepository.findByGameByGameIdAndName(
                    game, GameParamConstants.NUMBER_OF_PLAYERS).getValue());
            challengeDto.setMaxPlayers(maxPlayers);
            challengeDtos.add(challengeDto);
        }

        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.MAP);
        model.put(CHALLENGE_DTOS_MODEL_KEY, challengeDtos);

        return new ModelAndView(MAP_VIEW_NAME, model);
    }
}
