package com.hgebk.dokobackend.service;

import com.hgebk.dokobackend.exception.DuplicateEveningException;
import com.hgebk.dokobackend.model.Evening;
import com.hgebk.dokobackend.repository.EveningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EveningService {
    private final EveningRepository eveningRepository;

    @Autowired
    public EveningService(EveningRepository eveningRepository) {
        this.eveningRepository = eveningRepository;
    }

    public List<Evening> getAllEvenings() {
        return (List<Evening>) eveningRepository.findAll();
    }

    public List<Evening> getEveningsOfSemester(String semester) {
        return eveningRepository.findBySemester(semester);
    }

    public Optional<Evening> getEvening(String date) {
        return eveningRepository.findById(date);
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
        Optional<Evening> eveningWithDate = eveningRepository.findById(updatedEvening.getDate());

        if (eveningWithDate.isPresent() == false) {
            throw new NoSuchElementException(String.format("No evening with date \"%s\" found to update", updatedEvening.getDate()));
        }

        eveningRepository.save(updatedEvening);
    }

    public void deleteEveningWithId(String id) {
        eveningRepository.findById(id).ifPresent(eveningRepository::delete);
    }
}
