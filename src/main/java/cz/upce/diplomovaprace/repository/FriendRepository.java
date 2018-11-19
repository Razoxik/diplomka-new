package cz.upce.diplomovaprace.repository;

import cz.upce.diplomovaprace.entity.Friend;
import cz.upce.diplomovaprace.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends CrudRepository<Friend, Integer> {
    List<Friend> findByUserByFromUserId(User user);
}
