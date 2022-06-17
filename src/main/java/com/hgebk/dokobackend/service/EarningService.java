package com.hgebk.dokobackend.service;

import com.hgebk.dokobackend.exception.DuplicateEarningException;
import com.hgebk.dokobackend.model.Earning;
import com.hgebk.dokobackend.model.Expense;
import com.hgebk.dokobackend.repository.EarningRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
        return (List<Earning>) earningRepository.findAll();
    }

    public void saveEarning(Earning newEarning) {
        log.info("DBACK: Find earning with same art");
        Optional<Earning> earningWithSameArt = earningRepository.findById(
                newEarning.getDescription());

        if (earningWithSameArt.isPresent()) {
            log.error("DBACK: Duplicate earning");
            throw new DuplicateEarningException(String.format(
                    "Earning with art %s already exists",
                    newEarning.getDescription()
            ));
        }

        earningRepository.save(newEarning);
    }

    public void updateEarning(Earning updatedEarning) {
        log.info("DBACK: Find earning to update");
        Optional<Earning> earningWithId = earningRepository.findById(updatedEarning.getDescription());

        if (earningWithId.isPresent() == false) {
            log.error("DBACK: Earning with id \"{}\" does not exist", updatedEarning.getDescription());
            throw new NoSuchElementException(String.format("No earning with art \"%s\" found to update", updatedEarning.getDescription()));
        }

        earningRepository.save(updatedEarning);
    }

    public void deleteEarningById(String id) {
        log.info("DBACK: Find earning to delete");
        Earning earningToDelete = earningRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("No earning with art \"%s\" found to delete", id)));

        earningRepository.delete(earningToDelete);
    }

    public Double getTotalEarnings() {
        log.info("DBACK: Get total from earnings");
        List<Earning> allEarnings = (List<Earning>) earningRepository.findAll();
        return allEarnings.stream().mapToDouble(Earning::getValue).sum();
    }
}
