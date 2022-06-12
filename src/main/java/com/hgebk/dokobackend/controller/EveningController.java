package com.hgebk.dokobackend.controller;

import com.hgebk.dokobackend.model.Evening;
import com.hgebk.dokobackend.service.EveningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path ="/evenings")
public class EveningController {
    private final EveningService eveningService;

    @Autowired
    public EveningController(EveningService eveningService) {
        this.eveningService = eveningService;
    }

    @GetMapping
    public List<Evening> getEvenings(@RequestParam Optional<String> semester) {
        if (semester.isPresent()) {
            return eveningService.getEveningsOfSemester(semester.get());
        } else {
            return eveningService.getAllEvenings();
        }
    }

    @GetMapping(path = "/{date}")
    public Optional<Evening> getEvening(@PathVariable String date) {
        return eveningService.getEvening(date);
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
