package com.hgebk.dokobackend.controller;

import com.hgebk.dokobackend.dto.EveningDTO;
import com.hgebk.dokobackend.mapper.EveningMapper;
import com.hgebk.dokobackend.model.Evening;
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
    private final EveningMapper eveningMapper;
    private final EveningModelAssembler eveningModelAssembler;


    @Autowired
    public EveningController(EveningService eveningService,
                             EveningMapper eveningMapper,
                             EveningModelAssembler eveningModelAssembler
    ) {
        this.eveningService = eveningService;
        this.eveningMapper = eveningMapper;
        this.eveningModelAssembler = eveningModelAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<EveningDTO>> getEvenings(@RequestParam Optional<String> semester) {
        List<EntityModel<EveningDTO>> evenings = eveningService.searchEvenings(semester)
                                                  .stream()
                                                  .map(eveningMapper::toDTO)
                                                  .map(eveningModelAssembler::toModel)
                                                  .collect(Collectors.toList());

        return CollectionModel.of(evenings)
                              .add(linkTo(methodOn(EveningController.class).getEvenings(semester)).withSelfRel());
    }

    @GetMapping(path = "/{date}")
    public EntityModel<EveningDTO> getEvening(@PathVariable String date) {
        Evening evening = eveningService.getEvening(date);
        EveningDTO dto = eveningMapper.toDTO(evening);
        return eveningModelAssembler.toModel(dto);
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
