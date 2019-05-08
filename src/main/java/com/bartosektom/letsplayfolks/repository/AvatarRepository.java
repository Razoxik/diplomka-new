package com.bartosektom.letsplayfolks.repository;

import com.bartosektom.letsplayfolks.entity.Avatar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepository extends CrudRepository<Avatar, Integer> {
}
