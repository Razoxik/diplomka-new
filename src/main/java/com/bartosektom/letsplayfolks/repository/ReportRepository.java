package com.bartosektom.letsplayfolks.repository;

import com.bartosektom.letsplayfolks.entity.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends CrudRepository<Report, Integer> {
}
