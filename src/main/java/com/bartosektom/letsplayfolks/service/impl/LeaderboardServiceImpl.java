package com.bartosektom.letsplayfolks.service.impl;

import com.bartosektom.letsplayfolks.model.LeaderboardModel;
import com.bartosektom.letsplayfolks.repository.UserRepository;
import com.bartosektom.letsplayfolks.service.ChallengeResultService;
import com.bartosektom.letsplayfolks.service.LeaderboardService;
import com.bartosektom.letsplayfolks.constants.ResultStateConstants;
import com.bartosektom.letsplayfolks.entity.ChallengeResult;
import com.bartosektom.letsplayfolks.entity.Game;
import com.bartosektom.letsplayfolks.entity.User;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import com.bartosektom.letsplayfolks.repository.ChallengeRepository;
import com.bartosektom.letsplayfolks.repository.ChallengeResultRepository;
import com.bartosektom.letsplayfolks.repository.GameRepository;
import com.bartosektom.letsplayfolks.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class LeaderboardServiceImpl implements LeaderboardService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    ChallengeResultRepository challengeResultRepository;

    @Autowired
    ChallengeResultService challengeResultService;

    @Autowired
    GameRepository gameRepository;

    @Override
    public List<LeaderboardModel> prepareLeaderboardModels(Integer gameId) throws EntityNotFoundException {
        Game game = gameRepository.findById(gameId).orElseThrow(EntityNotFoundException::new);
        List<LeaderboardModel> leaderboardModels = new ArrayList<>();

        // FIND ALL USERS
        for (User user : userRepository.findAll()) {
            // FIND ALL CHALLENGE RESULTS FOR USER
            List<ChallengeResult> chs = challengeResultRepository.findByUserByUserId(user);
            // FIND ONLY CHALLENGES RESULTS FOR SELECTED GAME
            List<ChallengeResult> userGameChallengeResult = challengeResultService.findChallengeResultsForGame(chs, game);

            String gameName = game.getName();
            long userId = user.getId();
            String userName = user.getUserName();
            long numberOfWins = challengeResultService.countChallengeResultsForResultState(userGameChallengeResult, ResultStateConstants.WINNER);
            long numberOfLoses = challengeResultService.countChallengeResultsForResultState(userGameChallengeResult, ResultStateConstants.DEFEATED);
            long numberOfTies = challengeResultService.countChallengeResultsForResultState(userGameChallengeResult, ResultStateConstants.TIE);
            long totalNumberOfGames = numberOfWins + numberOfLoses + numberOfTies;
            long winningPercentage = totalNumberOfGames != 0 ? (numberOfWins * 100 / totalNumberOfGames)  : 0;
            Integer rating = ratingRepository.findByUserByUserIdAndGameByGameId(user, game).getRating();

            LeaderboardModel leaderboardModel = new LeaderboardModel();
            leaderboardModel.setGameId(gameId);
            leaderboardModel.setGameName(gameName);
            leaderboardModel.setUserId(userId);
            leaderboardModel.setUserName(userName);
            leaderboardModel.setNumberOfGames(totalNumberOfGames);
            leaderboardModel.setNumberOfWins(numberOfWins);
            leaderboardModel.setNumberOfLooses(numberOfLoses);
            leaderboardModel.setNumberOfTies(numberOfTies);
            leaderboardModel.setWinRateRatio(winningPercentage);
            leaderboardModel.setRating(rating);
            leaderboardModels.add(leaderboardModel);
        }
        leaderboardModels.sort(Comparator.comparingLong(LeaderboardModel::getRating).reversed());

        return leaderboardModels;
    }
}
