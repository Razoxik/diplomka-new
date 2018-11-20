package cz.upce.diplomovaprace.service.impl;

import cz.upce.diplomovaprace.entity.ChallengeResult;
import cz.upce.diplomovaprace.entity.Game;
import cz.upce.diplomovaprace.service.ChallengeResultService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChallengeResultServiceImpl implements ChallengeResultService {

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
}
