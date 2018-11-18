package cz.upce.diplomovaprace.repository;

import cz.upce.diplomovaprace.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
    List<Game> findByApproved(int approved);
}
