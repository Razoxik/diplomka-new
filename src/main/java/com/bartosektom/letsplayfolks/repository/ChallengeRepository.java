package com.bartosektom.letsplayfolks.repository;

import com.bartosektom.letsplayfolks.entity.Challenge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends CrudRepository<Challenge, Integer> {
}
