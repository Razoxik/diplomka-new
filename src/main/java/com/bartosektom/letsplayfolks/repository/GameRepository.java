package com.bartosektom.letsplayfolks.repository;

import com.bartosektom.letsplayfolks.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
    List<Game> findByApproved(int approved);
}
