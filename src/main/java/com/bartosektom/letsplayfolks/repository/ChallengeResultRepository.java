package com.bartosektom.letsplayfolks.repository;

import com.bartosektom.letsplayfolks.entity.Challenge;
import com.bartosektom.letsplayfolks.entity.ChallengeResult;
import com.bartosektom.letsplayfolks.entity.ResultState;
import com.bartosektom.letsplayfolks.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeResultRepository extends CrudRepository<ChallengeResult, Integer> {

    List<ChallengeResult> findByChallengeByChallengeId(Challenge challenge);

    List<ChallengeResult> findByUserByUserIdAndResultStateByResultStateId(User user, ResultState resultState);

    List<ChallengeResult> findByChallengeByChallengeIdAndTeamNumber(Challenge challenge, int teamNumber);

    ChallengeResult findByUserByUserIdAndChallengeByChallengeId(User user, Challenge challenge);

    List<ChallengeResult> findByUserByUserId(User user);
}
