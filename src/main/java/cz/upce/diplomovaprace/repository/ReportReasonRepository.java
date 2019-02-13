package cz.upce.diplomovaprace.repository;

import cz.upce.diplomovaprace.entity.ReportReason;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportReasonRepository extends CrudRepository<ReportReason, Integer> {

}
