package com.example.demo.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.IncomeExpenseDTO;
import com.example.demo.model.IncomeExpense;
import com.example.demo.model.MonthlyReport;
import com.example.demo.repository.IncomeExpenseRepository;
import com.example.demo.service.IncomeExpenseService;

@Service
public class IncomeExpenseServiceimpl implements IncomeExpenseService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired(required = false)
	private IncomeExpenseRepository incomeExpenseRepository;

	@Override
	@Transactional
	public IncomeExpenseDTO add(IncomeExpenseDTO incomeExpenseAdd) {
		IncomeExpense incomeExpense = new IncomeExpense();
		modelMapper.map(incomeExpenseAdd, incomeExpense);
		incomeExpenseRepository.save(incomeExpense);
		return incomeExpenseAdd;
	}

	@Override
	@Transactional
	public List<IncomeExpenseDTO> getList() {
		List<IncomeExpense> incomeExpenseList = incomeExpenseRepository.findAll();
		List<IncomeExpenseDTO> incomeExpenseDtoList = incomeExpenseList.stream().map(t -> {
			IncomeExpenseDTO incomeExpenseDto = new IncomeExpenseDTO();
			modelMapper.map(t, incomeExpenseDto);

			return incomeExpenseDto;
		}).collect(Collectors.toList());
		return incomeExpenseDtoList;
	}

	@Override
	public int balanceReport() {
		int income = incomeExpenseRepository.findByType("income");
		int expence = incomeExpenseRepository.findByType("expense");
		return income-expence;
	}

	@Override
	public int monthlyBalanceReport(MonthlyReport monthlyReport) {
		List<IncomeExpense> incomeExpense = incomeExpenseRepository.getMonthlyReport(
				Integer.parseInt(monthlyReport.getMonth().substring(5, 7)),
				Integer.parseInt(monthlyReport.getMonth().substring(0, 4)));
		int income = 0;
		int expence = 0;
		for (IncomeExpense incomeExpense2 : incomeExpense) {

			if (incomeExpense2.getType().equals("income")) {
				income = income + incomeExpense2.getAmount();
			} else {
				expence = expence + incomeExpense2.getAmount();
			}
		}
		return income - expence;

	}

	@Override
	public void deleteRecord(int id) {
		incomeExpenseRepository.deleteById(id);
	}
	
	@Override
	@Transactional
	public IncomeExpenseDTO update(IncomeExpenseDTO updateRecord) {

		IncomeExpense incomeExpense =incomeExpenseRepository.findById(updateRecord.getId()).get() ;

		modelMapper.map(updateRecord, incomeExpense);
		incomeExpenseRepository.save(incomeExpense);

		modelMapper.map(incomeExpense, updateRecord);
		return updateRecord;
	}

	@Override
	public IncomeExpenseDTO getById(long id) {
		IncomeExpense incomeExpense = incomeExpenseRepository.findById((int) id).get();
		IncomeExpenseDTO income=new IncomeExpenseDTO();
		modelMapper.map(incomeExpense , income);
			return income;
	}
}
