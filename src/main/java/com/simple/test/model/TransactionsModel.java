package com.simple.test.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_EMPTY)
public class TransactionsModel {

	private List<TransactionModel> transactions;

	private List<TransactionReportModel> transactionsReport;

	public TransactionsModel() {
		this.transactionsReport = new ArrayList<>();
	}

}
