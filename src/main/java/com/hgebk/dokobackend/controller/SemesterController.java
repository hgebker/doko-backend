package com.hgebk.dokobackend.controller;

import com.hgebk.dokobackend.model.Expense;
import com.hgebk.dokobackend.model.Semester;
import com.hgebk.dokobackend.modelassembler.SemesterModelAssembler;
import com.hgebk.dokobackend.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/semester")
public class SemesterController {
    private final SemesterService semesterService;
    private final SemesterModelAssembler semesterModelAssembler;

    @Autowired
    public SemesterController(SemesterService semesterService,
                              SemesterModelAssembler semesterModelAssembler
    ) {
        this.semesterService = semesterService;
        this.semesterModelAssembler = semesterModelAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Semester>> getAllSemesters() {
        List<EntityModel<Semester>> semester = semesterService.getAllSemesters()
                                                              .stream()
                                                              .map(semesterModelAssembler::toModel)
                                                              .collect(Collectors.toList());

        CollectionModel<EntityModel<Semester>> model = CollectionModel.of(semester);
        model.add(linkTo(methodOn(SemesterController.class).getAllSemesters()).withSelfRel());
        return model;
    }

    @GetMapping("/{key}")
    public EntityModel<Semester> getSemester(@PathVariable String key) {
        Semester semester = semesterService.getSemester(key);
        return semesterModelAssembler.toModel(semester);
    }
}
