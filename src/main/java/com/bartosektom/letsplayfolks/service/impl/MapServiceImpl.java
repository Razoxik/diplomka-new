package com.bartosektom.letsplayfolks.service.impl;

import com.bartosektom.letsplayfolks.constants.ChallengeStateConstants;
import com.bartosektom.letsplayfolks.constants.GameParamConstants;
import com.bartosektom.letsplayfolks.repository.ChallengeRepository;
import com.bartosektom.letsplayfolks.repository.ChallengeResultRepository;
import com.bartosektom.letsplayfolks.repository.GameParamRepository;
import com.bartosektom.letsplayfolks.repository.RatingRepository;
import com.bartosektom.letsplayfolks.service.MapService;
import com.bartosektom.letsplayfolks.tools.LocalDateTimeJPAConverter;
import com.bartosektom.letsplayfolks.entity.Challenge;
import com.bartosektom.letsplayfolks.entity.ChallengeResult;
import com.bartosektom.letsplayfolks.entity.Game;
import com.bartosektom.letsplayfolks.entity.User;
import com.bartosektom.letsplayfolks.model.MapModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
            // We do not want challenges with end date later than now or has state finished
            if (challenge.getEnd().before(new Date()) || challenge.getChallengeStateByChallengeStateId().getState().equals(ChallengeStateConstants.FINISHED)){
                continue;
            }
            List<ChallengeResult> challengeResults = challengeResultRepository.findByChallengeByChallengeId(challenge);
            // Sort challenge result for host.
            User host = challengeResults.get(0).getUserByUserId();
            int rating = 0;
            for (ChallengeResult challengeResult : challengeResults) {
                User user = challengeResult.getUserByUserId();
                rating += ratingRepository.findByUserByUserIdAndGameByGameId(user, challenge.getGameByGameId()).getRating();
            }

            Game game = challenge.getGameByGameId();
            MapModel mapModel = new MapModel();
            mapModel.setChallengeId(challenge.getId());
            mapModel.setHostId(host.getId());
            mapModel.setHostName(host.getUserName());
            mapModel.setGameName(game.getName());
            mapModel.setRating(rating);
            mapModel.setDescription(challenge.getDescription());
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
