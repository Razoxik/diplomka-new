package com.bartosektom.letsplayfolks.repository;

import com.bartosektom.letsplayfolks.entity.Game;
import com.bartosektom.letsplayfolks.entity.Rating;
import com.bartosektom.letsplayfolks.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Integer> {

    List<Rating> findByUserByUserId(User user);

    Rating findByUserByUserIdAndGameByGameId(User user, Game game);
}
