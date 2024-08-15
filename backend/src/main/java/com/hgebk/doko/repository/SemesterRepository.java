package com.hgebk.doko.repository;

import com.hgebk.doko.entity.Semester;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface SemesterRepository extends CrudRepository<Semester, String> {}
