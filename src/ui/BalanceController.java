package ui;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.Account;
import model.Assets;
import model.Company;
import model.Liabilities;
import model.Patrimonies;

public class BalanceController {
	
	private Company company;
	
	@FXML
    private BorderPane borderPane;

    @FXML
    private ImageView logo;

    @FXML
    private Label companyInformation;

    @FXML
    private SplitPane slipPane;

    @FXML
    private TableView<Account> table;

    @FXML
    private TableColumn<Account, Integer> code;

    @FXML
    private TableColumn<Account, String> name;

    @FXML
    private TableColumn<Account, Double> debit;

    @FXML
    private TableColumn<Account, Double> credit;
    
    @FXML
    private Label balance;
    
    @FXML
    private TextArea listDebit;

    @FXML
    private TextArea listCredit;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField codeTF;

    @FXML
    private TextField debitTF;

    @FXML
    private TextField creditTF;
    
    private ObservableList<Account> listAssets; 
    
    private ObservableList<Account> listLiabilities;
    
    private ObservableList<Account> listPatrimonies; 
      
    @FXML
    public void initialize() throws IOException{
    	
    	company = new Company();
    	company.loadInformation("data/companyInformation.txt");
    	Image imagen = new Image(company.getLogo());
    	logo.setImage(imagen);
    	companyInformation.setText("     "+company.getName()+"\n"+"NIT "+company.getNit());
    	initializeTable();
    	
    }
    
    public void  initializeTable() throws IOException {
    	code.setCellValueFactory(new PropertyValueFactory<>("code"));
		
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		debit.setCellValueFactory(new PropertyValueFactory<>("debit"));
		
		credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
    }
    
    @FXML
    public void handleRowSelect(MouseEvent event) {
    	Account row = table.getSelectionModel().getSelectedItem(); 
        if(row != null){ 
        	nameTF.setText(row.getName());
        	codeTF.setText(""+row.getCode());
        	addListBalance(row);
        } 
    }
    
    public void addListBalance(Account current) {
    	if(current.getListDebit()!= null) {
    		String debit = "";
    		for(int i = 0; i<current.getListDebit().size(); i++) {
    			debit += " "+current.getListDebit().get(i)+"\n";
        	}
    		listDebit.setText(debit);
    	}
    	
    	if(current.getListCredit()!= null) {
        	String credit = ""; 
    		for(int i = 0; i<current.getListCredit().size(); i++) {
        		credit += " "+current.getListCredit().get(i)+"\n";
        	}
    		listCredit.setText(credit);
    	}
    	balance.setText(current.getBalance());
    	current.setCredit(current.calculateCredit());
    	current.setDebit(current.calculateDebit());
    }
    
    
    public ObservableList<Account> getAssets(List<Assets> current) throws IOException{
    	listAssets = FXCollections.observableArrayList();
    	for(int i = 0 ;i < current.size(); i++) {
    		listAssets.add(current.get(i));
    	}
		return listAssets;
    }
    
    public ObservableList<Account> getLiabilities(List<Liabilities> current) throws IOException{
    	listLiabilities = FXCollections.observableArrayList();
    	for(int i = 0 ;i < current.size(); i++) {
    		listLiabilities.add(current.get(i));
    	}
		return listLiabilities;
    }
    
    public ObservableList<Account> getPatrimonies(List<Patrimonies> current) throws IOException{
    	listPatrimonies = FXCollections.observableArrayList();
    	for(int i = 0 ;i < current.size(); i++) {
    		listPatrimonies.add(current.get(i));
    	}
		return listPatrimonies;
    }
    
    @FXML
    public void addBalance(ActionEvent event) {
    	
    	String code = codeTF.getText(); 
    	if(code.charAt(0) == '1' ) {
    		addBalanceAssets(code);
    	}
    	else if(code.charAt(0) == '2') {
    		addBalanceLiabilities(code);
    	}
    	else {
    		addBalancePatrimonies(code);
    	}
    }
    
