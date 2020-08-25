package com.simple.test.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;
import com.simple.test.model.TransactionHelperModel;
import com.simple.test.model.TransactionModel;
import com.simple.test.model.TransactionReportModel;
import com.simple.test.model.TransactionsModel;
import com.simple.test.utils.Constants;
import com.simple.test.utils.Utils;

/**
 * 
 * @author Luis Serrato.
 *
 */

@Service
public class TransactionService implements ITransactionService {

	@Override
	public TransactionModel addTransaction(TransactionModel transaction, Integer userId) throws IOException {
		String userDirPath = Constants.ROOT_PATH.concat(userId.toString());
		String transactionId = Utils.generateUuid();

		File userDir = new File(userDirPath);
		userDir.mkdirs();

		File newTransaction = new File(userDirPath, transactionId.concat(Constants.CSV_EXTENSION));
		newTransaction.getAbsoluteFile().createNewFile();

		FileWriter output = new FileWriter(newTransaction);
		CSVWriter writer = new CSVWriter(output, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER,
				CSVWriter.DEFAULT_LINE_END);

		writer.writeNext(Constants.CSV_HEADERS);
		writer.writeNext(new String[] { userId.toString(),
				Objects.isNull(transaction.getAmount()) ? "0" : transaction.getAmount().toString(),
				transaction.getDescription(), Utils.formatDate(transaction.getDate()) });

		writer.close();

		transaction.setTransactionId(transactionId);
		return transaction;
	}

	@Override
	public TransactionModel showTransaction(Integer userId, String transactionId) throws IOException {

		File userFile = new File(Constants.ROOT_PATH.concat(userId.toString()),
				transactionId.concat(Constants.CSV_EXTENSION));

		Utils.fileExistsRule(userFile);

		TransactionModel transaction = Utils.getTransactionFromFile(userFile);
		transaction.setTransactionId(transactionId);

		return transaction;

	}

	@Override
	public TransactionsModel listTransactions(Integer userId) throws IOException {
		File userFolder = new File(Constants.ROOT_PATH.concat(userId.toString()));
		Utils.fileExistsRule(userFolder);

		TransactionsModel transactionResponse = new TransactionsModel();
		transactionResponse.setTransactions(Utils.getTransactionsFromFolder(userFolder));

		return transactionResponse;
	}

	@Override
	public TransactionModel sumTransaction(Integer userId) throws IOException {
		File userFolder = new File(Constants.ROOT_PATH.concat(userId.toString()));
		Utils.fileExistsRule(userFolder);

		List<TransactionModel> transactions = Utils.getTransactionsFromFolder(userFolder);

		TransactionModel transactionSum = new TransactionModel();
		transactionSum.setUserId(userId);
		transactionSum.setSum(transactions.stream().map(t -> BigDecimal.valueOf(t.getAmount())).reduce(BigDecimal.ZERO,
				BigDecimal::add));

		return transactionSum;
	}

	@Override
	public TransactionsModel reportTransactions(Integer userId) throws IOException {
		File userFolder = new File(Constants.ROOT_PATH.concat(userId.toString()));
		Utils.fileExistsRule(userFolder);

		List<TransactionModel> transactions = Utils.getTransactionsFromFolder(userFolder);
		Map<String, TransactionHelperModel> transactionsByDates = new LinkedHashMap<>();

		for (TransactionModel transactionModel : transactions) {
			Utils.transactionByDateRange(transactionModel, transactionsByDates);
		}

		TransactionsModel response = new TransactionsModel();
		BigDecimal totalAmount = BigDecimal.ZERO;

		for (Map.Entry<String, TransactionHelperModel> entry : transactionsByDates.entrySet()) {
			TransactionReportModel report = new TransactionReportModel();
			List<TransactionModel> groupTransactions = entry.getValue().getTransactions();

			report.setUserId(userId);
			report.setWeekStart(entry.getValue().getWeekStart());
			report.setWeekFinish(entry.getValue().getWeekFinish());
			report.setQuantity(groupTransactions.size());
			report.setAmount(groupTransactions.stream().map(t -> BigDecimal.valueOf(t.getAmount()))
					.reduce(BigDecimal.ZERO, BigDecimal::add));
			report.setTotalAmount(totalAmount);
			totalAmount = totalAmount.add(report.getAmount());

			response.getTransactionsReport().add(report);
		}
		return response;
	}

	@Override
	public TransactionModel showRandomTransaction() throws IOException {

		File rootFolder = new File(Constants.ROOT_PATH);
		List<File> userFolders = Arrays.asList(rootFolder.listFiles());
		Utils.listNonEmptyRule(userFolders);
		Collections.shuffle(userFolders);

		List<TransactionModel> transactions = Utils.getTransactionsFromFolder(userFolders.get(0));
		Utils.listNonEmptyRule(transactions);
		Collections.shuffle(transactions);

		return transactions.get(0);
	}
}
