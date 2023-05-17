package com.example.demo.dto;

import java.sql.Date;

public class IncomeExpenseDTO {
	
	int id;
	Date trasDate;
	String type;
	String name;
	int amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTrasDate() {
		return trasDate;
	}

	public void setTrasDate(Date trasDate) {
		this.trasDate = trasDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "IncomeExpense [id=" + id + ", trasDate=" + trasDate + ", type=" + type + ", name=" + name + ", amount="
				+ amount + "]";
	}
}
