package com.hgebk.dokobackend.repository;

import com.hgebk.dokobackend.entity.Earning;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface EarningRepository extends CrudRepository<Earning, String> {
}
