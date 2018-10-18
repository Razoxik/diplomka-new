package cz.upce.diplomovaprace.repository;

import cz.upce.diplomovaprace.entity.Game;
import cz.upce.diplomovaprace.entity.Rating;
import cz.upce.diplomovaprace.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Integer> {
    //Rating findByUserUserIdAndAndGameGameId(Integer userId, Integer gameId); doesnt work anymore po odendani tech IDcek z Entit, musis valit pres Entity -- USer a ne jen userId
     Rating findByUserByUserUserIdAndGameByGameGameId(User user, Game game);
}
