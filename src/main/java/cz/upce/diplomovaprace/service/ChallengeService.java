package cz.upce.diplomovaprace.service;


import cz.upce.diplomovaprace.entity.Challenge;

public interface ChallengeService {

    boolean isUserAlreadyInChallenge(int challengeId) throws Exception;
    boolean isChallengeFinished(Challenge challenge);
}
