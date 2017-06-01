package MainPackage.Register;

import MainPackage.BookingSystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Object.Customer;
import Object.Person;

public class RegisterController {
	
	ArrayList<String[]> companyname = new ArrayList<String[]>();
	ObservableList<String> companyList = FXCollections.observableArrayList();
	Connection con = null;
	Statement statement = null;
	
	@FXML
	TextField newuserid;
	@FXML
	TextField newusername;
	@FXML
	TextField newuserpassword;
	@FXML
	TextField newusermobno;
	@FXML
	TextField newuseraddress;
	@FXML
	Button Submit;
	@FXML
	Label invaliduserid;
	@FXML
	Label invalidusername;
	@FXML
	Label invaliduserpassword;
	@FXML
	Label invalidusermobno;
	@FXML
	Label invaliduseraddress;
	@FXML
	Label invalidusercompany;
	@FXML
	Label Success;
	@FXML
	Label iddatabase;
	@FXML
	public ComboBox<String> newusercompanychoice = new ComboBox<String>(companyList);
	
	@FXML
	private void initialize() {
		{
			try {
				con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
				statement = con.createStatement();
				ResultSet empSet = statement.executeQuery("SELECT businessname, ownid FROM owner");
				while(empSet.next()) {
					companyList.add(empSet.getString("businessname"));
					newusercompanychoice.setItems(companyList);
					String[]x ={empSet.getString("businessname"),empSet.getString("ownid")};
					companyname.add(x);
				}
			} catch (SQLException e1) {
				BookingSystem.log.error(e1.toString());
				e1.printStackTrace();
			} finally {
				if (statement != null) {
					try {
						statement.close();
					} catch (SQLException e1) {
						BookingSystem.log.error(e1.toString());
					}
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e1) {
					BookingSystem.log.error(e1.toString());
				}
			}
		}
	}
	public void onSubmit(ActionEvent event) throws IOException {
		
		String custid = newuserid.getText();
		String name = newusername.getText();
		String password = newuserpassword.getText();
		String address = newuseraddress.getText();
		String number = newusermobno.getText();
		String compid = null;
		for(String[] x: companyname){
			if(newusercompanychoice.getSelectionModel().getSelectedItem().equals(x[0])){
				compid = x[1];
			}
		}
	
		Person nC = new Customer();
		
		//Input validate customer ID
		nC.setID(custid);
		if(nC.getID() == null) {				
			invaliduserid.setVisible(true);
		} else {
			invaliduserid.setVisible(false);
		}
		//Input validate customer Name
		nC.setName(name);
		if(nC.getName() == null) {			
			invalidusername.setVisible(true);
		} else {
			invalidusername.setVisible(false);
		}
		//Input validate customer Password
		nC.setPassword(password);
		if(nC.getPassword() == null){
			invaliduserpassword.setVisible(true);
		} else {
			invaliduserpassword.setVisible(false);
		}
		//Input validate customer Address
		nC.setAddress(address);
		if(nC.getAddress() == null){
			invaliduseraddress.setVisible(true);
		} else {
			invaliduseraddress.setVisible(false);
		}
		//Input validate customer Mobile Number
		nC.setNumber(number);
		if(nC.getNumber() == null){
			invalidusermobno.setVisible(true);
		} else {
			invalidusermobno.setVisible(false);
		}
		nC.setcompanyID(compid);
		if(compid == null){
			invalidusercompany.setVisible(true);
		} else {
			invalidusercompany.setVisible(false);
		}
		//If all input is valid, it stores the new customer data into the database
		if(!(nC.getID() == null) && !(nC.getName() == null) && !(nC.getPassword() == null) && !(nC.getNumber() == null)
				&& !(nC.getAddress() == null) && compid != null){
			custid = "c" + nC.getID(); 
			if(databasecheck(custid) == 0) {
				iddatabase.setVisible(false);
				nC.addCustomer(nC);
				Success.setVisible(true);
				newuserid.setText("");
				newusername.setText("");
				newuserpassword.setText("");
				newusermobno.setText("");
				newuseraddress.setText("");
				BookingSystem.showLogin2();
			}
			else if(databasecheck(custid) == 1) {
				iddatabase.setVisible(true);
			}
		}
	}
	public int databasecheck(String ID) {
		
		Connection con = null;
		Statement statement = null;
		int i = 0;
		try{
			con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			statement = con.createStatement();	
			ResultSet customerSet = statement.executeQuery("SELECT custid FROM customer");		
			while(customerSet.next()) {
				if(ID.equals(customerSet.getString("custid"))) {
					i = 1;
				}
			}
			while(customerSet.next()) {
				if(!ID.equals(customerSet.getString("custid"))) {
					i = 0;
				}
			}
		}catch (SQLException e1) {
			BookingSystem.log.error(e1);
			e1.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e1) {BookingSystem.log.error(e1); }
			}
		}
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e1) {BookingSystem.log.error(e1); }
		}		
		return i;
	}
	@FXML
	public void onGoBack() throws IOException{
		BookingSystem.showLogin2();
	}
}
