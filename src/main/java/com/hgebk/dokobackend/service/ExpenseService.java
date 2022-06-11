package com.hgebk.dokobackend.service;

import com.hgebk.dokobackend.exception.DuplicateExpenseException;
import com.hgebk.dokobackend.model.Expense;
import com.hgebk.dokobackend.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAllExpenses() {
        return (List<Expense>) expenseRepository.findAll();
    }

    public void saveExpense(Expense newExpense) {
        Optional<Expense> expenseWithSameArt = expenseRepository.findById(
                newExpense.getArt());

        if (expenseWithSameArt.isPresent()) {
            throw new DuplicateExpenseException(String.format(
                    "Expense with art %s already exists",
                    newExpense.getArt()
            ));
        }

        expenseRepository.save(newExpense);
    }

    public void updateExpense(Expense updatedExpense) {
        Optional<Expense> expenseWithId = expenseRepository.findById(updatedExpense.getArt());

        if (expenseWithId.isPresent() == false) {
            throw new NoSuchElementException(String.format("No expense with art \"%s\" found to update", updatedExpense.getArt()));
        }

        expenseRepository.save(updatedExpense);
    }

    public void deleteExpenseById(String id) {
        expenseRepository.findById(id).ifPresent(expenseRepository::delete);
    }
}
