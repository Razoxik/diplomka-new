package cz.upce.diplomovaprace.repository;

import cz.upce.diplomovaprace.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamDao extends CrudRepository<Team, Integer> {

}
