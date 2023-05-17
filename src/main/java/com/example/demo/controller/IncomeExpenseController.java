package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.IncomeExpenseDTO;
import com.example.demo.model.IncomeExpense;
import com.example.demo.model.MonthlyReport;
import com.example.demo.repository.IncomeExpenseRepository;
import com.example.demo.service.IncomeExpenseService;

@RestController
@CrossOrigin(origins = "http://localhost:56124")
public class IncomeExpenseController {

	@Autowired
	IncomeExpenseService incomeExpenseService;

	@Autowired
	IncomeExpenseRepository expenseRepository;

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = { "text/plain;charset=UTF-8",
			MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody IncomeExpenseDTO incomeExpenseAdd) {
		incomeExpenseService.add(incomeExpenseAdd);
		return ResponseEntity.ok("String");
	}

	@GetMapping(value = "/list", produces = "application/json")
	public List<IncomeExpenseDTO> IncomeExpenseList() {
		return incomeExpenseService.getList();
	}

	@GetMapping(value = "/getbalancesheet", produces = "application/json")
	public int balanceReport() {
		return incomeExpenseService.balanceReport();
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST, consumes = { "text/plain;charset=UTF-8",
			MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<IncomeExpense> getMonthlyRecord(@RequestBody MonthlyReport monthlyReport) {
		return expenseRepository.getMonthlyReport(Integer.parseInt(monthlyReport.getMonth().substring(5, 7)),
				Integer.parseInt(monthlyReport.getMonth().substring(0, 4)));
	}

	@RequestMapping(value = "/getmonthlybalance", method = RequestMethod.POST, consumes = { "text/plain;charset=UTF-8",
			MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public int monthlyBalanceReport(@RequestBody MonthlyReport monthlyReport) {
		return incomeExpenseService.monthlyBalanceReport(monthlyReport);
	}

	@GetMapping(value = "/deleteById/{id}")
	public List<IncomeExpenseDTO> deleteRecord(@PathVariable("id") int id) {	
		incomeExpenseService.deleteRecord(id);
		return incomeExpenseService.getList();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = { "text/plain;charset=UTF-8",
			MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public IncomeExpenseDTO updateRecord(@RequestBody IncomeExpenseDTO updateRecord) {
		return incomeExpenseService.update(updateRecord);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public IncomeExpenseDTO getById(@PathVariable("id") long id) {
		return incomeExpenseService.getById(id);
	}
}









