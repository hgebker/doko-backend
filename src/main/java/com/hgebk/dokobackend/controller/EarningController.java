package com.hgebk.dokobackend.controller;

import com.hgebk.dokobackend.model.Earning;
import com.hgebk.dokobackend.modelassembler.EarningModelAssembler;
import com.hgebk.dokobackend.service.EarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping(path ="/earnings")
public class EarningController {
    private final EarningService earningService;
    private final EarningModelAssembler earningModelAssembler;

    @Autowired
    public EarningController(EarningService earningService,
                             EarningModelAssembler earningModelAssembler
    ) {
        this.earningService = earningService;
        this.earningModelAssembler = earningModelAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Earning>> getAllEarnings() {
        List<EntityModel<Earning>> earnings = earningService.getAllEarnings()
                                                            .stream()
                                                            .map(earningModelAssembler::toModel)
                                                            .collect(Collectors.toList());

        return CollectionModel.of(earnings, linkTo(methodOn(EarningController.class).getAllEarnings()).withSelfRel());
    }

    @GetMapping("/{description}")
    public EntityModel<Earning> getEarning(@PathVariable String description) {
        Earning earning = earningService.getEarning(description);
        return earningModelAssembler.toModel(earning);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveEarning(@RequestBody Earning newEarning) {
        earningService.saveEarning(newEarning);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEarning(@RequestBody Earning updatedEarning) {
        earningService.updateEarning(updatedEarning);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEarningById(@PathVariable String id) {
        earningService.deleteEarningByDescription(id);
    }
}