    public void addBalanceAssets(String code) {
    	if(debitTF.getText() != null) {
			int code1 = Integer.parseInt(code);
			Assets current = company.searchAssetsByCode(code1);
			String debitCurrent = debitTF.getText();
			double debit = Double.parseDouble(debitCurrent);
			current.getListDebit().add(debit);
			addListBalance(current);
		}
		if(creditTF.getText() != null) {
			int code1 = Integer.parseInt(code);
			Assets current = company.searchAssetsByCode(code1);
			String creditCurrent = creditTF.getText();
			double credit = Double.parseDouble(creditCurrent);
			current.getListCredit().add(credit);
			addListBalance(current);
		}
    }
    
    public void addBalanceLiabilities(String code) {
    	if(debitTF.getText() != null) {
			int code1 = Integer.parseInt(code);
			Liabilities current = company.searchLiabilitiesByCode(code1);
			String debitCurrent = debitTF.getText();
			double debit = Double.parseDouble(debitCurrent);
			current.getListDebit().add(debit);
			addListBalance(current);
		}
		if(creditTF.getText() != null) {
			int code1 = Integer.parseInt(code);
			Liabilities current = company.searchLiabilitiesByCode(code1);
			String creditCurrent = creditTF.getText();
			double credit = Double.parseDouble(creditCurrent);
			current.getListCredit().add(credit);
			addListBalance(current);
		}
    }
    
    public void addBalancePatrimonies(String code) {
    	if(debitTF.getText() != null) {
			int code1 = Integer.parseInt(code);
			Patrimonies current = company.searchPatrimoniesByCode(code1);
			String debitCurrent = debitTF.getText();
			double debit = Double.parseDouble(debitCurrent);
			current.getListDebit().add(debit);
			addListBalance(current);
		}
		if(creditTF.getText() != null) {
			int code1 = Integer.parseInt(code);
			Patrimonies current = company.searchPatrimoniesByCode(code1);
			String creditCurrent = creditTF.getText();
			double credit = Double.parseDouble(creditCurrent);
			current.getListCredit().add(credit);
			addListBalance(current);
		}
    }
    
  
    @FXML
    public void BalanceSheet(ActionEvent event) {

    }
    
    @FXML
    public void assets(ActionEvent event) throws IOException {
    	company.loadAssets();
    	getAssets(company.getAssets());
    	table.setItems(listAssets);
    }

    @FXML
    public void liabilities(ActionEvent event) throws IOException {
    	company.loadLiabilities();
    	getLiabilities(company.getLiabilities());
    	table.setItems(listLiabilities);
    }

    @FXML
    public void patrimonies(ActionEvent event) throws IOException {
    	company.loadPatrimonies();
    	getPatrimonies(company.getPatrimonies());
    	table.setItems(listPatrimonies);
    }

    @FXML
    public void saveInformation(ActionEvent event) {

    }

    @FXML
    public void sortCode(ActionEvent event) throws IOException {
    	String code = ""+table.getItems().get(0).getCode();
    	if(code.charAt(0) == '1') {
    		company.sortAssetsByCode();
    		getAssets(company.getAssets());
    		table.setItems(listAssets);
    	}
    	else if(code.charAt(0) == '2') {
    		company.sortLiabilitiesByCode();
    		getLiabilities(company.getLiabilities());
        	table.setItems(listLiabilities);
    	}
    	else {
    		company.sortPatrimoniesByCode();
    		getPatrimonies(company.getPatrimonies());
        	table.setItems(listPatrimonies);
    	}
    }

    @FXML
    public void sortName(ActionEvent event) throws IOException {
    	String code = ""+table.getItems().get(0).getCode();
    	if(code.charAt(0) == '1') {
    		company.sortAssetsByName();
    		getAssets(company.getAssets());
    		table.setItems(listAssets);
    	}
    	else if(code.charAt(0) == '2') {
    		company.sortLiabilitiesByName();
    		getLiabilities(company.getLiabilities());
        	table.setItems(listLiabilities);
    	}
    	else {
    		company.sortPatrimoniesByName();
    		getPatrimonies(company.getPatrimonies());
        	table.setItems(listPatrimonies);
    	}
    }

    @FXML
    public void testBalance(ActionEvent event) {
    	//Label label = new Label(""+company.testBalance());
    }

}
