package Object;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import MainPackage.BookingSystem;

public class Customer extends Person {
	final static Scanner scan = new Scanner(System.in);
	
	public Customer(){
		
	}
	public Customer(String ID, String name, String password, String address, String number) {
		super(ID, name, password, address, number);
	}
	public void createBooking() {
		// TODO Auto-generated method stub
	}
	//Input validation for new customer data to register into the system
	@Override
	public void setID(String ID) {
		if(ID.matches("[0-9]{3}")) {
			ID.trim();
			this.ID = ID;
		}			
	}
	@Override
	public void setName(String name) {
		if(name.matches("[a-zA-Z ]{2,30}")) {	
			this.name = name;		
		}
	}
	@Override
	public void setPassword(String password) {	
		if(password.matches("[a-zA-Z0-9,./';:?><{}|+=-_()*&^%$#@!`~]{8,20}")){
			this.password = password;			
		} 
	}
	@Override
	public void setAddress(String address) {
		if(address.matches("[a-zA-Z0-9 ,./':-|_`~&$@#+()]{2,40}")){
			this.address = address;	
		}			
	}
	@Override
	public void setNumber(String number) {
		if(number.matches("[0-9]{10}")){
			number.trim();
			this.number = number;	
		}
	}
	@Override
	public void setcompanyID(String companyID) {
			this.companyID = companyID;			
	}
	@Override
	public String toString(){
		return ID + "," + name + "," + password + "," + address + "," + number;
	}
	@Override
	//Function to add new customer into the system database
	public void addCustomer(Person nC){
		String ownerid = nC.retreivecompID();
		Connection con = null;
		Statement statement = null;
		try {
			con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			statement = con.createStatement();
			
			/* SQL Statement */
			statement.executeUpdate("INSERT INTO customer values('c" + nC.getID() + "', '" + nC.getName() + "', '" 
					+ nC.getPassword() + "', '" + nC.getAddress() + "', '0" + nC.getNumber() + "','" + ownerid + "')");
			BookingSystem.log.info("Customer Added To DataBase "+ nC.number);
		} catch (Exception e) {
			BookingSystem.log.error(e);
			System.err.println(e);
		} finally {
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) { BookingSystem.log.error(e);}
			}
		}
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {BookingSystem.log.error(e); }
		}
	}
}
