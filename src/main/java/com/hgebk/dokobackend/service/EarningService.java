package com.hgebk.dokobackend.service;

import com.hgebk.dokobackend.exception.DuplicateEarningException;
import com.hgebk.dokobackend.model.Earning;
import com.hgebk.dokobackend.repository.EarningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EarningService {
    private final EarningRepository earningRepository;

    @Autowired
    public EarningService(EarningRepository earningRepository) {
        this.earningRepository = earningRepository;
    }

    public List<Earning> getAllEarnings() {
        return (List<Earning>) earningRepository.findAll();
    }

    public void saveEarning(Earning newEarning) {
        Optional<Earning> earningWithSameArt = earningRepository.findById(
                newEarning.getArt());

        if (earningWithSameArt.isPresent()) {
            throw new DuplicateEarningException(String.format(
                    "Earning with art %s already exists",
                    newEarning.getArt()
            ));
        }

        earningRepository.save(newEarning);
    }

    public void updateEarning(Earning updatedEarning) {
        Optional<Earning> earningWithId = earningRepository.findById(updatedEarning.getArt());

        if (earningWithId.isPresent() == false) {
            throw new NoSuchElementException(String.format("No earning with art \"%s\" found to update", updatedEarning.getArt()));
        }

        earningRepository.save(updatedEarning);
    }

    public void deleteEarningById(String id) {
        earningRepository.findById(id).ifPresent(earningRepository::delete);
    }
}
