package com.hgebk.dokobackend.service;

import com.hgebk.dokobackend.exception.DuplicateExpenseException;
import com.hgebk.dokobackend.model.Expense;
import com.hgebk.dokobackend.repository.ExpenseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAllExpenses() {
        log.info("DBACK: Find all expenses");
        return (List<Expense>) expenseRepository.findAll();
    }

    public void saveExpense(Expense newExpense) {
        log.info("DBACK: Find expense with same art");
        Optional<Expense> expenseWithSameArt = expenseRepository.findById(
                newExpense.getDescription());

        if (expenseWithSameArt.isPresent()) {
            log.error("DBACK: Duplicate expense");
            throw new DuplicateExpenseException(String.format(
                    "Expense with art %s already exists",
                    newExpense.getDescription()
            ));
        }

        expenseRepository.save(newExpense);
    }

    public void updateExpense(Expense updatedExpense) {
        log.info("DBACK: Find expense to update");
        Optional<Expense> expenseWithId = expenseRepository.findById(updatedExpense.getDescription());

        if (expenseWithId.isPresent() == false) {
            log.error("DBACK: Expense with id \"{}\" does not exist", updatedExpense.getDescription());
            throw new NoSuchElementException(String.format("No expense with art \"%s\" found to update", updatedExpense.getDescription()));
        }

        expenseRepository.save(updatedExpense);
    }

    public void deleteExpenseById(String id) {
        log.info("DBACK: Find expense to delete");
        Expense toDelete = expenseRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("No earning with art \"%s\" found to delete", id)));

        expenseRepository.delete(toDelete);
    }

    public Double getTotalExpenses() {
        log.info("DBACK: Get total from expenses");
        List<Expense> allExpenses = (List<Expense>) expenseRepository.findAll();
        return allExpenses.stream().mapToDouble(Expense::getValue).sum();
    }
}
