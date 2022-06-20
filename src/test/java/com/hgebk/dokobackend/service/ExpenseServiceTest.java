package com.hgebk.dokobackend.service;

import com.hgebk.dokobackend.BaseTest;
import com.hgebk.dokobackend.entity.Earning;
import com.hgebk.dokobackend.entity.Expense;
import com.hgebk.dokobackend.exception.DuplicateEarningException;
import com.hgebk.dokobackend.exception.DuplicateExpenseException;
import com.hgebk.dokobackend.exception.EarningNotFoundException;
import com.hgebk.dokobackend.exception.ExpenseNotFoundException;
import com.hgebk.dokobackend.repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.openMocks;

class ExpenseServiceTest extends BaseTest {
    @Mock
    private ExpenseRepository expenseRepository;

    private ExpenseService underTest;

    @BeforeEach
    public void setUp() {
        underTest = new ExpenseService(expenseRepository);
    }

    @Test
    void getAllExpenses_withResults() {
        List<Expense> expenses = List.of(
                new Expense("foo1", 3.5, "foo1"),
                new Expense("bar1", 7, "bar1")
        );
        given(expenseRepository.findAll()).willReturn(expenses);

        List<Expense> actualExpenses = underTest.getAllExpenses();

        assertThat(actualExpenses).isNotNull().containsAll(expenses);
    }

    @Test
    void getAllExpenses_withoutResults() {
        List<Expense> expenses = List.of();
        given(expenseRepository.findAll()).willReturn(expenses);

        List<Expense> actualExpenses = underTest.getAllExpenses();

        assertThat(actualExpenses).isNotNull().isEmpty();
    }

    @Test
    void getExpense_found() {
        Expense expense = new Expense("foo1", 3.5, "foo1");
        given(expenseRepository.findById("foo1")).willReturn(Optional.of(expense));

        Expense actualExpense = underTest.getExpense("foo1");

        assertThat(actualExpense).isNotNull().isEqualTo(expense);
    }

    @Test
    void getExpense_notFound() {
        given(expenseRepository.findById("foo1")).willReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> underTest.getExpense("foo1"));

        assertThat(thrown)
                .isInstanceOf(ExpenseNotFoundException.class)
                .hasMessage("Expense with description 'foo1' does not exist");
    }

    @Test
    void saveExpense_new() {
        Expense expense = new Expense("foo1", 3.5, "foo1");
        given(expenseRepository.findById("foo1")).willReturn(Optional.empty());

        assertThatCode(() -> underTest.saveExpense(expense)).doesNotThrowAnyException();
    }

    @Test
    void saveExpense_duplicate() {
        Expense expense = new Expense("foo1", 3.5, "foo1");
        given(expenseRepository.findById("foo1")).willReturn(Optional.of(expense));

        Throwable thrown = catchThrowable(() -> underTest.saveExpense(expense));

        assertThat(thrown)
                .isInstanceOf(DuplicateExpenseException.class)
                .hasMessage("Expense with description foo1 already exists");
    }

    @Test
    void updateExpense_found() {
        Expense expense = new Expense("foo1", 3.5, "foo1");
        given(expenseRepository.findById("foo1")).willReturn(Optional.of(expense));

        assertThatCode(() -> underTest.updateExpense(expense)).doesNotThrowAnyException();
    }

    @Test
    void updateExpense_notFound() {
        Expense expense = new Expense("foo1", 3.5, "foo1");
        given(expenseRepository.findById("foo1")).willReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> underTest.updateExpense(expense));

        assertThat(thrown)
                .isInstanceOf(ExpenseNotFoundException.class)
                .hasMessage("Expense with description 'foo1' does not exist");
    }

    @Test
    void deleteExpenseByDescription_found() {
        Expense expense = new Expense("foo1", 3.5, "foo1");
        given(expenseRepository.findById("foo1")).willReturn(Optional.of(expense));

        assertThatCode(() -> underTest.deleteExpenseByDescription("foo1")).doesNotThrowAnyException();
    }

    @Test
    void deleteExpenseByDescription_notFound() {
        given(expenseRepository.findById("foo1")).willReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> underTest.deleteExpenseByDescription("foo1"));

        assertThat(thrown)
                .isInstanceOf(ExpenseNotFoundException.class)
                .hasMessage("Expense with description 'foo1' does not exist");
    }

    @Test
    void getTotalExpenses_twoResults() {
        List<Expense> expenses = List.of(
                new Expense("foo1", 3.5, "foo1"),
                new Expense("bar1", 7, "bar1")
        );
        given(expenseRepository.findAll()).willReturn(expenses);

        double actualTotalExpenses = underTest.getTotalExpenses();

        assertThat(actualTotalExpenses)
                .isNotNull()
                .isNotNegative()
                .isEqualTo(10.5);
    }

    @Test
    void getTotalExpenses_noResults() {
        List<Expense> expenses = List.of();
        given(expenseRepository.findAll()).willReturn(expenses);

        double actualTotalExpenses = underTest.getTotalExpenses();

        assertThat(actualTotalExpenses)
                .isNotNull()
                .isZero();
    }
}