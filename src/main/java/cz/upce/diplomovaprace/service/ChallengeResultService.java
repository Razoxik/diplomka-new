package cz.upce.diplomovaprace.service;

import cz.upce.diplomovaprace.entity.ChallengeResult;
import cz.upce.diplomovaprace.entity.Game;

import java.util.List;

public interface ChallengeResultService {

    List<ChallengeResult> findChallengeResultsForGame(List<ChallengeResult> challengeResults, Game game);

    long countChallengeResultsForResultState(List<ChallengeResult> challengeResults, String resultState);
}
