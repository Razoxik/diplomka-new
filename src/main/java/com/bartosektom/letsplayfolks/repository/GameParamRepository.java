package com.bartosektom.letsplayfolks.repository;

import com.bartosektom.letsplayfolks.entity.Game;
import com.bartosektom.letsplayfolks.entity.GameParam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameParamRepository extends CrudRepository<GameParam, Integer> {

    GameParam findByGameByGameIdAndName(Game game, String name);
}
