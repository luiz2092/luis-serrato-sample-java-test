package com.simple.test.model;

import java.util.ArrayList;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionHelperModel {

	private Date weekStart;

	private Date weekFinish;

	private ArrayList<TransactionModel> transactions;

	public TransactionHelperModel() {
		this.transactions = new ArrayList<>();
	}
}
