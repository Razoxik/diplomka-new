package com.bartosektom.letsplayfolks.service.impl;

import com.bartosektom.letsplayfolks.exception.UnexpectedChallengeException;
import com.bartosektom.letsplayfolks.service.ChallengeResultService;
import com.bartosektom.letsplayfolks.constants.ChallengeStateConstants;
import com.bartosektom.letsplayfolks.constants.GameParamConstants;
import com.bartosektom.letsplayfolks.constants.ResultStateConstants;
import com.bartosektom.letsplayfolks.entity.Challenge;
import com.bartosektom.letsplayfolks.entity.ChallengeResult;
import com.bartosektom.letsplayfolks.entity.Game;
import com.bartosektom.letsplayfolks.repository.ChallengeResultRepository;
import com.bartosektom.letsplayfolks.repository.GameParamRepository;
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
     * Pokud se všechny scoreWinner a scoreLoser ve vyzve nerovnaji tak je skore rozdilne
     * Krome skore se take musi rovnat vysledek vyzvy tzn. VITEZ, PROHRANY, aby vsicihni nerekli ze vyhrali se stejnym skorem
     * Kontroluješ tohle pouze u zadanych hodnot tzn. NE STAV IN PROGRESS u result statu
     *
     * @param challenge challenge
     * @return true or false
     * @throws UnexpectedChallengeException sdas
     */
    @Override
    public boolean isChallengeResultScoreSame(@NonNull Challenge challenge) throws UnexpectedChallengeException {
        List<ChallengeResult> challengeResults = challengeResultRepository.findByChallengeByChallengeId(challenge);

        if (challengeResults.isEmpty()) {
            throw new UnexpectedChallengeException("Challenge without result should not exist.");
        }
        // pracujes s Integerama protoze to muze byt null a do int null nedas
        Integer scoreWinner = challengeResults.get(0).getScoreWinner();
        Integer scoreLoser = challengeResults.get(0).getScoreDefeated();
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
            if (!challengeResult.getResultStateByResultStateId().getState().equals(ResultStateConstants.IN_PROGRESS) &&
                    ((numberOfWinners > maxAllowedWinners || numberOfLosers > maxAllowedLosers) ||
                            (challengeResult.getScoreWinner() == null || !challengeResult.getScoreWinner().equals(scoreWinner)) ||
                            (challengeResult.getScoreDefeated() == null || !challengeResult.getScoreDefeated().equals(scoreLoser)))) {
                return false;
            }
        }
        return true;
    }

    // Challenge je poslana na rozhodovani pokud nedosahla stavu FINISHED do 3 dnu od Konce vyzvy
    // Do stavu finished se dostane, kdyz tam vsichni zadaji stejne skore
    // presunou to dchallengeservice
    public boolean isChallengeTooLongWithoutScore(@NonNull Challenge challenge) {
        Timestamp challengeEndDate = challenge.getEnd();
        LocalDate date = LocalDate.now().minusDays(3);

        return !challenge.getChallengeStateByChallengeStateId().getState().equals(ChallengeStateConstants.FINISHED) &&
                challengeEndDate.before(Date.valueOf(date));
    }
}
