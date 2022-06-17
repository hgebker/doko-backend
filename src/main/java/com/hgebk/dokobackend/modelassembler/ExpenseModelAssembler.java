package com.hgebk.dokobackend.modelassembler;

import com.hgebk.dokobackend.controller.ExpenseController;
import com.hgebk.dokobackend.entity.Expense;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ExpenseModelAssembler implements RepresentationModelAssembler<Expense, EntityModel<Expense>> {
    @Override
    public EntityModel<Expense> toModel(Expense expense) {
        return EntityModel.of(expense)
                          .add(linkTo(methodOn(ExpenseController.class).getExpense(expense.getDescription())).withSelfRel());
    }
}
