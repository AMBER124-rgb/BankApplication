package Validations;

import java.util.Map;

import com.app.bank.AccountType;
import com.app.bank.BankAccount;

import CustomException.InvalidInputException;

public class ValidationRules {


	public static AccountType validateAccountType(String accType) {
		
		return AccountType.valueOf(accType);
		
	}
	
	public static int checkforDup(Map<Integer, BankAccount> bankMap , int accno) throws InvalidInputException {
			if(bankMap.containsKey(accno)) {
				throw new InvalidInputException("Duplicate account found!!!");
			}
			else
				return accno;
			
	}
	
	public static double validateBalance(double minbalance , double balance) throws InvalidInputException {
		if(balance < minbalance)
			throw new InvalidInputException("Minimum balance must be required !!!!");
		return balance;
	}
	
	public static int validateAccountNumber(Map<Integer, BankAccount> bankMap,int accNum) throws InvalidInputException {
		if(bankMap.containsKey(accNum)) {
			return accNum;
		}
		throw new InvalidInputException("Invalid aCCOUNT numvered eneterd!!!");
	}
}
