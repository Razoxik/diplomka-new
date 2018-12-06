package cz.upce.diplomovaprace.service.impl;

import cz.upce.diplomovaprace.constants.GameParamConstants;
import cz.upce.diplomovaprace.entity.Challenge;
import cz.upce.diplomovaprace.entity.ChallengeResult;
import cz.upce.diplomovaprace.entity.Game;
import cz.upce.diplomovaprace.entity.User;
import cz.upce.diplomovaprace.model.MapModel;
import cz.upce.diplomovaprace.repository.ChallengeRepository;
import cz.upce.diplomovaprace.repository.ChallengeResultRepository;
import cz.upce.diplomovaprace.repository.GameParamRepository;
import cz.upce.diplomovaprace.repository.RatingRepository;
import cz.upce.diplomovaprace.service.MapService;
import cz.upce.diplomovaprace.tools.LocalDateTimeJPAConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapServiceImpl implements MapService {

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    GameParamRepository gameParamRepository;

    @Autowired
    ChallengeResultRepository challengeResultRepository;

    @Override
    public List<MapModel> prepareMapModels() {
        List<MapModel> mapModels = new ArrayList<>();

        for (Challenge challenge : challengeRepository.findAll()) {
            List<ChallengeResult> challengeResults = challengeResultRepository.findByChallengeByChallengeId(challenge);
            // players sou uz vsici v tom challengeResults
            // challengeresults sort by created date pro hosta
            User host = challengeResults.get(0).getUserByUserId();
            int rating = 0;
            for (ChallengeResult challengeResult : challengeResults) {
                User user = challengeResult.getUserByUserId();
                rating += ratingRepository.findByUserByUserIdAndGameByGameId(user, challenge.getGameByGameId()).getRating();
            }

            Game game = challenge.getGameByGameId();
            MapModel mapModel = new MapModel();
            mapModel.setChallengeId(challenge.getId());
            // TODO Nezobrazovat již ukončené výzvy (ve stavu finished)
            // TODO kdyz odchazis z vyzvy a je prazdna tak se rovnou smaze at nemusis resit prazdnou vyzvu na nully a dava to i sense
            mapModel.setHostId(host.getId());
            mapModel.setHostName(host.getUserName());
            mapModel.setGameName(game.getName());
            mapModel.setRating(rating);
            LocalDateTimeJPAConverter converter = new LocalDateTimeJPAConverter();
            LocalDateTime startDate = converter.convertToEntityAttribute(challenge.getStart());
            LocalDateTime endDate = converter.convertToEntityAttribute(challenge.getEnd());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM. HH:mm");
            mapModel.setStart(startDate.format(formatter));
            mapModel.setEnd(endDate.format(formatter));
            mapModel.setLatCoords(challenge.getCoordsLat());
            mapModel.setLngCoords(challenge.getCoordsLng());
            int numberOfPlayers = challengeResults.stream().map(ChallengeResult::getUserByUserId).collect(Collectors.toList()).size();
            mapModel.setNumberOfPlayers(numberOfPlayers);
            mapModel.setRating(rating / numberOfPlayers);
            int maxPlayers = Integer.parseInt(gameParamRepository.findByGameByGameIdAndName(
                    game, GameParamConstants.NUMBER_OF_PLAYERS).getValue());
            mapModel.setMaxPlayers(maxPlayers);
            mapModels.add(mapModel);
        }
        return mapModels;
    }
}
