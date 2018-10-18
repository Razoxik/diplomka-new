package cz.upce.diplomovaprace.repository;

import cz.upce.diplomovaprace.entity.Message;
import cz.upce.diplomovaprace.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

    List<Message> findMessagesByUserByToUserId(User user);
}
