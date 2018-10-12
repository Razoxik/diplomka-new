package cz.upce.diplomovaprace.repository;

import cz.upce.diplomovaprace.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
    User findUserByUsername(String userName);
}
