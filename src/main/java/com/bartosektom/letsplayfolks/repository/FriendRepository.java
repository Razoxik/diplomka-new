package com.bartosektom.letsplayfolks.repository;

import com.bartosektom.letsplayfolks.entity.Friend;
import com.bartosektom.letsplayfolks.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends CrudRepository<Friend, Integer> {

    List<Friend> findByUserByFromUserId(User user);

    Friend findByUserByToUserId(User user);
}
