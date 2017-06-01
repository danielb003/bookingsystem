package Object;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import MainPackage.BookingSystem;
import Object.Person;

public class Owner extends Person {

	private String businessName, businessOwnName;
	
	public Owner() {
		
	}
	public Owner(String ID, String businessName, String name, String password, String address, String number) {
		super(ID, name, password, address, number);
		this.businessName = businessName;
	}
	
	public String getBusinessName() { return businessName; }
	
	@Override
	public void setID(String ID) {
		if(ID.matches("[0-9]{3}")) {
			ID.trim();
			this.ID = ID;
		}
	}
	public void setBusinessName(String businessName) {
		if(businessName.matches("[a-zA-Z ]{2,30}")) {	
			this.businessName = businessName;		
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
	public String toString(){
		return ID + "," + businessName + "," + businessOwnName + "," + password + "," + address + "," + number;
	}
	@Override
	//Function to add new owner into the system database
	public void addOwner(Person nO){
		Connection con = null;
		Statement statement = null;
		try {
			con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			statement = con.createStatement();
			
			/* SQL Statement */
			statement.executeUpdate("INSERT INTO owner values('o" + nO.getID() + "', '" + getBusinessName() + "','" + nO.getName() + "', '" 
					+ nO.getPassword() + "', '" + nO.getAddress() + "', '0" + nO.getNumber() + "')");
			BookingSystem.log.info("Owner Added To DataBase "+ nO.number);
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
