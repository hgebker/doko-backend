package com.hgebk.dokobackend.service;

import com.hgebk.dokobackend.domain.EveningResults;
import com.hgebk.dokobackend.dto.EveningDTO;
import com.hgebk.dokobackend.dto.EveningResultDTO;
import com.hgebk.dokobackend.dto.SemesterResultDTO;
import com.hgebk.dokobackend.exception.DuplicateEveningException;
import com.hgebk.dokobackend.mapper.EveningMapper;
import com.hgebk.dokobackend.model.Evening;
import com.hgebk.dokobackend.model.Player;
import com.hgebk.dokobackend.repository.EveningRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EveningService {
    private final EveningRepository eveningRepository;
    private final EveningMapper eveningMapper;

    @Autowired
    public EveningService(
            EveningRepository eveningRepository, EveningMapper eveningMapper
    ) {
        this.eveningRepository = eveningRepository;
        this.eveningMapper = eveningMapper;
    }

    public List<Evening> searchEvenings(Optional<String> semester) {
        List<Evening> evenings;

        if (semester.isPresent()) {
            log.info("DBACK: Find evenings for semester {}", semester.get());
            evenings = eveningRepository.findBySemester(semester.get());
        } else {
            log.info("DBACK: Find all evenings");
            evenings = (List<Evening>) eveningRepository.findAll();
        }

        return evenings;
    }

    public Evening getEvening(String date) {
        log.info("DBACK: Find evening for date {}", date);
        return eveningRepository.findById(date)
                                .orElseThrow(() -> new NoSuchElementException(String.format("No evening with date \"%s\" found", date)));
    }

    public void saveEvening(Evening newEvening) {
        log.info("DBACK: Find evening with same date");
        Optional<Evening> eveningWithSameDate = eveningRepository.findById(
                newEvening.getDate());

        if (eveningWithSameDate.isPresent()) {
            log.error("DBACK: Evening with same date already exists");
            throw new DuplicateEveningException(String.format(
                    "Evening with date %s already exists",
                    newEvening.getDate()
            ));
        }

        eveningRepository.save(newEvening);
    }

    public void updateEvening(Evening updatedEvening) {
        log.info("DBACK: Find evening to update");
        Optional<Evening> eveningWithDate = eveningRepository.findById(
                updatedEvening.getDate());

        if (eveningWithDate.isPresent() == false) {
            log.error("DBACK: Evening with date \"{}\" does not exist", updatedEvening.getDate());
            throw new NoSuchElementException(String.format(
                    "No evening with date \"%s\" found to update",
                    updatedEvening.getDate()
            ));
        }

        eveningRepository.save(updatedEvening);
    }

    public void deleteEveningWithId(String id) {
        log.info("DBACK: Find evening to delete");
        Evening toDelete = eveningRepository.findById(id)
                                            .orElseThrow(() -> new NoSuchElementException(String.format("No evening with id \"%s\" found", id)));

        eveningRepository.delete(toDelete);
    }

    public Double getTotalIncomeFromEvenings(Optional<String> semesterKey) {
        log.info("DBACK: Get total income from evenings");
        List<Evening> allEvenings = searchEvenings(semesterKey);

        return allEvenings.stream().mapToDouble(evening -> {
            return evening.getResultJan() +
                   evening.getResultTim() +
                   evening.getResultOle() +
                   evening.getResultLouisa() +
                   evening.getResultHannes();
        }).sum();
    }

    public Map<Player, List<EveningResultDTO>> getEveningResultsByPlayer(Optional<String> semester) {
        List<Evening> allEvenings = searchEvenings(semester);
        return allEvenings.stream()
                          .map(eveningMapper::toResults)
                          .flatMap(List::stream)
                          .collect(Collectors.groupingBy(EveningResultDTO::getPlayer));
    }
}
