package Tester;

import static utils.BankMap.populateBankList;
import static utils.BankMap.populateBankMap;
import static Validations.ValidationRules.validateAccountNumber;
import static Validations.ValidationRules.*;
import static com.app.bank.BankAccount.*;
import static com.app.bank.BankAccount.Fundtranfer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.app.bank.AccountType;
import com.app.bank.BankAccount;

import CustomException.InvalidInputException;

public class bankingApplication {

	
	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {
			Map<Integer, BankAccount> bankMap = populateBankMap(populateBankList());
			boolean exit = false;

			while (!exit) {

				System.out.println("Enter the options to select\n" + "1. Add Account\n"
						+ "2. Display the account Details\n" + "3. Account Summary of particular AccNo\n"
						+ "4. Withdraw the amount\n" + "6. Funds Transfer\n" + "5. Deposit \n"
						+ "7. Natural Sorting based on Account Number 8.custom sorting based on balance 9.custom sorting based on date" + "0. Exit");

				try {
					System.out.println("ENter your choice: ");
					switch (sc.nextInt()) {
					case 1://Add Account
						System.out.println("Enter account details to be entered!!!Enter details: AccountNumber, Accountholder name, AccountType, Balance,AccountOpeningDate( YYYY-MM-DD )");
						int accNum = checkforDup(bankMap, sc.nextInt());
						String name = sc.next();
						AccountType act = validateAccountType(sc.next());
						double balance = validateBalance(act.getMinimumBalance(), sc.nextDouble());
						LocalDate date = LocalDate.parse(sc.next());
						bankMap.put(accNum,new BankAccount(accNum, name, act, balance, date));
						System.out.println("Account details added!!!");
						break;
						
					case 2://Display the account Details
						System.out.println("Deatils of all the accounts is as follow!!!");
						for(BankAccount ba : bankMap.values()) {
							System.out.println(ba);
						}

						break;
					case 3://Account Summary of particular AccNo
						System.out.println("ENter account number=");
						BankAccount ba = bankMap.get(sc.nextInt());
						if(ba==null)
							throw new InvalidInputException("Account number not found!!");
						System.out.println(ba);
						break;
					case 4://Withdraw the amount
						System.out.println("ENter account num and amount");
						int acc = sc.nextInt();
						double amt = sc.nextDouble();
						withdraw(bankMap, acc, amt);

						break;
					case 5://Deposit
						System.out.println("ENter account num and amount");
						int acc1 = sc.nextInt();
						double amt1 = sc.nextDouble();
						deposite(bankMap, acc1, amt1);

						break;

					case 6://Funds Transfer
						System.out.println("Enter the Sender AccNo, Destination AccNo, Amount to send");
						int sourceAccNo = validateAccountNumber(bankMap, sc.nextInt());
						int destinationAccNo = validateAccountNumber(bankMap, sc.nextInt());
						double transferAmount = sc.nextDouble();
						Fundtranfer(bankMap, sourceAccNo, destinationAccNo, transferAmount);
						break;

						
					case 7://Natural Sorting based on Account Number
							TreeMap<Integer, BankAccount> bankTree = new TreeMap<>(bankMap);
							for(BankAccount ba1 : bankTree.values())
								System.out.println(ba1);
						break;
					case 8://custom sorting based on balance
						ArrayList<BankAccount> list = new ArrayList<>(bankMap.values());
						Collections.sort(list, new Comparator<BankAccount>() {

							@Override
							public int compare(BankAccount o1, BankAccount o2) {
								
								return (int) (o1.getBalance()-o2.getBalance());
							}
						}
						
								
								);
							for(BankAccount b : list) {
								System.out.println(b);
							}
						break;
						
					case 9 : //custom sorting based on date
						ArrayList<BankAccount> list2 = new ArrayList<>(bankMap.values());
						Collections.sort(list2, new Comparator<BankAccount>() {

							@Override
							public int compare(BankAccount o1, BankAccount o2) {
								
								return o1.getAccountOpeningDate().compareTo(o2.getAccountOpeningDate());
							}
						}
						
								
								);
							for(BankAccount b : list2) {
								System.out.println(b);
							}
						
						
						break;
					case 0:
						exit = true;

						break;
					default:
						System.out.println("Invalid choic entered!!!");
						break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					sc.next();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
