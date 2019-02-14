package com.bartosektom.letsplayfolks.service;


import com.bartosektom.letsplayfolks.exception.UnexceptedChallengeException;
import com.bartosektom.letsplayfolks.model.ChallengeDetailUserModel;
import com.bartosektom.letsplayfolks.model.ChallengeResultModel;
import com.bartosektom.letsplayfolks.model.QuestionableChallengeModel;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import com.bartosektom.letsplayfolks.entity.Challenge;
import com.bartosektom.letsplayfolks.entity.User;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface ChallengeService {

    boolean isUserAlreadyInChallenge(Challenge challenge);

    boolean isChallengeFinished(Challenge challenge);

    boolean canUserEnterResult(Challenge challenge);

    Pair<List<User>, List<User>> prepareTeamsUsers(Challenge challenge);

    List<ChallengeDetailUserModel> prepareChallengeDetailUserModels(Challenge challenge, List<User> users) throws EntityNotFoundException;

    Pair<List<ChallengeDetailUserModel>, List<ChallengeDetailUserModel>> prepareTeamsDtos(Challenge challenge) throws EntityNotFoundException;

    List<QuestionableChallengeModel> prepareQuestionableChallengeModels() throws UnexceptedChallengeException;

    void finishChallenge(User user, Challenge challenge, ChallengeResultModel challengeResultModel) throws UnexceptedChallengeException;
}
