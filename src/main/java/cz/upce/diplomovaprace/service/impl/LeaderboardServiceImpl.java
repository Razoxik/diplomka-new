package cz.upce.diplomovaprace.service.impl;

import cz.upce.diplomovaprace.constants.ResultStateConstants;
import cz.upce.diplomovaprace.entity.ChallengeResult;
import cz.upce.diplomovaprace.entity.Game;
import cz.upce.diplomovaprace.entity.User;
import cz.upce.diplomovaprace.exception.EntityNotFoundException;
import cz.upce.diplomovaprace.model.LeaderboardModel;
import cz.upce.diplomovaprace.repository.ChallengeRepository;
import cz.upce.diplomovaprace.repository.ChallengeResultRepository;
import cz.upce.diplomovaprace.repository.GameRepository;
import cz.upce.diplomovaprace.repository.RatingRepository;
import cz.upce.diplomovaprace.repository.UserRepository;
import cz.upce.diplomovaprace.service.ChallengeResultService;
import cz.upce.diplomovaprace.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        // Najdi všechny uživatele, najdi pro každýho uživatele jeho challenge resulty a z nich vem pouze ty co se týkají vybrané hry
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
            long winningPercentage = totalNumberOfGames != 0 ? (numberOfWins / totalNumberOfGames) * 100 : 0;
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
        return leaderboardModels;
    }
}
