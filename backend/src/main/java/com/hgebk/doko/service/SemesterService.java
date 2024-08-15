package com.hgebk.doko.service;

import com.hgebk.doko.entity.Semester;
import com.hgebk.doko.exception.SemesterNotFoundException;
import com.hgebk.doko.repository.SemesterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SemesterService {
    private final SemesterRepository semesterRepository;

    @Autowired
    public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    public List<Semester> getAllSemesters() {
        log.info("DBACK: Find all semesters");
        return (List<Semester>) semesterRepository.findAll();
    }

    public Semester getSemester(String key) {
        log.info("DBACK: Find semester for key {}", key);
        return semesterRepository.findById(key).orElseThrow(() -> new SemesterNotFoundException(key));
    }
}
