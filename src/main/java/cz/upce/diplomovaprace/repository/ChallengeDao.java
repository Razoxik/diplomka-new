package cz.upce.diplomovaprace.repository;

import cz.upce.diplomovaprace.entity.Challenge;
import cz.upce.diplomovaprace.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeDao extends CrudRepository<Challenge, Long> {
    List<Challenge> findChallengeByTeamByChallengerTeamId(Optional<Team> teamByChallengerTeamId);
}
