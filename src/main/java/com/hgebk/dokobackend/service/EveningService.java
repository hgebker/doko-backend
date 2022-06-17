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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
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
            evenings = eveningRepository.findBySemester(semester.get());
        } else {
            evenings = (List<Evening>) eveningRepository.findAll();
        }

        return evenings;
    }

    public Evening getEvening(String date) {
        return eveningRepository.findById(date)
                                .orElseThrow(() -> new NoSuchElementException(String.format(
                "No evening with date \"%s\" found",
                date
        )));
    }

    public void saveEvening(Evening newEvening) {
        Optional<Evening> eveningWithSameDate = eveningRepository.findById(
                newEvening.getDate());

        if (eveningWithSameDate.isPresent()) {
            throw new DuplicateEveningException(String.format(
                    "Evening with date %s already exists",
                    newEvening.getDate()
            ));
        }

        eveningRepository.save(newEvening);
    }

    public void updateEvening(Evening updatedEvening) {
        Optional<Evening> eveningWithDate = eveningRepository.findById(
                updatedEvening.getDate());

        if (eveningWithDate.isPresent() == false) {
            throw new NoSuchElementException(String.format(
                    "No evening with date \"%s\" found to update",
                    updatedEvening.getDate()
            ));
        }

        eveningRepository.save(updatedEvening);
    }

    public void deleteEveningWithId(String id) {
        eveningRepository.findById(id).ifPresent(eveningRepository::delete);
    }

    public Double getTotalIncomeFromEvenings(Optional<String> semesterKey) {
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
