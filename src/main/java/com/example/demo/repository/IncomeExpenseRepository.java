package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.IncomeExpense;

@Repository
public interface IncomeExpenseRepository extends JpaRepository<IncomeExpense, Integer> {

	@Query(value = "select sum(income_expense.amount) from account.income_expense where type=?1", nativeQuery = true)
	public int findByType(String typeName);
	
	@Query(value = "SELECT * from account.income_expense WHERE DATE_FORMAT(trans_date, \"%m\") = ?1 AND DATE_FORMAT(trans_date, \"%Y\") = ?2", nativeQuery = true)
	public List<IncomeExpense> getMonthlyReport(int i, int j);
}
