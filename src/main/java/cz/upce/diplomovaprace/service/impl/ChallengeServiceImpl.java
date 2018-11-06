package cz.upce.diplomovaprace.service.impl;

import cz.upce.diplomovaprace.dto.UserDto;
import cz.upce.diplomovaprace.entity.Challenge;
import cz.upce.diplomovaprace.entity.ChallengeResult;
import cz.upce.diplomovaprace.entity.Game;
import cz.upce.diplomovaprace.entity.User;
import cz.upce.diplomovaprace.enums.ChallengeStateConstants;
import cz.upce.diplomovaprace.enums.ResultStateConstants;
import cz.upce.diplomovaprace.manager.SessionManager;
import cz.upce.diplomovaprace.repository.ChallengeResultRepository;
import cz.upce.diplomovaprace.repository.RatingRepository;
import cz.upce.diplomovaprace.repository.ResultStateRepository;
import cz.upce.diplomovaprace.repository.UserRepository;
import cz.upce.diplomovaprace.repository.UserRepositoryCustom;
import cz.upce.diplomovaprace.service.ChallengeService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChallengeResultRepository challengeResultRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    UserRepositoryCustom userRepositoryCustom;

    @Autowired
    ResultStateRepository resultStateRepository;

    @Autowired
    SessionManager sessionManager;

    @Override
    public boolean isUserAlreadyInChallenge(Challenge challenge) {
        User user = userRepository.findById(sessionManager.getUserId()).orElse(null);
        return user != null && userRepositoryCustom.findAllChallengeUsersByChallenge(challenge).contains(user);
    }

    @Override
    public boolean isChallengeFinished(Challenge challenge) {
        return ChallengeStateConstants.FINISHED.equals(challenge.getChallengeStateByChallengeStateId().getState());
    }

    private void recalculateTeams(Challenge challenge) {
        List<ChallengeResult> challengeResults = challengeResultRepository.findByChallengeByChallengeId(challenge);
        List<User> users = challengeResults.stream().map(ChallengeResult::getUserByUserId).collect(Collectors.toList());
        Game game = challenge.getGameByGameId();
        // sort players based on rating
        List<Pair<User, Integer>> usersWithRatings = new ArrayList<>();
        for (User user : users) {
            int rating = ratingRepository.findByUserByUserIdAndGameByGameId(user, game).getRating();
            Pair<User, Integer> userWithRating = Pair.of(user, rating);
            usersWithRatings.add(userWithRating);
        }
        usersWithRatings.sort(Comparator.comparingInt(Pair::getValue));
        Collections.reverse(usersWithRatings); // we want players with better rankings be in team 1

        //set teams for players based on their ratings
        for (int i = 0; i < usersWithRatings.size(); i++) {
            ChallengeResult challengeResult = challengeResultRepository.findByUserByUserIdAndChallengeByChallengeId(usersWithRatings.get(i).getKey(), challenge);
            if (i % 2 == 0) {
                challengeResult.setTeamNumber(1);
            } else {
                challengeResult.setTeamNumber(2);
            }
            challengeResultRepository.save(challengeResult);
        }
    }

    /**
     * // First value = team 1; second value = team 2
     *
     * @param challenge x
     * @return x
     */

    @Override
    public Pair<List<User>, List<User>> prepareTeamsUsers(Challenge challenge) {
        recalculateTeams(challenge);
        List<ChallengeResult> firstTeamChallengeResult = challengeResultRepository.findByChallengeByChallengeIdAndTeamNumber(challenge, 1);
        List<ChallengeResult> secondTeamChallengeResult = challengeResultRepository.findByChallengeByChallengeIdAndTeamNumber(challenge, 2);
        List<User> firstTeamUsers = firstTeamChallengeResult.stream().map(ChallengeResult::getUserByUserId).collect(Collectors.toList());
        List<User> secondTeamUsers = secondTeamChallengeResult.stream().map(ChallengeResult::getUserByUserId).collect(Collectors.toList());

        return Pair.of(firstTeamUsers, secondTeamUsers);
    }

    /**
     * Prepare ONE team of UserDtos, TODO MAKE THIS METHOD PRIVATE AND SO OTHERS
     *
     * @param challenge x
     * @param users     x
     * @return x
     */

    @Override
    public List<UserDto> prepareTeamDtos(Challenge challenge, List<User> users) {
        Game game = challenge.getGameByGameId();

        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            int userId = user.getId();
            int rating = ratingRepository.findByUserByUserIdAndGameByGameId(user, game).getRating();
            String userName = user.getUserName();
            // TADY TI CHYBI JESTE ZAS FILTER NA GAME, JINAK TI TO POCITA VYHRY ZE VSECH HER a ne treba jen z šachů
            // udelat metodu return Triple<int, int int> xx (User user, Game game) ktera bude vracet win, loose, tie pro hrace pro tu hru

            int numberOfWins = (int) challengeResultRepository.findByUserByUserIdAndResultStateByResultStateId(
                    user, resultStateRepository.findByState(ResultStateConstants.WINNER)).stream().filter(
                    challengeResult -> challengeResult.getChallengeByChallengeId().getGameByGameId().equals(challenge.getGameByGameId())).count();
            int numberOfLosses = challengeResultRepository.findByUserByUserIdAndResultStateByResultStateId(
                    user, resultStateRepository.findByState(ResultStateConstants.DEFEATED)).size();
            int numberOfTies = challengeResultRepository.findByUserByUserIdAndResultStateByResultStateId(
                    user, resultStateRepository.findByState(ResultStateConstants.TIE)).size();
            int totalNumberOfGames = numberOfWins + numberOfLosses + numberOfTies;
            ChallengeResult result = challengeResultRepository.findByUserByUserIdAndChallengeByChallengeId(user, challenge);

            UserDto userDto = new UserDto();
            userDto.setId(userId);
            userDto.setRating(rating);
            userDto.setUserName(userName);
            userDto.setNumberOfWins(numberOfWins);
            userDto.setNumberOfLosses(numberOfLosses);
            userDto.setNumberOfTies(numberOfTies);
            userDto.setNumberOfGames(totalNumberOfGames);
            if (!ResultStateConstants.IN_PROGRESS.equals(result.getResultStateByResultStateId().getState())) {
                userDto.setWinningUserScore(result.getScoreWinner());
                userDto.setLossingUserScore(result.getScoreDefeated());
            } else {
                userDto.setChallengeResultState(ResultStateConstants.IN_PROGRESS);
            }
            userDtos.add(userDto);
        }
        return userDtos;
    }

    /**
     * Prepare BOTH teams of User Dtos
     *
     * @param challenge x
     * @return x
     */

    @Override
    public Pair<List<UserDto>, List<UserDto>> prepareTeamsDtos(Challenge challenge) {
        Pair<List<User>, List<User>> teamsUsers = prepareTeamsUsers(challenge);
        List<UserDto> firstTeam = prepareTeamDtos(challenge, teamsUsers.getKey());
        List<UserDto> secondTeam = prepareTeamDtos(challenge, teamsUsers.getValue());

        return Pair.of(firstTeam, secondTeam);
    }
}
