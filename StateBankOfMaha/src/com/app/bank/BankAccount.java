package com.app.bank;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

import com.app.bank.BankAccount;

import CustomException.InvalidInputException;

import static Validations.ValidationRules.*;

public class BankAccount {
	private int accountNumber;
	private String name;
	private AccountType accountType;
	private double balance;
	private LocalDate accountOpeningDate;
	
	public BankAccount(int accountNumber, String name, AccountType accountType, double balance,
			LocalDate accountOpeningDate) {
		super();
		this.accountNumber = accountNumber;
		this.name = name;
		this.accountType = accountType;
		this.balance = balance;
		this.accountOpeningDate = accountOpeningDate;
	}

	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", name=" + name + ", accountType=" + accountType
				+ ", balance=" + balance + ", accountOpeningDate=" + accountOpeningDate + "]";
	}

	public BankAccount(int accountNumber) {
		super();
		this.accountNumber = accountNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int d) {
		this.accountNumber = d;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public LocalDate getAccountOpeningDate() {
		return accountOpeningDate;
	}

	public void setAccountOpeningDate(LocalDate accountOpeningDate) {
		this.accountOpeningDate = accountOpeningDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, accountOpeningDate, accountType, balance, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		return accountNumber == other.accountNumber && Objects.equals(accountOpeningDate, other.accountOpeningDate)
				&& accountType == other.accountType
				&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& Objects.equals(name, other.name);
	}
	
	public static void withdraw(Map<Integer, BankAccount> bankMap , int accno , double amount) throws InvalidInputException {
		int accno1 = validateAccountNumber(bankMap, accno);
		BankAccount bankAccount =bankMap.get(accno1);
		double updateBal = bankAccount.getBalance()-amount;
		double minBal = bankAccount.getAccountType().getMinimumBalance();
		if(updateBal<minBal)
			throw new InvalidInputException("Minimum balance condition not fullfilled!!");
		bankAccount.setBalance(updateBal);
		System.out.println("Amount withdraw successfully and new balance is "+ bankAccount.getBalance());
	}
	
	
	public static void deposite(Map<Integer,BankAccount> bankMap, int accno2 , double amount) {
		BankAccount bankAccount2 =bankMap.get(accno2);
		bankAccount2.setBalance(bankAccount2.getBalance());
		System.out.println("Amount deposited  successfully and new balance is "+ bankAccount2.getBalance());
	
	}
	
	public static void Fundtranfer(Map<Integer,BankAccount> map, int sourceAcc, int destAcc, double transferAmount)
			throws InvalidInputException {

		BankAccount source = map.get(sourceAcc);
		BankAccount target = map.get(destAcc);
		double sourceMinimumBalance = source.accountType.getMinimumBalance();
		double newSourceAmount = source.getBalance() - transferAmount;
		double newDestAmount = target.getBalance() + transferAmount;

		if (newSourceAmount > sourceMinimumBalance) {
			source.setBalance(newSourceAmount);
			target.setBalance(newDestAmount);
			System.out.println(source.getName() + " Balance: " + source.getBalance());
			System.out.println(target.getName() + " Balance: " + target.getBalance());

		} else {
			throw new InvalidInputException("Minimum Balance Limit Exceeded");
		}
	}
	
	
	
}
