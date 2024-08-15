package com.hgebk.doko.repository;

import com.hgebk.doko.entity.Evening;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface EveningRepository extends CrudRepository<Evening, String> {
    List<Evening> findBySemester(String semester);
}
