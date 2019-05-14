package com.bartosektom.letsplayfolks.service.impl;

import com.bartosektom.letsplayfolks.constants.ChallengeStateConstants;
import com.bartosektom.letsplayfolks.constants.GameParamConstants;
import com.bartosektom.letsplayfolks.constants.ResultStateConstants;
import com.bartosektom.letsplayfolks.entity.Challenge;
import com.bartosektom.letsplayfolks.entity.ChallengeResult;
import com.bartosektom.letsplayfolks.entity.Game;
import com.bartosektom.letsplayfolks.exception.UnexpectedChallengeException;
import com.bartosektom.letsplayfolks.repository.ChallengeResultRepository;
import com.bartosektom.letsplayfolks.repository.GameParamRepository;
import com.bartosektom.letsplayfolks.service.ChallengeResultService;
import io.micrometer.core.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChallengeResultServiceImpl implements ChallengeResultService {

    @Autowired
    ChallengeResultRepository challengeResultRepository;

    @Autowired
    GameParamRepository gameParamRepository;

    @Override
    public List<ChallengeResult> findChallengeResultsForGame(List<ChallengeResult> challengeResults, Game game) {
        return challengeResults.stream().filter(challengeResult ->
                challengeResult.getChallengeByChallengeId().getGameByGameId().equals(game)).
                collect(Collectors.toList());
    }

    @Override
    public long countChallengeResultsForResultState(List<ChallengeResult> challengeResults, String resultState) {
        return challengeResults.stream().filter(challengeResult ->
                challengeResult.getResultStateByResultStateId().getState().equals(resultState)).count();
    }

    /**
     * If all scoreWinner and scoreLoser are not same
     * Beside score, we need to check even result state eg. winner, loser
     * Only check for inserted result score - that means not for result state IN PROGRESS
     *
     * @param challenge challenge
     * @return true or false
     * @throws UnexpectedChallengeException when challenge has unexpected state
     */
    @Override
    public boolean isChallengeResultScoreDifferent(@NonNull Challenge challenge) throws UnexpectedChallengeException {
        List<ChallengeResult> challengeResults = challengeResultRepository.findByChallengeByChallengeId(challenge);

        if (challengeResults.isEmpty()) {
            throw new UnexpectedChallengeException("Challenge without result should not exist.");
        }
        // Need Integers because it can be null.
        Integer scoreWinner = challengeResults.get(0).getScoreWinner();
        Integer scoreLoser = challengeResults.get(0).getScoreDefeated();
        // If there is more winners than half of the players
        // eg if on 8 players there is 5 winners there is there is something bad
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
            if (!challengeResult.getResultStateByResultStateId().getState().equals(ResultStateConstants.IN_PROGRESS) &&
                    ((numberOfWinners > maxAllowedWinners || numberOfLosers > maxAllowedLosers) ||
                            (challengeResult.getScoreWinner() == null || !challengeResult.getScoreWinner().equals(scoreWinner)) ||
                            (challengeResult.getScoreDefeated() == null || !challengeResult.getScoreDefeated().equals(scoreLoser)))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Challenge is too long without score if there is not Finished state after 3 days after challenge end.
     * Finished is after everybody put same score
     *
     * @param challenge challenge
     * @return true or false
     */
    public boolean isChallengeTooLongWithoutScore(@NonNull Challenge challenge) {
        Timestamp challengeEndDate = challenge.getEnd();
        LocalDate date = LocalDate.now().minusDays(3);

        return !challenge.getChallengeStateByChallengeStateId().getState().equals(ChallengeStateConstants.FINISHED) &&
                challengeEndDate.before(Date.valueOf(date));
    }
}
