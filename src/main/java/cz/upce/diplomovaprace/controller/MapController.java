package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.dto.ChallengeDto;
import cz.upce.diplomovaprace.entity.Challenge;
import cz.upce.diplomovaprace.entity.ChallengeResult;
import cz.upce.diplomovaprace.entity.Game;
import cz.upce.diplomovaprace.entity.GameParam;
import cz.upce.diplomovaprace.entity.User;
import cz.upce.diplomovaprace.enums.ActiveTabConstants;
import cz.upce.diplomovaprace.repository.ChallengeRepository;
import cz.upce.diplomovaprace.repository.ChallengeResultRepository;
import cz.upce.diplomovaprace.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
public class MapController {

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    ChallengeResultRepository challengeResultRepository;

    @GetMapping("/map")
    public ModelAndView renderMap(Map<String, Object> model) throws Exception {
        model.put("challenges", challengeRepository.findAll());
        model.put("activeTab", ActiveTabConstants.MAP);
        Challenge c = challengeRepository.findById(1).get();

        List<ChallengeDto> challengeDtoList = new ArrayList<>();
        for (Challenge challenge : challengeRepository.findAll()) {
            ChallengeDto challengeDto = new ChallengeDto();
            Game game = challenge.getGameByGameId(); // PREMENOVAT CHALLENGERESULT NA CHALLENGE DETAIL!!!
            List<ChallengeResult> challengeResults = challengeResultRepository.findChallengeResultsByChallengeByChallengeId(challenge);
            // players sou uz vsici v tom challengeResults
            User host = challengeResults.get(0).getUserByUserId(); // order by created abych mel hosta
            int rating = 0;
            for (ChallengeResult challengeResult : challengeResults) {
                rating += challengeResult.getUserByUserId().getRatingsById().stream().filter(rating1 -> rating1.getGameByGameId().equals(challenge.getGameByGameId())).findFirst().orElseThrow(Exception::new).getRating();
            }
            challengeDto.setGame(game);
            challengeDto.setHost(host);
            challengeDto.setRating(rating / challengeResults.size());
            // date
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("H:m d.M.yyyy");
            challengeDto.setStart(simpleDateFormat.format(challenge.getStart()));
            challengeDto.setEnd(simpleDateFormat.format(challenge.getEnd()));
            //https://stackoverflow.com/questions/7309259/get-list-of-attributes-of-an-object-in-an-list
            List<User> players = challengeResults.stream().map(ChallengeResult::getUserByUserId).collect(Collectors.toList());
            challengeDto.setListOfPlayers(players);
            challengeDto.setChallenge(challenge);
            // list game Paramu tej hry tej challene
            Collection<GameParam> gameParams = game.getGameParamsById();
            challengeDto.setMaxPlayers(Integer.parseInt(gameParams.stream().filter(gameParam -> gameParam.getName().equals("number_of_players")).findFirst().orElseThrow(Exception::new).getValue()));
            challengeDtoList.add(challengeDto);
        }
        model.put("challengesDto", challengeDtoList);

        return new ModelAndView("map", model);
        //${challenge.userByChallengerUserId.ratingsByUserId.stream().filter(r -> r.gameByGameGameId.gameId==challenge.gameByGameGameId.gameId).findFirst().orElse(null).rating}
        //  c.getChallengeResultsById().stream().filter(challengeResult -> challengeResult.getChallengeByChallengeId().getId() == c.getId()).findFirst().orElse(null).getUserByUserId().getRatingsById().stream().filter(rating -> rating.getGameByGameId().getId() == c.getGameByGameId().getId()).findFirst().orElse(null).getRating()
    }
}
