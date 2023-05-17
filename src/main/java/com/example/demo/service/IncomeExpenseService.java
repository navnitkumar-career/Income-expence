package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.IncomeExpenseDTO;
import com.example.demo.model.MonthlyReport;


public interface IncomeExpenseService {

	IncomeExpenseDTO add(IncomeExpenseDTO incomeExpenseAdd);

	List<IncomeExpenseDTO> getList();
	
	int balanceReport();
	
	int monthlyBalanceReport(MonthlyReport monthlyReport);
	
	void deleteRecord(int id);
	
	IncomeExpenseDTO update(IncomeExpenseDTO incomeExpenseAdd);
	
	IncomeExpenseDTO getById(long id);
}
