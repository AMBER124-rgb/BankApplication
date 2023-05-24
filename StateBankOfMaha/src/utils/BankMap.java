package utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.bank.AccountType;
import com.app.bank.BankAccount;

public class BankMap {

	public static List<BankAccount> populateBankList (){
		
		List<BankAccount> bankList = new ArrayList<>();
		bankList.add(new BankAccount(1234, "Cust1", AccountType.SAVING, 20000 ,LocalDate.parse("2021-03-08")));
		bankList.add(new BankAccount(1269, "Cust2", AccountType.CURRENT, 200000 ,LocalDate.parse("2021-09-28")));
		bankList.add(new BankAccount(9574, "Cust3", AccountType.LOAN, 250000 ,LocalDate.parse("2018-11-18")));
		bankList.add(new BankAccount(5241, "Cust4", AccountType.SAVING, 40000 ,LocalDate.parse("2020-02-22")));
		bankList.add(new BankAccount(8475, "Cust5", AccountType.FIXED, 600000 ,LocalDate.parse("2010-10-07")));
		bankList.add(new BankAccount(2345, "Cust6", AccountType.LOAN, 20000 ,LocalDate.parse("2023-04-08")));
		bankList.add(new BankAccount(6345, "Cust7", AccountType.CURRENT, 600000 ,LocalDate.parse("2015-12-13")));
		
		return bankList;
	}
	
	public static Map<Integer, BankAccount> populateBankMap (List<BankAccount> bankList){
		Map<Integer, BankAccount> bankMap = new HashMap<>();
		for(BankAccount ba : bankList) {
			bankMap.put(ba.getAccountNumber(), ba);
		}
		return bankMap;
		
	}


}
