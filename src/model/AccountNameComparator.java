package model;

import java.util.Comparator;

public class AccountNameComparator implements Comparator<Account>{
	
	@Override
	public int compare(Account account1, Account account2) {
		int comparation;
		String name1 = account1.getName();
		String name2 = account2.getName();
		
		if(name1.compareTo(name2)<0) {
			comparation = -1;
		}else if(name1.compareTo(name2)>0) {
			comparation = 1;
		}else {
			comparation = 0;
		}
			
		return comparation;
	}
}
