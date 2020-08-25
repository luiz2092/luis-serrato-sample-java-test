package com.simple.test.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Luis Serrato.
 *
 */

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class TransactionModel implements Comparable<TransactionModel> {

	private String transactionId;

	@CsvBindByName(column = "USER_ID")
	private Integer userId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, locale = "mx-MX", timezone = "Mexico/General")
	@CsvBindByName(column = "DATE")
	@CsvDate("yyyy-MM-dd")
	private Date date;

	@CsvBindByName(column = "DESCRIPTION")
	private String description;

	@CsvBindByName(column = "AMOUNT")
	private Double amount;

	private BigDecimal sum;

	/**
	 * To the response from a new transaction.
	 * 
	 * @param transactionId
	 */
	public TransactionModel(String transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public int compareTo(TransactionModel transaction) {
		return getDate().compareTo(transaction.getDate());
	}
}
