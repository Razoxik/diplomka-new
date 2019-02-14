package com.bartosektom.letsplayfolks.repository;

import com.bartosektom.letsplayfolks.entity.Game;
import com.bartosektom.letsplayfolks.entity.Rating;
import com.bartosektom.letsplayfolks.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Integer> {
    //Rating findByUserUserIdAndAndGameGameId(Integer userId, Integer gameId); doesnt work anymore po odendani tech IDcek z Entit, musis valit pres Entity -- USer a ne jen userId
    //  Rating findByUserByUserUserIdAndGameByGameGameId(User user, Game game);
    List<Rating> findByUserByUserId(User user);

    Rating findByUserByUserIdAndGameByGameId(User user, Game game);

    List<Rating> findByGameByGameId(Game game);

}
