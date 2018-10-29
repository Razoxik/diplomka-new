package cz.upce.diplomovaprace.repository;

import cz.upce.diplomovaprace.entity.User;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> findAllChallengeUsersByChallengeId(int challengeId) throws Exception;
}
