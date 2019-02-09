package cz.upce.diplomovaprace.service;

import cz.upce.diplomovaprace.entity.Challenge;
import cz.upce.diplomovaprace.entity.ChallengeResult;
import cz.upce.diplomovaprace.entity.Game;
import cz.upce.diplomovaprace.exception.UnexceptedChallengeException;

import java.util.List;

public interface ChallengeResultService {

    List<ChallengeResult> findChallengeResultsForGame(List<ChallengeResult> challengeResults, Game game);

    long countChallengeResultsForResultState(List<ChallengeResult> challengeResults, String resultState);

    boolean isChallengeResultScoreSame(Challenge challenge) throws UnexceptedChallengeException;

    boolean isChallengeTooLongWithoutScore(Challenge challenge) throws UnexceptedChallengeException;
}
