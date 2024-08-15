package com.hgebk.dokobackend.controller;

import com.hgebk.dokobackend.entity.Expense;
import com.hgebk.dokobackend.modelassembler.ExpenseModelAssembler;
import com.hgebk.dokobackend.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path ="/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final ExpenseModelAssembler expenseModelAssembler;

    @Autowired
    public ExpenseController(ExpenseService expenseService,
                             ExpenseModelAssembler expenseModelAssembler
    ) {
        this.expenseService = expenseService;
        this.expenseModelAssembler = expenseModelAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Expense>> getAllExpenses() {
        List<EntityModel<Expense>> expenses = expenseService.getAllExpenses()
                                                            .stream()
                                                            .map(expenseModelAssembler::toModel)
                                                            .collect(Collectors.toList());

        return CollectionModel.of(expenses, linkTo(methodOn(ExpenseController.class).getAllExpenses()).withSelfRel());
    }

    @GetMapping("/{description}")
    public EntityModel<Expense> getExpense(@PathVariable String description) {
        Expense expense = expenseService.getExpense(description);
        return expenseModelAssembler.toModel(expense);
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
        expenseService.deleteExpenseByDescription(id);
    }
}
