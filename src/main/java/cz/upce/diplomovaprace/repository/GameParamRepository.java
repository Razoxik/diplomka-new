package cz.upce.diplomovaprace.repository;

import cz.upce.diplomovaprace.entity.Game;
import cz.upce.diplomovaprace.entity.GameParam;
import org.springframework.data.repository.CrudRepository;

public interface GameParamRepository extends CrudRepository<GameParam, Integer> {
    GameParam findByGameByGameIdAndName(Game game, String name);
}
