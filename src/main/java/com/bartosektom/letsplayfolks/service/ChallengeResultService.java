package com.bartosektom.letsplayfolks.service;

import com.bartosektom.letsplayfolks.exception.UnexceptedChallengeException;
import com.bartosektom.letsplayfolks.entity.Challenge;
import com.bartosektom.letsplayfolks.entity.ChallengeResult;
import com.bartosektom.letsplayfolks.entity.Game;

import java.util.List;

public interface ChallengeResultService {

    List<ChallengeResult> findChallengeResultsForGame(List<ChallengeResult> challengeResults, Game game);

    long countChallengeResultsForResultState(List<ChallengeResult> challengeResults, String resultState);

    boolean isChallengeResultScoreSame(Challenge challenge) throws UnexceptedChallengeException;

    boolean isChallengeTooLongWithoutScore(Challenge challenge) throws UnexceptedChallengeException;
}
