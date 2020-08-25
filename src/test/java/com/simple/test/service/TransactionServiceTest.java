package com.simple.test.service;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TransactionServiceTest {

	@InjectMocks
	private TransactionService transactionService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * iF transaction folder doesnt exist, test will return fail.
	 * 
	 * @throws IOException
	 */

	@Test
	public void showRandomTransactionTest() throws IOException {
		transactionService.showRandomTransaction();
	}

}
