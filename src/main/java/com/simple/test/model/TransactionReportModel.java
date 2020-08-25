package com.simple.test.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionReportModel {

	private Integer userId;

	private Date weekStart;

	private Date weekFinish;

	private Integer quantity;

	private BigDecimal amount;

	private BigDecimal totalAmount;
}
