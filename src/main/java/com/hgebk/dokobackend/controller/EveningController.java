package com.hgebk.dokobackend.controller;

import com.hgebk.dokobackend.dto.EveningDTO;
import com.hgebk.dokobackend.entity.Evening;
import com.hgebk.dokobackend.modelassembler.EveningModelAssembler;
import com.hgebk.dokobackend.service.EveningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path ="/evenings")
public class EveningController {
    private final EveningService eveningService;
    private final EveningModelAssembler eveningModelAssembler;


    @Autowired
    public EveningController(EveningService eveningService, EveningModelAssembler eveningModelAssembler) {
        this.eveningService = eveningService;
        this.eveningModelAssembler = eveningModelAssembler;
    }

    @GetMapping
    public CollectionModel<EveningDTO> getEvenings(@RequestParam Optional<String> semester) {
        List<EveningDTO> evenings = eveningService.searchEvenings(semester)
                                                  .stream()
                                                  .map(eveningModelAssembler::toModel)
                                                  .collect(Collectors.toList());

        return CollectionModel.of(evenings)
                              .add(linkTo(methodOn(EveningController.class).getEvenings(semester)).withSelfRel());
    }

    @GetMapping(path = "/{date}")
    public EveningDTO getEvening(@PathVariable String date) {
        Evening evening = eveningService.getEvening(date);
        return eveningModelAssembler.toModel(evening);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveEvening(@RequestBody Evening newEvening) {
        eveningService.saveEvening(newEvening);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEvening(@RequestBody Evening updatedEvening) {
        eveningService.updateEvening(updatedEvening);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEveningWithId(@PathVariable String id) {
        eveningService.deleteEveningByDate(id);
    }
}
