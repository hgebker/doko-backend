package com.hgebk.dokobackend.controller;

import com.hgebk.dokobackend.model.Earning;
import com.hgebk.dokobackend.service.EarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path ="/earnings")
public class EarningController {
    private final EarningService earningService;

    @Autowired
    public EarningController(EarningService earningService) {
        this.earningService = earningService;
    }

    @GetMapping
    public List<Earning> getAllEarnings() {
        return earningService.getAllEarnings();
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
        earningService.deleteEarningById(id);
    }
}
