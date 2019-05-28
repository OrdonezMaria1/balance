package model;

public interface BalanceAccount {

	public final static String ZERO_BALANCE = "Zero Balance";
	public final static String DEBIT_BALANCE = "Debit Balance";
	public final static String CREDIT_BALANCE = "Credit Balance";
	
	public static String calculateBalance(double debit, double credit) {
		String balance;
		if(debit > credit) {
			balance = DEBIT_BALANCE;
		}else if(credit > debit) {
			balance = CREDIT_BALANCE;
		}else {
			balance = ZERO_BALANCE;
		}
		return balance;
	}
	
	public static double calculateBalanceAccount(double debit, double credit) {
		double balance = 0;
		if(calculateBalance(debit,credit) == DEBIT_BALANCE) {
			balance = debit-credit;
		}else if(calculateBalance(debit,credit) == CREDIT_BALANCE) {
			balance = credit-debit;
		}else {
			balance = 0;
		}
		return balance;
	}
}
