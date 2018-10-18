package cz.upce.diplomovaprace.repository;

import cz.upce.diplomovaprace.entity.Challenge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends CrudRepository<Challenge, Integer> {
    List<Challenge> findChallengesByUserByChallengerUserId(int id);

    //TODO opravit preklep v opoNNent      oponnentUserId;
   // List<Challenge> findChallengesByOponnentUserId(int id);

}
