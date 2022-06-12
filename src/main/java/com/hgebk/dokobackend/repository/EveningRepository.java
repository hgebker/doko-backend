package com.hgebk.dokobackend.repository;

import com.hgebk.dokobackend.model.Earning;
import com.hgebk.dokobackend.model.Evening;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface EveningRepository extends CrudRepository<Evening, String> {
}
