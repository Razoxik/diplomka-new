package com.bartosektom.letsplayfolks.repository;

import com.bartosektom.letsplayfolks.entity.Challenge;
import com.bartosektom.letsplayfolks.entity.User;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> findAllChallengeUsersByChallenge(Challenge challenge);
}
