package com.bartosektom.letsplayfolks.repository;

import com.bartosektom.letsplayfolks.entity.ResultState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultStateRepository extends CrudRepository<ResultState, Integer> {

    ResultState findByState(String state);
}
