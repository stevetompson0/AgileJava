package test.java.com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.jimbob.ach.AchCredentials;
import com.jimbob.ach.AchResponse;
import com.jimbob.ach.AchStatus;
import com.jimbob.ach.AchTransactionData;

import main.java.com.model.Account;

public class AccountTest {
	static final String ABA = "102000012";
	static final String ACCOUNT_NUMBER = "194431518811";
	
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
		//account.setAch(new com.jimbob.ach.JimBobAch());
		account.setAch(new MockAch() {
			@Override
			public AchResponse issueDebit(AchCredentials credentials, AchTransactionData data) {
				assertTrue(data.account.equals(AccountTest.ACCOUNT_NUMBER));
				assertTrue(data.aba.equals(AccountTest.ABA));
				
				AchResponse response = new AchResponse();
				response.timestamp = new Date();
				response.traceCode = "1";
				response.status = AchStatus.SUCCESS;
				return response;
			}
		});
		
		final BigDecimal amount = new BigDecimal("50.00");
		account.transferFromBank(amount);
		
		assertEquals(account.getBalance(), amount);
		
	}

}
