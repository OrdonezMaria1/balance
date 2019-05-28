package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Account implements BalanceAccount, Comparable<Account>{
	
	private String name;
	private int code;
	private double debit;
	private double credit;
	private List<Double> listDebit;
	private List<Double> listCredit;
	
	public Account(String name, int code) {
		this.name = name;
		this.code = code;
		this.listDebit = new ArrayList<Double>();
		this.listCredit = new ArrayList<Double>();
	}
	
	public double getDebit() {
		return debit;
	}
	
	public double getCredit() {
		return credit;
	}
	
	public void setDebit(double debit) {
		this.debit = debit;
	}
	
	public void setCredit(double credit) {
		this.credit = credit;
	}
	
	public String getName() {
		return name;
	}
	
	public int getCode() {
		return code;
	}
	
	public List<Double> getListDebit() {
		return listDebit;
	}
	
	public List<Double> getListCredit() {
		return listCredit;
	}
	
	public double calculateDebit() {
		double balance = 0;
		for(int i = 0; i < listDebit.size(); i++) {
			balance += listDebit.get(i);
		}
		return balance;
	}
	
	public double calculateCredit() {
		double balance = 0;
		for(int i = 0; i < listCredit.size(); i++) {
			balance += listCredit.get(i);
		}
		return balance;
	}
	
	public double getTotalBalance() {
		double totalBalance = BalanceAccount.calculateBalanceAccount(calculateDebit(), calculateCredit());
		return totalBalance;
	}
	
	public String getBalance() {
		String balance = BalanceAccount.calculateBalance(calculateDebit(), calculateCredit());
		return balance;
	}
	
	public String toString(){
		String msg = " "+code+" "+name+" "+debit+" "+credit;
		return msg;
		}
	
	@Override
	public int compareTo(Account account) {
		int comparation;
		if(code < account.code) {
			comparation = -1;
		}else if(code > account.code) {
			comparation = 1;
		}else {
			comparation = 0;
		}
		return comparation;
	}
	
}
