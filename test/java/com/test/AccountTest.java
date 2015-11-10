package test.java.com.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import main.java.com.model.Account;

public class AccountTest {
	private Account account;
	
	@Before
	public void setUp(){
		account = new Account();
	}
	
	@Test
	public void testTransaction(){
		account.credit(new BigDecimal("0.10"));
		account.credit(new BigDecimal("11.00"));
		assertEquals(new BigDecimal("11.10"), account.getBalance());
	}
	
	@Test
	public void testTransactionAverage(){
		account.credit(new BigDecimal("0.10"));
		account.credit(new BigDecimal("11.00"));
		account.credit(new BigDecimal("2.99"));
		assertEquals(new BigDecimal("4.70"), account.transactionAverage());
		
	}

}
