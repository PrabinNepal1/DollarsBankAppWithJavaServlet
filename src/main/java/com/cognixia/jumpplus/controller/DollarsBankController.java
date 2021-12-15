package com.cognixia.jumpplus.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cognixia.jumpplus.utility.DataGenerationStubUtility;
import com.cognixia.jumpplus.model.Account;
import com.cognixia.jumpplus.model.Customer;
import com.cognixia.jumpplus.model.Transaction;
import com.cognixia.jumpplus.model.Transaction.Type;
import com.cognixia.jumpplus.utility.FileStorageUtility;

public class DollarsBankController {

	public List<Customer> customerList;
	public List<Account> accountList;
	public Customer currentCustomer , transferReceiver;
	
	
	
	public DollarsBankController(){
		
		customerList = new ArrayList<Customer>();
		
		
		File file = new File("resources/objectFile.txt");
		
		if(file.exists()) {
			customerList = FileStorageUtility.readFromFile(file);
		}
		else {
			
			for (int i=0; i < DataGenerationStubUtility.email.length; i++) {
				String email = DataGenerationStubUtility.email[i];
				String customerId = DataGenerationStubUtility.userId[i];
				String password = DataGenerationStubUtility.password[i];
				Double initialDeposit = DataGenerationStubUtility.initialDeposit[i];
				
				Account account = addInitialTransaction(initialDeposit, customerId);
				
				Customer customer = new Customer(email, customerId, password, account);

				customerList.add(customer);
				
			}
		}
		
	}
	
	public Account addInitialTransaction(double initialDeposit, String customerId) {
		
		Account account = new Account(initialDeposit);
		
		LocalDateTime timestamp = LocalDateTime.now();
		
		String depositDes = "Initial Deposit Amount In Account [" + customerId + "]\nBalance: "+ initialDeposit +" as on" + timestamp;
		
		Type transType =  Type.valueOf("DEPOSIT");
		
		Transaction transaction = new Transaction(depositDes, transType, timestamp);
		
		account.setTransactions(transaction);
		
		return account;
		
	}
	
	
	public boolean validUserId(String id) {
		for(int i=0; i <customerList.size(); i++) {
			if(customerList.get(i).getUserId().equals(id)) {
				return false;
			}
		}
		return true;
		
	}
	
	public boolean validPassword(String password) {
			
			boolean hasUpper = false;
			boolean hasLower = false; 
			boolean hasSpecial = false;
			
			if (password.length() < 8) {
				return false;
			}
			
			for(int i =0;i<password.length();i++) {
				if(Character.isUpperCase(password.charAt(i))){
					hasUpper=true;
				}
				if(Character.isLowerCase(password.charAt(i))){
					hasLower=true;
				}
			}
			
			Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
		    Matcher m = special.matcher(password);
		    if(m.find()) {
		    	hasSpecial = true;
		    }
		    
		    if(hasUpper && hasLower && hasSpecial) {
		    	return true;
		    }
		    return false;
		}

	public void addCustomer(Customer customer) {
		customerList.add(customer);
	}
	
	public boolean checkLoginCreds(String userId, String password) {
			
		Optional<Customer> found = customerList.stream()
					.filter(c ->(c.getUserId().equals(userId) && c.getPassword().equals(password)))
					.findAny();
		
		if(found.isPresent()) {
			currentCustomer = found.get();
			return true;
		}
		
		return false;
			

	}
	public boolean deposit(double amt) {
			
			int index = customerList.indexOf(currentCustomer);
			
			double newBalance = currentCustomer.getAccount().getBalance()+amt;
			
			customerList.get(index).getAccount().setBalance(newBalance);
			
			LocalDateTime timestamp = LocalDateTime.now();
			
			String depositDes = "Deposit Amount In Account [" + currentCustomer.getUserId() + "]\nBalance: "+ newBalance +" as on" + timestamp;
			
			Type transType =  Type.valueOf("DEPOSIT");
			
			Transaction transaction = new Transaction(depositDes, transType, timestamp);
			
			customerList.get(index).getAccount().setTransactions(transaction);
			
			currentCustomer = customerList.get(index);
			
			return true;
			
		}
	
	public double currentBalance() {
		return currentCustomer.getAccount().getBalance();
	}
	
	
	
	public boolean withdraw(double amt) {
		
		int index = customerList.indexOf(currentCustomer);
		
		if (amt > customerList.get(index).getAccount().getBalance()) {
			return false;
		}
		
		double newBalance = currentCustomer.getAccount().getBalance() - amt;
		
		customerList.get(index).getAccount().setBalance(newBalance);
		
		LocalDateTime timestamp = LocalDateTime.now();
		
		String withdrawDes = "Withdrawn From Account [" + currentCustomer.getUserId() + "]\nBalance: "+ newBalance +" as on" + timestamp;
		
		Type transType =  Type.valueOf("WITHDRAW");
		
		Transaction transaction = new Transaction(withdrawDes, transType, timestamp);
		
		customerList.get(index).getAccount().setTransactions(transaction);
		
		currentCustomer = customerList.get(index);
		
		return true;
		
	}
	
	public boolean isValidReceiver(String userId) {
		
		Optional<Customer> receiverFound = customerList.stream()
				.filter(c -> c.getUserId().equals(userId)).findAny();
		
		if (receiverFound.isPresent() && (!userId.equals(currentCustomer.getUserId()))) {
			
			transferReceiver = receiverFound.get();
			return true;
		}
		
		return false;
	}
	
	public boolean transferFund(double amt){
		
		if (amt > currentCustomer.getAccount().getBalance()){
			return false;
		}
		
		int receiverIndex = customerList.indexOf(transferReceiver);
		int senderIndex = customerList.indexOf(currentCustomer);
		
		double customerBalance = currentCustomer.getAccount().getBalance()-amt;
		double receiverBalance = transferReceiver.getAccount().getBalance()+amt;
		
		customerList.get(senderIndex).getAccount().setBalance(customerBalance);
		customerList.get(receiverIndex).getAccount().setBalance(receiverBalance);
		
		LocalDateTime timestamp = LocalDateTime.now();
		
		String customerTransferDes = "Trasnferred From Account [" + currentCustomer.getUserId() + "]\nBalance: "+ customerBalance +" as on" + timestamp;
		String receiverTransferDes = "Trasnfer Received In Account [" + transferReceiver.getUserId() + "]\nBalance: "+ receiverBalance +" as on" + timestamp;
		
		
		Type transType =  Type.valueOf("TRANSFER");
		
		Transaction transaction = new Transaction(customerTransferDes, transType, timestamp);
		customerList.get(senderIndex).getAccount().setTransactions(transaction);
		
		transaction = new Transaction(receiverTransferDes, transType, timestamp);
		customerList.get(receiverIndex).getAccount().setTransactions(transaction);
		
		currentCustomer = customerList.get(senderIndex);
		transferReceiver = customerList.get(receiverIndex);
				
		return true;
			
	}

}
