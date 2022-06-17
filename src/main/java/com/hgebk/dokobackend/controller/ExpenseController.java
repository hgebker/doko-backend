package com.hgebk.dokobackend.controller;

import com.hgebk.dokobackend.model.Expense;
import com.hgebk.dokobackend.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExpense(@RequestBody Expense newExpense) {
        expenseService.saveExpense(newExpense);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateExpense(@RequestBody Expense updatedExpense) {
        expenseService.updateExpense(updatedExpense);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExpenseById(@PathVariable String id) {
        expenseService.deleteExpenseById(id);
    }
}
