package com.hgebk.dokobackend.service;

import com.hgebk.dokobackend.model.Semester;
import com.hgebk.dokobackend.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterService {
    private final SemesterRepository semesterRepository;

    @Autowired
    public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    public List<Semester> getAllSemesters() {
        return (List<Semester>) semesterRepository.findAll();
    }
}
