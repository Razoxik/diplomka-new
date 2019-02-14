package com.bartosektom.letsplayfolks.repository;

import com.bartosektom.letsplayfolks.entity.Message;
import com.bartosektom.letsplayfolks.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

    List<Message> findByUserByToUserId(User user);
}
