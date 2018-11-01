package cz.upce.diplomovaprace.repository;

import cz.upce.diplomovaprace.entity.Challenge;
import cz.upce.diplomovaprace.entity.User;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> findAllChallengeUsersByChallenge(Challenge challenge) ;
}
