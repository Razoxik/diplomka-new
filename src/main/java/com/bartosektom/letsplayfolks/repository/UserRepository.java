package com.bartosektom.letsplayfolks.repository;

import com.bartosektom.letsplayfolks.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUserName(String userName);
}
