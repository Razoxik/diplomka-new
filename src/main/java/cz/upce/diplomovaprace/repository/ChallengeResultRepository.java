package cz.upce.diplomovaprace.repository;

import cz.upce.diplomovaprace.entity.Challenge;
import cz.upce.diplomovaprace.entity.ChallengeResult;
import cz.upce.diplomovaprace.entity.ResultState;
import cz.upce.diplomovaprace.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChallengeResultRepository extends CrudRepository<ChallengeResult, Integer> {
    List<ChallengeResult> findChallengeResultsByChallengeByChallengeId(Challenge challenge);

    List<ChallengeResult> findChallengeResultsByUserByUserIdAndResultStateByResultStateId(User user, ResultState resultState);

    ChallengeResult findChallengeResultByUserByUserIdAndChallengeByChallengeId(User user, Challenge challenge);
}
