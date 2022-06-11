package com.hgebk.dokobackend.repository;

import com.hgebk.dokobackend.model.Earning;
import com.hgebk.dokobackend.model.Expense;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ExpenseRepository extends CrudRepository<Expense, String> {
}
