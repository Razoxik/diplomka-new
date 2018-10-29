package cz.upce.diplomovaprace.service.impl;

import cz.upce.diplomovaprace.entity.Challenge;
import cz.upce.diplomovaprace.entity.User;
import cz.upce.diplomovaprace.enums.ChallengeStateConstants;
import cz.upce.diplomovaprace.manager.SessionManager;
import cz.upce.diplomovaprace.repository.UserRepository;
import cz.upce.diplomovaprace.repository.UserRepositoryCustom;
import cz.upce.diplomovaprace.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRepositoryCustom userRepositoryCustom;

    @Autowired
    SessionManager sessionManager;

    @Override
    public boolean isUserAlreadyInChallenge(int challengeId) throws Exception {
        User user = userRepository.findById(sessionManager.getUserId()).orElse(null);
        return user != null && userRepositoryCustom.findAllChallengeUsersByChallengeId(challengeId).contains(user);
    }

    @Override
    public boolean isChallengeFinished(Challenge challenge) {
        return ChallengeStateConstants.FINISHED.equals(challenge.getChallengeStateByChallengeStateId().getState());
    }

}
