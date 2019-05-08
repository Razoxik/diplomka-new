package com.bartosektom.letsplayfolks.repository;

import com.bartosektom.letsplayfolks.entity.ChallengeState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeStateRepository extends CrudRepository<ChallengeState, Integer> {

    ChallengeState findByState(String state);
}
