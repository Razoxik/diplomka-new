package com.bartosektom.letsplayfolks.service.impl;

import com.bartosektom.letsplayfolks.constants.ChallengeStateConstants;
import com.bartosektom.letsplayfolks.constants.GameParamConstants;
import com.bartosektom.letsplayfolks.constants.QuestionableChallengeReason;
import com.bartosektom.letsplayfolks.constants.ResultStateConstants;
import com.bartosektom.letsplayfolks.entity.Challenge;
import com.bartosektom.letsplayfolks.entity.ChallengeResult;
import com.bartosektom.letsplayfolks.entity.ChallengeState;
import com.bartosektom.letsplayfolks.entity.Game;
import com.bartosektom.letsplayfolks.entity.Rating;
import com.bartosektom.letsplayfolks.entity.ResultState;
import com.bartosektom.letsplayfolks.entity.User;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import com.bartosektom.letsplayfolks.exception.UnexpectedChallengeException;
import com.bartosektom.letsplayfolks.manager.SessionManager;
import com.bartosektom.letsplayfolks.model.ChallengeDetailModel;
import com.bartosektom.letsplayfolks.model.ChallengeDetailUserModel;
import com.bartosektom.letsplayfolks.model.ChallengeModel;
import com.bartosektom.letsplayfolks.model.ChallengeResultModel;
import com.bartosektom.letsplayfolks.model.QuestionableChallengeModel;
import com.bartosektom.letsplayfolks.repository.ChallengeRepository;
import com.bartosektom.letsplayfolks.repository.ChallengeResultRepository;
import com.bartosektom.letsplayfolks.repository.ChallengeStateRepository;
import com.bartosektom.letsplayfolks.repository.GameParamRepository;
import com.bartosektom.letsplayfolks.repository.GameRepository;
import com.bartosektom.letsplayfolks.repository.RatingRepository;
import com.bartosektom.letsplayfolks.repository.ResultStateRepository;
import com.bartosektom.letsplayfolks.repository.UserRepository;
import com.bartosektom.letsplayfolks.repository.UserRepositoryCustom;
import com.bartosektom.letsplayfolks.service.ChallengeResultService;
import com.bartosektom.letsplayfolks.service.ChallengeService;
import com.bartosektom.letsplayfolks.service.FriendService;
import com.bartosektom.letsplayfolks.tools.LocalDateTimeJPAConverter;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    FriendService friendService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    ChallengeResultRepository challengeResultRepository;

    @Autowired
    GameParamRepository gameParamRepository;

    @Autowired
    ChallengeResultService challengeResultService;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    UserRepositoryCustom userRepositoryCustom;

    @Autowired
    ResultStateRepository resultStateRepository;

    @Autowired
    SessionManager sessionManager;

    @Autowired
    ChallengeStateRepository challengeStateRepository;

    @Override
    public boolean isUserAlreadyInChallenge(Challenge challenge) {
        User user = userRepository.findById(sessionManager.getUserId()).orElse(null);
        return user != null && userRepositoryCustom.findAllChallengeUsersByChallenge(challenge).contains(user);
    }

    @Override
    public boolean isChallengeFinished(Challenge challenge) {
        return ChallengeStateConstants.FINISHED.equals(challenge.getChallengeStateByChallengeStateId().getState());
    }

    @Override
    public boolean isChallengeFull(Challenge challenge) {
        int numberOfPlayers = Integer.parseInt(gameParamRepository.findByGameByGameIdAndName(
                challenge.getGameByGameId(), GameParamConstants.NUMBER_OF_PLAYERS).getValue());
        return userRepositoryCustom.findAllChallengeUsersByChallenge(challenge).size() >= numberOfPlayers;
    }

    /**
     * User can enter result if there is 1: that many people, that game needs
     * 2: if its after start date of challenge
     *
     * @param challenge s
     * @return s
     */
    @Override
    public boolean canUserEnterResult(Challenge challenge) {
        int numberOfPlayers = Integer.parseInt(gameParamRepository.findByGameByGameIdAndName(
                challenge.getGameByGameId(), GameParamConstants.NUMBER_OF_PLAYERS).getValue());
        int numberOfPlayersInChallenge = challenge.getChallengeResultsById().size();
        Date date = challenge.getStart();

        return numberOfPlayers == numberOfPlayersInChallenge && new Date().after(date);
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
    public List<ChallengeDetailUserModel> prepareChallengeDetailUserModels(Challenge challenge, List<User> users) throws EntityNotFoundException {
        Game game = challenge.getGameByGameId();

        List<ChallengeDetailUserModel> challengeDetailUserModels = new ArrayList<>();
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

            ChallengeDetailUserModel challengeDetailUserModel = new ChallengeDetailUserModel();
            challengeDetailUserModel.setId(userId);
            challengeDetailUserModel.setRating(rating);
            challengeDetailUserModel.setUserName(userName);
            challengeDetailUserModel.setNumberOfWins(numberOfWins);
            challengeDetailUserModel.setNumberOfLosses(numberOfLosses);
            challengeDetailUserModel.setNumberOfTies(numberOfTies);
            challengeDetailUserModel.setNumberOfGames(totalNumberOfGames);
            challengeDetailUserModel.setCanBeAddToFriends(friendService.canBeAddedToFriends(userId));
            if (!ResultStateConstants.IN_PROGRESS.equals(result.getResultStateByResultStateId().getState())) {
                challengeDetailUserModel.setWinningUserScore(result.getScoreWinner());
                challengeDetailUserModel.setLosingUserScore(result.getScoreDefeated());
                challengeDetailUserModel.setChallengeResultState(result.getResultStateByResultStateId().getState());
            } else {
                challengeDetailUserModel.setChallengeResultState(ResultStateConstants.IN_PROGRESS);
            }
            // can be added to Friends? to by melo fakat

            challengeDetailUserModels.add(challengeDetailUserModel);
        }
        return challengeDetailUserModels;
    }

    /**
     * Prepare BOTH teams of User Dtos
     *
     * @param challenge x
     * @return x
     */

    @Override
    public Pair<List<ChallengeDetailUserModel>, List<ChallengeDetailUserModel>> prepareTeamsDtos(Challenge challenge)
            throws EntityNotFoundException {
        Pair<List<User>, List<User>> teamsUsers = prepareTeamsUsers(challenge);
        List<ChallengeDetailUserModel> firstTeam = prepareChallengeDetailUserModels(challenge, teamsUsers.getKey());
        List<ChallengeDetailUserModel> secondTeam = prepareChallengeDetailUserModels(challenge, teamsUsers.getValue());

        return Pair.of(firstTeam, secondTeam);
    }

    // Pokud ZADANE vyzsledky u vyzvy nejsou shodne tak je vyzva sporna
    // Pokud je vyzva dyl jak 10 dni po ukonceni bez skore je sporna
    //for each Challenge
    //       find Result v challengeresult repku
    //      list<resultu pro tu challenge>
    //check if score of every result challenge is same if true add to list to decide
    @Override
    public List<QuestionableChallengeModel> prepareQuestionableChallengeModels() throws UnexpectedChallengeException {
        List<QuestionableChallengeModel> questionableChallengeModels = new ArrayList<>();

        for (Challenge challenge : challengeRepository.findAll()) {
            if (challengeResultService.isChallengeResultScoreDifferent(challenge) ||
                    challengeResultService.isChallengeTooLongWithoutScore(challenge)) {
                QuestionableChallengeModel questionableChallengeModel = new QuestionableChallengeModel();
                questionableChallengeModel.setId(challenge.getId());
                LocalDateTimeJPAConverter converter = new LocalDateTimeJPAConverter();
                LocalDateTime challengeCreated = converter.convertToEntityAttribute(challenge.getCreated());
                LocalDateTime challengeEnd = converter.convertToEntityAttribute(challenge.getEnd());
                questionableChallengeModel.setCreated(challengeCreated);
                questionableChallengeModel.setEnd(challengeEnd);
                questionableChallengeModel.setGameName(challenge.getGameByGameId().getName());
                if (challengeResultService.isChallengeResultScoreDifferent(challenge)) {
                    questionableChallengeModel.setReason(QuestionableChallengeReason.DIFFERENT_SCORE);
                } else {
                    questionableChallengeModel.setReason(QuestionableChallengeReason.TOO_LONG_WITHOUT_SCORE);
                }
                questionableChallengeModels.add(questionableChallengeModel);
            }
        }
        return questionableChallengeModels;
    }

    // Challenge má být převedena do stavu finished v případě, že všichni uživatelé zadali shodné skore
    // tzn. V případě hry fotbal musí 8 hráčů zadat shodné skore
    // potom previst challenge do FINISHED - nezobrazovat na mape, a prepocitat rating hracu
    private boolean shouldBeChallengeFinished(Challenge challenge) throws UnexpectedChallengeException {
        List<ChallengeResult> challengeResults = challengeResultRepository.findByChallengeByChallengeId(challenge);
        int numberOfPlayers = Integer.parseInt(gameParamRepository.findByGameByGameIdAndName(
                challenge.getGameByGameId(), GameParamConstants.NUMBER_OF_PLAYERS).getValue());

        if (challengeResults.isEmpty()) {
            throw new UnexpectedChallengeException("Challenge without result should not exist.");
        }

        // mame tolik vysledku kolik je hracu? neboli máme od každého hráče výsledek?
        // pokud ne, tak vyzva neni dokoncena a vratit false
        if (challengeResults.size() != numberOfPlayers) {
            return false;
        }

        // kdyz mame od kazdeho hrace vysledek tak se jen kouknem jestli se shoduji
        Integer scoreWinner = challengeResults.get(0).getScoreWinner();
        Integer scoreLoser = challengeResults.get(0).getScoreDefeated();

        // pokud se nějaky shodovat nebude vratit false - jde na rozhodovani operatora
        // nutno kontrolovat i ten vysledek WIN/LOSER, aby kdyz daj oba ze vyhrali s 1:0 to neproslo i kdyz to skore je stejny zejo
        // Pokud tam bude víc vítězů než je půlka number of players - zkrátka na 8 hráčů, když tam zadá 5 lidí vítězství, tak je něco špatně
        int maxPlayers = Integer.parseInt(gameParamRepository.findByGameByGameIdAndName(
                challenge.getGameByGameId(), GameParamConstants.NUMBER_OF_PLAYERS).getValue());
        int maxAllowedWinners = maxPlayers / 2;
        int numberOfWinners = 0;
        int maxAllowedLosers = maxPlayers / 2;
        int numberOfLosers = 0;
        for (ChallengeResult challengeResult : challengeResults) {
            if (challengeResult.getResultStateByResultStateId().getState().equals(ResultStateConstants.WINNER)) {
                numberOfWinners++;
            }
            if (challengeResult.getResultStateByResultStateId().getState().equals(ResultStateConstants.DEFEATED)) {
                numberOfLosers++;
            }
            if (((numberOfWinners > maxAllowedWinners || numberOfLosers > maxAllowedLosers) ||
                    (challengeResult.getScoreWinner() == null || !challengeResult.getScoreWinner().equals(scoreWinner)) ||
                    (challengeResult.getScoreDefeated() == null || !challengeResult.getScoreDefeated().equals(scoreLoser)))) {
                return false;
            }
        }
        // v pripade ze mame 1. tolik vysledku kolik je hracu teto hry
        //                  2. a vsechny se shoduji vratime true, vyzva muze byt ukoncena
        return true;
    }

    @Override
    public void finishChallenge(User user, Challenge challenge, ChallengeResultModel challengeResultModel) throws UnexpectedChallengeException {
        // Steps for finishing challenge
        // 1. Submit challenge results - every time
        submitChallengeResults(user, challenge, challengeResultModel);
        // 2. Check if challenge should be finished - ie. Change to FINISHED state and recalculate players rating
        if (shouldBeChallengeFinished(challenge)) {
            // 3. Change challenge state to FINISHED
            challenge.setChallengeStateByChallengeStateId(challengeStateRepository.findByState(ChallengeStateConstants.FINISHED));
            challengeRepository.save(challenge);
            // 4. Recalculate challenge players rating based on their results
            recalculateRatingAfterFinishedChallenge(challenge);
        }
    }

    @Override
    public void createChallenge(ChallengeModel challengeModel) throws EntityNotFoundException {
        Challenge challenge = new Challenge();
        ChallengeState challengeState = challengeStateRepository.findByState(ChallengeStateConstants.CREATED);
        Game game = gameRepository.findById(challengeModel.getGameId()).orElseThrow(() -> new EntityNotFoundException("Game does not found"));

        challenge.setChallengeStateByChallengeStateId(challengeState);
        challenge.setGameByGameId(game);
        challenge.setStart(Timestamp.valueOf(challengeModel.getStart()));
        challenge.setEnd(Timestamp.valueOf(challengeModel.getEnd()));
        challenge.setCoordsLat(challengeModel.getLatCoords());
        challenge.setCoordsLng(challengeModel.getLngCoords());
        challenge.setDescription(challengeModel.getDescription());
        challenge.setPassword(challengeModel.getPassword());
        challengeRepository.save(challenge);

        ChallengeResult challengeResult = new ChallengeResult();
        User user = userRepository.findById(sessionManager.getUserId()).orElseThrow(EntityNotFoundException::new);
        ResultState resultState = resultStateRepository.findByState(ResultStateConstants.IN_PROGRESS);

        challengeResult.setChallengeByChallengeId(challenge);
        challengeResult.setUserByUserId(user);
        challengeResult.setResultStateByResultStateId(resultState);
        challengeResultRepository.save(challengeResult);
    }

    @Override
    public void joinChallenge(int challengeId) throws EntityNotFoundException {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(sessionManager.getUserId()).orElseThrow(EntityNotFoundException::new);
        ResultState resultState = resultStateRepository.findByState(ResultStateConstants.IN_PROGRESS);

        ChallengeResult challengeResult = new ChallengeResult();
        challengeResult.setChallengeByChallengeId(challenge);
        challengeResult.setUserByUserId(user);
        challengeResult.setResultStateByResultStateId(resultState);
        challengeResultRepository.save(challengeResult);

        // IF number of people in game is ten počet max
        // IF je po START čase vyzvy
        challenge.setChallengeStateByChallengeStateId(challengeStateRepository.findByState(ChallengeStateConstants.IN_PROGRESS));
        challengeRepository.save(challenge);
    }

    @Override
    public boolean leaveChallenge(int challengeId) throws EntityNotFoundException {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(sessionManager.getUserId()).orElseThrow(EntityNotFoundException::new);

        ChallengeResult challengeResult = challengeResultRepository.findByUserByUserIdAndChallengeByChallengeId(user, challenge);
        challengeResultRepository.delete(challengeResult);

        // Pokud jsem poslední na výzvě a odcházím tak smazat.
        if (challengeResultRepository.findByChallengeByChallengeId(challenge).isEmpty()) {
            challengeRepository.delete(challenge);
            return false;
        }
        return true;
    }

    @Override
    public ChallengeDetailModel prepareChallengeDetailDto(Challenge challenge) throws EntityNotFoundException {
        Game game = challenge.getGameByGameId();

        ChallengeDetailModel challengeDetailModel = new ChallengeDetailModel();
        Pair<List<ChallengeDetailUserModel>, List<ChallengeDetailUserModel>> teamsDtos = prepareTeamsDtos(challenge);
        int maxPlayers = Integer.parseInt(gameParamRepository.findByGameByGameIdAndName(
                game, GameParamConstants.NUMBER_OF_PLAYERS).getValue());
        challengeDetailModel.setFirstTeam(teamsDtos.getLeft());
        challengeDetailModel.setSecondTeam(teamsDtos.getRight());
        challengeDetailModel.setMaxPlayers(maxPlayers);

        return challengeDetailModel;
    }

    private void submitChallengeResults(User user, Challenge challenge, ChallengeResultModel challengeResultModel) {
        ChallengeResult challengeResult = challengeResultRepository.findByUserByUserIdAndChallengeByChallengeId(user, challenge);
        challengeResult.setResultStateByResultStateId(resultStateRepository.findByState(challengeResultModel.getResultState()));
        challengeResult.setScoreWinner(challengeResultModel.getWinnerScore());
        challengeResult.setScoreDefeated(challengeResultModel.getLoserScore());
        challengeResult.setDescription(challengeResultModel.getDescription());
        challengeResultRepository.save(challengeResult);
    }

    private void recalculateRatingAfterFinishedChallenge(Challenge challenge) {
        Integer ratingTeam1 = calculateTeamRating(challenge, 1);
        Integer ratingTeam2 = calculateTeamRating(challenge, 2);
        // V případě výhry vyhrajeme 1% ratingu SOUPEŘE.
        // V případě prohry prohrajeme 1% ratingu NAŠEHO.
        // V případě remízy vyhrajeme/prohrajeme 0.5% ratingu, na základě toho jestli jsme měli/neměli větší rating než druhý tým.
        for (ChallengeResult challengeResult : challengeResultRepository.findByChallengeByChallengeId(challenge)) {
            Rating rating = ratingRepository.findByUserByUserIdAndGameByGameId(challengeResult.getUserByUserId(), challenge.getGameByGameId());
            int recalculatedRating = rating.getRating();
            switch (challengeResult.getResultStateByResultStateId().getState()) {
                case "WINNER":
                    if (challengeResult.getTeamNumber() == 1) {
                        recalculatedRating += (ratingTeam2 / 100);
                    } else {
                        recalculatedRating += (ratingTeam1 / 100);
                    }
                    break;
                case "DEFEATED":
                    if (challengeResult.getTeamNumber() == 1) {
                        recalculatedRating -= (ratingTeam1 / 100);
                    } else {
                        recalculatedRating -= (ratingTeam2 / 100);
                    }
                    break;
                case "TIE":
                    if (challengeResult.getTeamNumber() == 1 && ratingTeam1 > ratingTeam2) {
                        recalculatedRating -= (ratingTeam1 / 200);
                    }
                    if (challengeResult.getTeamNumber() == 1 && ratingTeam1 < ratingTeam2) {
                        recalculatedRating += (ratingTeam2 / 200);
                    }
                    if (challengeResult.getTeamNumber() == 2 && ratingTeam2 > ratingTeam1) {
                        recalculatedRating -= (ratingTeam2 / 200);
                    }
                    if (challengeResult.getTeamNumber() == 2 && ratingTeam2 < ratingTeam1) {
                        recalculatedRating += (ratingTeam1 / 200);
                    }
                    break;
                default:
                    break;
            }
            rating.setRating(recalculatedRating);
            ratingRepository.save(rating);
        }
    }

    private Integer calculateTeamRating(Challenge challenge, int teamNumber) {
        int numberOfPlayers = Integer.parseInt(gameParamRepository.findByGameByGameIdAndName(
                challenge.getGameByGameId(), GameParamConstants.NUMBER_OF_PLAYERS).getValue());

        List<User> teamUsers = challengeResultRepository.findByChallengeByChallengeIdAndTeamNumber(challenge, teamNumber)
                .stream()
                .map(ChallengeResult::getUserByUserId).collect(Collectors.toList());

        Integer teamRating = 0;
        for (User u : teamUsers) {
            teamRating += ratingRepository.findByUserByUserIdAndGameByGameId(u, challenge.getGameByGameId()).getRating();
        }
        return teamRating / (numberOfPlayers / 2);
    }
}
