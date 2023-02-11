package com.hgebk.dokobackend.service;

import com.hgebk.dokobackend.entity.Earning;
import com.hgebk.dokobackend.exception.DuplicateEarningException;
import com.hgebk.dokobackend.exception.EarningNotFoundException;
import com.hgebk.dokobackend.repository.EarningRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EarningService {
    private final EarningRepository earningRepository;

    @Autowired
    public EarningService(EarningRepository earningRepository) {
        this.earningRepository = earningRepository;
    }

    public List<Earning> getAllEarnings() {
        log.info("DBACK: Find all earnings");
        List<Earning> earnings = (List<Earning>) earningRepository.findAll();
        return earnings.stream().sorted().toList();
    }

    public Earning getEarning(String description) {
        log.info("DBACK: Find earning with description {}", description);
        return earningRepository
                .findById(description)
                .orElseThrow(() -> new EarningNotFoundException(description));
    }

    public void saveEarning(Earning newEarning) {
        log.info("DBACK: Find earning with same art");
        Optional<Earning> earningWithSameArt = earningRepository.findById(
                newEarning.getDescription());

        if (earningWithSameArt.isPresent()) {
            throw new DuplicateEarningException(newEarning.getDescription());
        }

        earningRepository.save(newEarning);
    }

    public void updateEarning(Earning updatedEarning) {
        log.info("DBACK: Find earning to update");
        Optional<Earning> earningWithId = earningRepository.findById(
                updatedEarning.getDescription());

        if (earningWithId.isPresent() == false) {
            throw new EarningNotFoundException(updatedEarning.getDescription());
        }

        earningRepository.save(updatedEarning);
    }

    public void deleteEarningByDescription(String description) {
        log.info("DBACK: Find earning to delete");
        Earning earningToDelete = earningRepository
                .findById(description)
                .orElseThrow(() -> new EarningNotFoundException(description));

        earningRepository.delete(earningToDelete);
    }

    public Double getTotalEarnings() {
        log.info("DBACK: Get total from earnings");
        List<Earning> allEarnings = (List<Earning>) earningRepository.findAll();
        return allEarnings.stream().mapToDouble(Earning::getValue).sum();
    }
}
