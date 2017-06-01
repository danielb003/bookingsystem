package Object;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class booking {
	public String bookingID;
	public String date;
	public String startTime;
	public String empID;
	public String serviesID;
	public String customerNumber;
	public String status;
	
	public booking(String id,String date,String startTime, String customerID, String empID,String serviesID,String status){
		this.bookingID = id;
		this.date = date;
		this.startTime = startTime;
		this.empID = empID;
		this.serviesID = serviesID;
		this.customerNumber = customerID;
		this.status = status;
	}
	
	public void updateStatus(String c) throws SQLException{
		Connection con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
		con.createStatement().executeQuery("UPDATE newbooking set status = '"+c+"' WHERE bookingID = '" +this.bookingID +"' ");
		con.close();
		this.status = c;
	}

}
