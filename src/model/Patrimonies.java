package model;

public class Patrimonies extends Account {

	public Patrimonies(String name, int code) {
		super(name, code);
	}

	public int compareTo(Patrimonies patrimonies) {
		int comparation;
		int code1 = this.getCode();
		int code2 = patrimonies.getCode();
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
