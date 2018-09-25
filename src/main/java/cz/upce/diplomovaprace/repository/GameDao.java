package cz.upce.diplomovaprace.repository;

import cz.upce.diplomovaprace.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameDao extends CrudRepository<Game, Integer> {
}
