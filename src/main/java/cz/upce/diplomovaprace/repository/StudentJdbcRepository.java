package cz.upce.diplomovaprace.repository;

import cz.upce.diplomovaprace.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentJdbcRepository extends CrudRepository<Student, Long>  {

    @Query("SELECT U.name FROM Student U WHERE LOWER(U.name) LIKE LOWER(concat(?1, '%'))")
    List<Student> findByName(String matchPhrase);
}