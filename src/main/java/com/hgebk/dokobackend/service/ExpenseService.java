package com.hgebk.dokobackend.service;

import com.hgebk.dokobackend.exception.DuplicateExpenseException;
import com.hgebk.dokobackend.exception.ExpenseNotFoundException;
import com.hgebk.dokobackend.entity.Expense;
import com.hgebk.dokobackend.repository.ExpenseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Expense getExpense(String description) {
        log.info("DBACK: Find expense with description {}", description);
        return expenseRepository
                .findById(description)
                .orElseThrow(() -> new ExpenseNotFoundException(description));
    }

    public void saveExpense(Expense newExpense) {
        log.info("DBACK: Find expense with same art");
        Optional<Expense> expenseWithSameArt = expenseRepository.findById(
                newExpense.getDescription());

        if (expenseWithSameArt.isPresent()) {
            throw new DuplicateExpenseException(newExpense.getDescription());
        }

        expenseRepository.save(newExpense);
    }

    public void updateExpense(Expense updatedExpense) {
        log.info("DBACK: Find expense to update");
        Optional<Expense> expenseWithId = expenseRepository.findById(updatedExpense.getDescription());

        if (expenseWithId.isPresent() == false) {
            throw new ExpenseNotFoundException(updatedExpense.getDescription());
        }

        expenseRepository.save(updatedExpense);
    }

    public void deleteExpenseByDescription(String description) {
        log.info("DBACK: Find expense to delete");
        Expense toDelete = expenseRepository
                .findById(description)
                .orElseThrow(() -> new ExpenseNotFoundException(description));

        expenseRepository.delete(toDelete);
    }

    public Double getTotalExpenses() {
        log.info("DBACK: Get total from expenses");
        List<Expense> allExpenses = (List<Expense>) expenseRepository.findAll();
        return allExpenses.stream().mapToDouble(Expense::getValue).sum();
    }
}
