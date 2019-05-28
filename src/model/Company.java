package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {
	private String name;
	private String nit;
	private String logo;
	private List<Assets> assets;
	private List<Liabilities> liabilities;
	private List<Patrimonies> patrimonies;
	public final static String PATH_ASSETS = "data/assets.txt";
	public final static String PATH_LIABILITIES = "data/liabilities.txt";
	public final static String PATH_PATRIMONIES = "data/patrimonies.txt";
	
	public Company() {
		this.name = null;
		this.nit = null;
		this.logo = null;
		assets = new ArrayList<Assets>();
		liabilities = new ArrayList<Liabilities>();
		patrimonies = new ArrayList<Patrimonies>();
	}
	
	public String getName() {
		return name;
	}
	
	public String getNit() {
		return nit;
	}
	
	public String getLogo() {
		return logo;
	}
	
	public List<Assets> getAssets() {
		return assets;
	}
	
	public List<Liabilities> getLiabilities() {
		return liabilities;
	}
	
	public List<Patrimonies> getPatrimonies() {
		return patrimonies;
	}
	
	public void loadInformation(String path) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
		String line = bufferedReader.readLine();
		while(line != null) {
			String[] parts = line.split(";");
			name = parts[0];
			nit = parts[1];
			logo = parts[2];
			
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
	}
		
	public void loadAssets() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(PATH_ASSETS)));
		String line = bufferedReader.readLine();
		while(line != null) {
			String[] parts = line.split(";");
			int code = Integer.parseInt(parts[0]);
			String name = parts[1];
			Assets current = new Assets(name,code);
			assets.add(current);
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
	}
	
	public void loadLiabilities() throws IOException{
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(PATH_LIABILITIES)));
		String line = bufferedReader.readLine();
		while(line != null) {
			String[] parts = line.split(";");
			int code = Integer.parseInt(parts[0]);
			String name = parts[1];
			Liabilities current = new Liabilities(name,code);
			liabilities.add(current);
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
	}
	
	public void loadPatrimonies() throws IOException{
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(PATH_PATRIMONIES)));
		String line = bufferedReader.readLine();
		while(line != null) {
			String[] parts = line.split(";");
			int code = Integer.parseInt(parts[0]);
			String name = parts[1];
			Patrimonies current = new Patrimonies(name,code);
			patrimonies.add(current);
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
	}
	
	public String generateBalance() {
		String balance = "";
		return balance;
	}
	
	public void sortAssetsByCode() {
		Collections.sort(assets);
	}
	
	public void sortAssetsByName() {
		Collections.sort(assets, new  AccountNameComparator());
	}
	
	public void sortLiabilitiesByCode() {
		Collections.sort(liabilities);
	}
	
	public void sortLiabilitiesByName() {
		Collections.sort(liabilities, new AccountNameComparator());
	}
	
	public void sortPatrimoniesByCode() {
		Collections.sort(patrimonies);
	}
	
	public void sortPatrimoniesByName() {
		Collections.sort(patrimonies, new AccountNameComparator());
	}
	
	public Assets searchAssetsByCode(int code) {
		Assets searched = null;
		for(int i = 0; i< assets.size(); i++) {
			if(assets.get(i).getCode() == code) {
				searched = assets.get(i);
			}
		}
		return searched;
	}
	
	public Liabilities searchLiabilitiesByCode(int code) {
		Liabilities searched = null;
		for(int i = 0; i< liabilities.size(); i++) {
			if(liabilities.get(i).getCode() == code) {
				searched = liabilities.get(i);
			}
		}
		return searched;
	}
	
	public Patrimonies searchPatrimoniesByCode(int code) {
		Patrimonies searched = null;
		for(int i = 0; i< patrimonies.size(); i++) {
			if(patrimonies.get(i).getCode() == code) {
				searched = patrimonies.get(i);
			}
		}
		return searched;
	}
	
	public double totalAssetsBalance(){
		double totalBalance = 0;
		List<Assets> current = assetsBalance();
		for(int i = 0; i< current.size(); i++){
			totalBalance += current.get(i).getTotalBalance();
		}
		return totalBalance;
	} 
	public double totalLiabilitiesBalance(){
		double totalBalance = 0;
		List<Liabilities> current = liabilitiesBalance();
		for(int i = 0; i< current.size(); i++){
			totalBalance += current.get(i).getTotalBalance();
		}
		return totalBalance;
	} 

	public double totalPatrimoniesBalance(){
		double totalBalance = 0;
		List<Patrimonies> current = patrimoniesBalance();
		for(int i = 0; i< current.size(); i++){
			totalBalance += current.get(i).getTotalBalance();
		}
		return totalBalance;
	}
	
	public List<Assets> assetsBalance(){
		List<Assets> current = new ArrayList<Assets>();
		for(int i = 0; i<assets.size(); i++){
			if((assets.get(i).getDebit() != 0) || (assets.get(i).getCredit() != 0)){
				current.add(assets.get(i));
				}
			}
		return current;
	}
	
	public List<Liabilities> liabilitiesBalance(){
		List<Liabilities> current = new ArrayList<Liabilities>();
		for(int i = 0; i<liabilities.size(); i++){
			if((liabilities.get(i).getDebit() != 0) || (liabilities.get(i).getCredit() != 0)){
				current.add(liabilities.get(i));
			}
		}
		return current;
	}
	
	public List<Patrimonies> patrimoniesBalance(){
		List<Patrimonies> current = new ArrayList<Patrimonies>();
		for(int i = 0; i<patrimonies.size(); i++){
			if((patrimonies.get(i).getDebit() != 0) || (patrimonies.get(i).getCredit() != 0)){
				current.add(patrimonies.get(i));
			}
		}
		return current;
	}
	
	public String testBalance(){ 
		String testBalance = "";
		testBalance += " Code"+ " Name"+" Debit"+" Credit"+"\n";
		List<Assets> assentsCurrent = assetsBalance();
		for(int i = 0; i<assentsCurrent.size(); i++){
			testBalance += ""+ assentsCurrent.get(i).toString()+"\n";
		}
		List<Liabilities> liabilitiesCurrent = liabilitiesBalance();
		for(int i = 0; i<liabilitiesCurrent.size(); i++){
			testBalance += ""+ liabilitiesCurrent.get(i).toString()+"\n";
		}
		List<Patrimonies> patrimoniesCurrent = patrimoniesBalance();
		for(int i = 0; i<patrimoniesCurrent.size(); i++){
			testBalance += ""+ patrimoniesCurrent.get(i).toString()+"\n";
		}
		return testBalance;
	}
}
