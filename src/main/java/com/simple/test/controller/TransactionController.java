package com.simple.test.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple.test.model.TransactionModel;
import com.simple.test.model.TransactionsModel;
import com.simple.test.service.ITransactionService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Luis Serrato.
 *
 */
@Slf4j
@RestController
@RequestMapping("/v1/transactions")
public class TransactionController {

	@Autowired
	private ITransactionService transactionService;

	@PostMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionModel> addTransaction(@PathVariable("userId") final Integer userId,
			@RequestBody TransactionModel newTransaction) throws IOException {

		log.info("Add new transaction to {}", userId);
		return new ResponseEntity<>(transactionService.addTransaction(newTransaction, userId), HttpStatus.CREATED);
	}

	@GetMapping(value = "/{userId}/{transactionId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionModel> showTransaction(@PathVariable("userId") final Integer userId,
			@PathVariable("transactionId") final String transactionId) throws IOException {

		log.info("Show transaction to {} - {}", userId, transactionId);

		return new ResponseEntity<>(transactionService.showTransaction(userId, transactionId), HttpStatus.OK);
	}

	@GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionsModel> listTransactions(@PathVariable("userId") final Integer userId)
			throws IOException {

		log.info("List transactions to {}", userId);
		return new ResponseEntity<>(transactionService.listTransactions(userId), HttpStatus.OK);
	}

	@GetMapping(value = "/sum/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionModel> sumTransactions(@PathVariable("userId") final Integer userId)
			throws IOException {

		log.info("Sum transactions to {}", userId);
		return new ResponseEntity<>(transactionService.sumTransaction(userId), HttpStatus.OK);
	}

	@GetMapping(value = "/report/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionsModel> resportTransactions(@PathVariable("userId") final Integer userId)
			throws IOException {

		log.info("Report transactions from {}", userId);
		return new ResponseEntity<>(transactionService.reportTransactions(userId), HttpStatus.OK);
	}

	@GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionModel> showRandomTransaction() throws IOException {

		log.info("Show Random transaction");

		return new ResponseEntity<>(transactionService.showRandomTransaction(), HttpStatus.OK);
	}

}
