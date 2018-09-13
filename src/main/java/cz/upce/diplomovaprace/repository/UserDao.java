package cz.upce.diplomovaprace.repository;

import cz.upce.diplomovaprace.entity.Challenge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<Challenge, Long> {
}
