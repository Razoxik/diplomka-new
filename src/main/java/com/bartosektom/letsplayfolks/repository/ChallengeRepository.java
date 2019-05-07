package com.bartosektom.letsplayfolks.repository;

import com.bartosektom.letsplayfolks.entity.Challenge;
import com.bartosektom.letsplayfolks.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends CrudRepository<Challenge, Integer> {
    List<Challenge> findByGameByGameId(Game game);
    /// Another repository methods
}

//List<Challenge> findChallengesByUserByChallengerUserId(int id);

//TODO opravit preklep v opoNNent      oponnentUserId;
// List<Challenge> findChallengesByOponnentUserId(int id);
