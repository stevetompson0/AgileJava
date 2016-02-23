package test.java.com.test;

import com.jimbob.ach.*;

import static org.junit.Assert.assertTrue;

import java.util.Date;

public class MockAch implements Ach{

	@Override
	public AchResponse issueDebit(AchCredentials credentials, AchTransactionData data) {
		return null;
	}

	@Override
	public AchResponse markTransactionAsNSF(AchCredentials credentials, AchTransactionData data, String traceCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AchResponse refundTransaction(AchCredentials credentials, AchTransactionData data, String traceCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AchResponse issueCredit(AchCredentials credentials, AchTransactionData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AchResponse voidSameDayTransaction(AchCredentials credentials, AchTransactionData data, String traceCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AchResponse queryTransactionStatus(AchCredentials credentials, AchTransactionData data, String traceCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
