package com.simple.test.service;

import java.io.IOException;

import com.simple.test.model.TransactionModel;
import com.simple.test.model.TransactionsModel;

public interface ITransactionService {

	TransactionModel addTransaction(TransactionModel transaction, Integer userId) throws IOException;

	TransactionModel showTransaction(Integer userId, String transactionId) throws IOException;

	TransactionsModel listTransactions(Integer userId) throws IOException;

	TransactionModel sumTransaction(Integer userId) throws IOException;

	TransactionsModel reportTransactions(Integer userId) throws IOException;
	
	TransactionModel showRandomTransaction() throws IOException;

}
