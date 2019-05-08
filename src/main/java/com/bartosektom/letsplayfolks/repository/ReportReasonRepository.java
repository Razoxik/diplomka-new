package com.bartosektom.letsplayfolks.repository;

import com.bartosektom.letsplayfolks.entity.ReportReason;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportReasonRepository extends CrudRepository<ReportReason, Integer> {
}
