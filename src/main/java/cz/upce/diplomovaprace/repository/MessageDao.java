package cz.upce.diplomovaprace.repository;

import cz.upce.diplomovaprace.entity.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao extends CrudRepository<Message, Integer> {
}
