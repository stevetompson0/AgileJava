package test.java.com.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import main.java.com.model.Account;

public class AccountTest {
	private static final String ABA = "102000012";
	private static final String ACCOUNT_NUMBER = "194431518811";
	
	private Account account;
	
	@Before
	public void setUp(){
		account = new Account();
		account.setBankAba(AccountTest.ABA);
		account.setBankAccountNumber(AccountTest.ACCOUNT_NUMBER);
		account.setBankAccountType(Account.BankAccountType.CHECKING);
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
	
	@Test
	public void testTransferFromBank(){
		account.setAch(new com.jimbob.ach.JimBobAch());
	
		final BigDecimal amount = new BigDecimal("50.00");
		account.transferFromBank(amount);
		
		assertEquals(account.getBalance(), amount);
		
	}

}
