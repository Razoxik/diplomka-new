package cz.upce.diplomovaprace.repository;

import cz.upce.diplomovaprace.entity.ResultState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultStateRepository extends CrudRepository<ResultState, Integer> {
    ResultState findResultStateByState(String state);
}
