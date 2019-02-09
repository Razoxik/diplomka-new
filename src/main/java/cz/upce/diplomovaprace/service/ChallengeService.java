package cz.upce.diplomovaprace.service;


import cz.upce.diplomovaprace.model.ChallengeDetailUserModel;
import cz.upce.diplomovaprace.entity.Challenge;
import cz.upce.diplomovaprace.entity.User;
import cz.upce.diplomovaprace.exception.UnexceptedChallengeException;
import cz.upce.diplomovaprace.model.ChallengeResultModel;
import cz.upce.diplomovaprace.model.QuestionableChallengeModel;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface ChallengeService {

    boolean isUserAlreadyInChallenge(Challenge challenge);

    boolean isChallengeFinished(Challenge challenge);

    Pair<List<User>, List<User>> prepareTeamsUsers(Challenge challenge);

    List<ChallengeDetailUserModel> prepareChallengeDetailUserModels(Challenge challenge, List<User> users);

    Pair<List<ChallengeDetailUserModel>, List<ChallengeDetailUserModel>> prepareTeamsDtos(Challenge challenge);

    List<QuestionableChallengeModel> prepareQuestionableChallengeModels() throws UnexceptedChallengeException;

    void finishChallenge(User user, Challenge challenge, ChallengeResultModel challengeResultModel) throws UnexceptedChallengeException;
}
