package MainPackage.Login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import MainPackage.BookingSystem;
import Object.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	
	@SuppressWarnings("unused")
	private BookingSystem main;
	@FXML
	TextField userid;
	@FXML
	PasswordField userpassword;
	@FXML
	Button login;
	@FXML
	Label invaliduserid;
	@FXML
	Label invaliduserpass;
		
	public void onLogin(ActionEvent event) throws IOException {
		
		String ID = userid.getText();
		String password = userpassword.getText();
		
		
		if(loginIDValidation(ID) == true){
			
			
			if(loginPasswordValidation(ID, password) == 1){
				// Launch Customer Menu
				BookingSystem.companyLogin = getCompnayType(ID);
				getCompanyInfo(BookingSystem.companyLogin);
				BookingSystem.log.info("Customer Logged in");
				BookingSystem.showCustomerMenu();
			} else if(loginPasswordValidation(ID, password) == 2){
				// Launch Owner Menu
				BookingSystem.log.info("Owner Logged in");
				BookingSystem.companyLogin = ID;
				getCompanyInfo(BookingSystem.companyLogin);
				BookingSystem.showOwnerMenu();
			} else if(loginPasswordValidation(ID, password) == 3){
				// Launch Super User Menu
				BookingSystem.log.info("Super User Logged in");
				BookingSystem.showSuperUserMenu();
			} else {
				invaliduserid.setVisible(true);
				invaliduserpass.setVisible(true);
				userid.setText("");
				userpassword.setText("");
			}
			
		} else if (loginIDValidation(ID) == false){
			// Incorrect user name or password
			invaliduserid.setVisible(true);
			invaliduserpass.setVisible(true);
			userid.setText("");
			userpassword.setText("");
		}
	}
	public void getCompanyInfo(String x){
		Connection con = null;
		
		String ownerid = "";
		try {
			con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			
			ResultSet ownerSet = con.createStatement().executeQuery("SELECT businessname FROM owner WHERE ownid ='"+x+"'");
			ownerid = ownerSet.getString("businessname");
			BookingSystem.log.info("customer loged in under company:"+ownerid);
			con.close();
			BookingSystem.companyname = ownerid;
		} catch (SQLException e) {
			BookingSystem.log.error(e.toString());
			e.printStackTrace();
		}
	}
	public String getCompnayType(String id){
		Connection con = null;
		ResultSet ownerSet = null;
		String ownerid = "";
		try {
			con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
				
			ownerSet = con.createStatement().executeQuery("SELECT ownerID FROM customer WHERE custid ='"+id+"'");
			ownerSet.next();
			ownerid = ownerSet.getString("ownerID");
			
			
			BookingSystem.log.info("customer loged in under company:"+ownerid);
			con.close();
		} catch (SQLException e) {
			BookingSystem.log.error(e.toString());
			e.printStackTrace();
		}
		
		return ownerid;
	}
	public boolean loginIDValidation(String ID){
		Connection con = null;
		
		boolean id = false;		
		if(ID.matches("[c][0-9]{3}") | ID.matches("[o][0-9]{3}") | ID.matches("[s][0-9]{3}")){	
			try{
				con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
				
				ResultSet ownerSet = con.createStatement().executeQuery("SELECT ownid FROM owner");
				ResultSet customerSet = con.createStatement().executeQuery("SELECT custid FROM customer");
				ResultSet superuserSet = con.createStatement().executeQuery("SELECT supid FROM superuser");
				if(customerSet.next() || ownerSet.next() || superuserSet.next()){
					BookingSystem.log.debug("ID found");
					id = true;
				}
				else {
					BookingSystem.log.debug("ID not found");
					id = false;
				}
				con.close();
			}catch (SQLException e1) {
				BookingSystem.log.error(e1.toString());
				e1.printStackTrace();
			} 
		}
		return id;
	}
	public int loginPasswordValidation(String ID, String password){	
		Connection con = null;
		
		ResultSet resultSet = null;
		int i = 0;	
		try{
			con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			
			//use string ID and password together
			if(ID.contains("o")){
				resultSet = con.createStatement().executeQuery("SELECT password FROM owner WHERE ownid='" + ID + "'");		
				if(resultSet.next()){			
					if(password.equals(resultSet.getString("password"))) {
						BookingSystem.log.debug("Correct password");
						i = 2;
					}
				}
			}
			else if(ID.contains("c")){
				resultSet = con.createStatement().executeQuery("SELECT password FROM customer WHERE custid='" + ID + "'");
				if(resultSet.next()){
					if(password.equals(resultSet.getString("password"))) {
						BookingSystem.log.debug("Correct password");
						Person.storeID(ID);
						i = 1;
					}
				}
			}
			else if(ID.contains("s")){
				resultSet = con.createStatement().executeQuery("SELECT password FROM superuser WHERE supid='" + ID + "'");
				if(resultSet.next()){
					if(password.equals(resultSet.getString("password"))) {
						BookingSystem.log.debug("Correct password");
						i = 3;
					}
				}
			}
			con.close();
		}catch (SQLException e){
			BookingSystem.log.error(e.toString());
			e.printStackTrace();
		}
		
		
		return i;
	}
	public void onSignUp() throws IOException {
		BookingSystem.showRegisterMenu();
	}
}
