package com.hgebk.dokobackend.controller;

import com.hgebk.dokobackend.dto.EveningDTO;
import com.hgebk.dokobackend.mapper.EveningMapper;
import com.hgebk.dokobackend.model.Evening;
import com.hgebk.dokobackend.service.EveningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path ="/evenings")
public class EveningController {
    private final EveningService eveningService;
    private final EveningMapper eveningMapper;


    @Autowired
    public EveningController(EveningService eveningService,
                             EveningMapper eveningMapper
    ) {
        this.eveningService = eveningService;
        this.eveningMapper = eveningMapper;
    }

    @GetMapping
    public List<EveningDTO> getEvenings(@RequestParam Optional<String> semester) {
        return eveningService.searchEvenings(semester).stream().map(eveningMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping(path = "/{date}")
    public EveningDTO getEvening(@PathVariable String date) {
        Evening evening = eveningService.getEvening(date);
        return eveningMapper.toDTO(evening);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveEvening(@RequestBody Evening newEvening) {
        eveningService.saveEvening(newEvening);
    }

    @PutMapping
    public void updateEvening(@RequestBody Evening updatedEvening) {
        eveningService.updateEvening(updatedEvening);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteEveningWithId(@PathVariable String id) {
        eveningService.deleteEveningWithId(id);
    }
}
