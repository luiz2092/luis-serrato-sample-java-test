package com.simple.test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.simple.test.exception.TransactionServiceException;
import com.simple.test.model.TransactionHelperModel;
import com.simple.test.model.TransactionModel;

/**
 * 
 * @author Luis Serrato.
 *
 */

public class Utils {

	public static String generateUuid() {
		return UUID.randomUUID().toString();
	}

	public static String formatDate(Date newDate) {
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleFormat.format(newDate);
	}

	public static TransactionModel getTransactionFromFile(File userFile) throws IOException {
		Reader reader = new InputStreamReader(new FileInputStream(userFile.getAbsoluteFile()));

		CsvToBean<TransactionModel> csvBean = new CsvToBeanBuilder<TransactionModel>(reader)
				.withType(TransactionModel.class).withIgnoreLeadingWhiteSpace(true).build();

		return csvBean.parse().get(0);
	}

	public static void fileExistsRule(File file) {
		if (!file.exists()) {
			throw new TransactionServiceException("User/Transaction Not Found");
		}
	}

	public static void listNonEmptyRule(List<?> list) {
		if (list.isEmpty()) {
			throw new TransactionServiceException("No users/transactions Found");
		}
	}

	public static List<TransactionModel> getTransactionsFromFolder(File userFolder) throws IOException {
		List<TransactionModel> transactions = new ArrayList<>();

		for (int idx = 0; idx < userFolder.listFiles().length; idx++) {
			File transactionFile = userFolder.listFiles()[idx];
			TransactionModel transaction = getTransactionFromFile(transactionFile);
			transaction.setTransactionId(transactionFile.getName().replace(Constants.CSV_EXTENSION, ""));
			transactions.add(transaction);
		}

		Collections.sort(transactions);
		return transactions;
	}

	public static void transactionByDateRange(TransactionModel transaction,
			Map<String, TransactionHelperModel> transactionsByDates) {

		TransactionHelperModel helper = new TransactionHelperModel();

		Calendar range = Calendar.getInstance();
		range.setTime(transaction.getDate());

		Calendar dateOrigin = Calendar.getInstance();
		dateOrigin.setTime(transaction.getDate());

		int orginalMonth = range.get(Calendar.MONTH);
		String key = formatDate(range.getTime());
		helper.setWeekStart(range.getTime());

		if (range.getActualMinimum(Calendar.DAY_OF_MONTH) != range.get(Calendar.DAY_OF_MONTH)) {
			range.setFirstDayOfWeek(Calendar.FRIDAY);
			range.set(Calendar.DAY_OF_WEEK, range.getFirstDayOfWeek());

			if (orginalMonth != range.get(Calendar.MONTH)) {
				range.set(Calendar.DAY_OF_WEEK, dateOrigin.getActualMinimum(Calendar.DAY_OF_MONTH));
			}

			key = formatDate(range.getTime());
			helper.setWeekStart(range.getTime());

		} else {
			range.add(Calendar.WEEK_OF_YEAR, 1);
		}

		int actualMaximo = range.getActualMaximum(Calendar.DAY_OF_MONTH);

		range.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		int newMaximo = range.getActualMaximum(Calendar.DAY_OF_MONTH);

		if (actualMaximo != newMaximo) {
			range.set(Calendar.DAY_OF_MONTH, actualMaximo);
		}

		helper.setWeekFinish(range.getTime());

		if (transactionsByDates.containsKey(key)) {
			transactionsByDates.get(key).getTransactions().add(transaction);
		} else {
			helper.getTransactions().add(transaction);
			transactionsByDates.put(key, helper);
		}
	}

}
