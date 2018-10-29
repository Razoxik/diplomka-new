package cz.upce.diplomovaprace.repository;

import cz.upce.diplomovaprace.entity.Challenge;
import cz.upce.diplomovaprace.entity.ChallengeResult;
import cz.upce.diplomovaprace.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {


    @Autowired
    ChallengeRepository challengeRepository;
    @Autowired
    ChallengeResultRepository challengeResultRepository;

    @Override
    public List<User> findAllChallengeUsersByChallengeId(int challengeId) throws Exception {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(Exception::new);
        List<ChallengeResult> challengeResults = challengeResultRepository.findChallengeResultsByChallengeByChallengeId(challenge);

        return challengeResults.stream().map(ChallengeResult::getUserByUserId).collect(Collectors.toList());
    }
}
