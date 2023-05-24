package com.app.bank;

public enum AccountType {
SAVING(2000), CURRENT(10000), FIXED(50000), LOAN(100000);
	
	private double minimumBalance;

	private AccountType(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

	public double getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}
	
	public String toString() {
		return name().toLowerCase()+" @ "+minimumBalance;
	}
}
