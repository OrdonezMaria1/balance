package model;

public class Liabilities extends Account{
	
	public Liabilities(String name, int code) {
		super(name, code);
	}
	
	public int compareTo(Liabilities liabilities) {
		int comparation;
		int code1 = this.getCode();
		int code2 = liabilities.getCode();
		if(code1 < code2) {
			comparation = -1;
		}else if(code1 > code2) {
			comparation = 1;
		}else {
			comparation = 0;
		}
		return comparation;
	}
}
