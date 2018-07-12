package com.cg.mypaymentapp.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

public class TestClass {

	static WalletService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception 
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Lakshmi", "9010859483",new Wallet(new BigDecimal(9000)));
		 Customer cust2=new Customer("Satya", "7981482754",new Wallet(new BigDecimal(6000)));
		 Customer cust3=new Customer("Manasa", "9963095705",new Wallet(new BigDecimal(7000)));
				
		 data.put("9010859483", cust1);
		 data.put("7981482754", cust2);	
		 data.put("9963095705", cust3);	
			service= new WalletServiceImpl(data);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount1() 
	{
		service.createAccount(null, "9908620704", new BigDecimal(1400));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount2() 
	{
		service.createAccount("", "9349894574", new BigDecimal(1400));
	}
	

	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount3() 
	{
		service.createAccount("sravani", "", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount4() 
	{
		service.createAccount("", "", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount5() 
	{
		service.createAccount("Lakshmi", "9010859483", new BigDecimal(9000));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount6() 
	{
		service.createAccount("Lakshmi", "9010859483", new BigDecimal(-100));
	}
	
	
	@Test
	public void testCreateAccount7() 
	{
		Customer actual=service.createAccount("cat", "9567823452", new BigDecimal(5000));
		Customer expected=new Customer("cat", "9567823452", new Wallet(new BigDecimal(5000)));
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void testCreateAccount8() 
	{
		Customer actual=service.createAccount("swathi", "6754922472", new BigDecimal(0));
		Customer expected=new Customer("Swathi", "6754922472", new Wallet(new BigDecimal(0)));
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCreateAccount9() 
	{
		Customer actual=service.createAccount("Aarthi", "8754922472", new BigDecimal(5000.75));
		Customer expected=new Customer("Aarthi", "8754922472", new Wallet(new BigDecimal(5000.75)));
		
		assertEquals(expected, actual);
	}


	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance10() 
	{
		service.showBalance(null);		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance11() 
	{
		service.showBalance("");		
	}
	
	
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance12() 
	{
		service.showBalance("9823485753");		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance13() 
	{
		service.showBalance("90108594832");		
	}
	
	
	@Test
	public void testShowBalance14() 
	{
		Customer customer=service.showBalance("9010859483");
		BigDecimal expectedResult=new BigDecimal(9000);
		BigDecimal obtainedResult=customer.getWallet().getBalance();
		
		assertEquals(expectedResult, obtainedResult);
		
	}

	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer15() 
	{
		service.fundTransfer("9948484810", "9963095705", new BigDecimal(5000));		
	}
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer16() 
	{
		service.fundTransfer("9963095705", "9963095705", new BigDecimal(5000));		
	}

	
	@Test(expected=InsufficientBalanceException.class)
	public void testFundTransfer17() 
	{
		service.fundTransfer("9010859483", "9963095705", new BigDecimal(12000));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer18() 
	{
		service.fundTransfer("9010859483", "9963095705", new BigDecimal(0));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer19() 
	{
		service.fundTransfer("9010859483", "", new BigDecimal(0));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer20() 
	{
		service.fundTransfer("", "9963095705", new BigDecimal(500));		
	}
	
	
	@Test
	public void testFundTransfer21() 
	{
		Customer customer=service.fundTransfer("9010859483", "9963095705", new BigDecimal(500));
		BigDecimal expected=customer.getWallet().getBalance();
		BigDecimal actual=new BigDecimal(8500);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFundTransfer22() 
	{
		Customer customer=service.fundTransfer("9010859483", "9963095705", new BigDecimal(550.50));
		BigDecimal expected=customer.getWallet().getBalance();
		BigDecimal actual=new BigDecimal(8449.50);
		
		assertEquals(expected, actual);
	}
	
	
	@Test(expected=InsufficientBalanceException.class)
	public void testFundTransfer23() 
	{
		Customer customer=service.fundTransfer("9010859483", "9963095705", new BigDecimal(15000));	
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer24() 
	{
		service.fundTransfer("", "9963095705", new BigDecimal(-100));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer25() 
	{
		service.fundTransfer("", "", new BigDecimal(500));		
	}
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer26() 
	{
		service.fundTransfer("9963095705", null, new BigDecimal(50));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer27() 
	{
		service.fundTransfer("9963095705", "7981482754", new BigDecimal(0));		
	}

	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount28() 
	{
		service.depositAmount("", new BigDecimal(500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount29() 
	{
		service.depositAmount("9942221102", new BigDecimal(500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount30() 
	{
		service.depositAmount("9942221102", new BigDecimal(0));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount31() 
	{
		service.depositAmount("9963095705", new BigDecimal(-1000));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount32() 
	{
		service.depositAmount("9963095705", new BigDecimal(200000));
	}
	

	
	@Test(expected=InsufficientBalanceException.class)
	public void testWithdrawAmount33() 
	{
		service.withdrawAmount("9010859483", new BigDecimal(15000));	
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testWithdrawAmount34() 
	{
		service.withdrawAmount("9010859483", new BigDecimal(0));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testWithdrawAmount35() 
	{
		service.withdrawAmount("8754922472", new BigDecimal(5000));	
	}

}
