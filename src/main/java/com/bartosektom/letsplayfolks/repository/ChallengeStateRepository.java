package com.bartosektom.letsplayfolks.repository;

import com.bartosektom.letsplayfolks.entity.ChallengeState;
import org.springframework.data.repository.CrudRepository;

public interface ChallengeStateRepository extends CrudRepository<ChallengeState, Integer> {
    ChallengeState findByState(String state);
}
