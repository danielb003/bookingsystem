package MainPackage.Owner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import MainPackage.BookingSystem;
import Object.booking;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class FullBookingSummaryOwnerController {
	
	private Connection con = null;
	private Statement statement = null;
	
	@FXML
	private TableView<booking> Orders;
	
	@FXML
	private TextField searchFeild;
	
	@FXML
	private Button searchButton;
	
	@FXML
	private TableColumn<booking,String> Date;
	@FXML
	private TableColumn<booking,String> Status;
	@FXML
	private TableColumn<booking,String> Time;
	@FXML
	private TableColumn<booking,String> Employee;
	@FXML
	private TableColumn<booking,String> Service;
	@FXML
	private TableColumn<booking,String> Customer;
	
	private final ObservableList<booking> dta = FXCollections.observableArrayList();
	
	@FXML
	private void initialize() {
		System.out.println("loading bookings");
		loadBookings("");
	}
	
	private void loadBookings(String search){
		try {
			con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			statement = con.createStatement();
			ResultSet bookingSet = statement.executeQuery("SELECT * FROM newbooking WHERE ownerID = '" + BookingSystem.companyLogin + "'");
			if(!search.equals("")){
				bookingSet = statement.executeQuery("SELECT * FROM newbooking WHERE `bookingID` LIKE '"+search+"' or `date` LIKE '"+search+"' or `startTime` LIKE '"+search+"' or `empID` LIKE '"+search+"' or `servicesID` LIKE '"+search+"'");
				bookingSet = statement.executeQuery("SELECT * FROM newbooking WHERE `bookingID` LIKE '"+search+"' or `date` LIKE '"+search+"' or `startTime` LIKE '"+search+"' or `empID` LIKE '"+search+"' or `servicesID` LIKE '"+search+"' or `status` LIKE '"+search+"' AND ownerID = '" + BookingSystem.companyLogin + "'");
			}
			while(bookingSet.next()) {
				
				BookingSystem.log.info("displaying booking history"+bookingSet.getString("bookingID")+" status"+bookingSet.getString("status"));
				String id = bookingSet.getString("bookingID");
				String date = bookingSet.getString("date");
				String time = bookingSet.getString("startTime");
				String employee = getEmpName(bookingSet.getString("empID"));
				String servies = bookingSet.getString("servicesID");
				String customer = getCustName(bookingSet.getString("customerNumber"));
				String status = getStatus(bookingSet.getString("status"));
				booking booking = new booking(id,date,time,customer,employee,servies,status);
				dta.add(booking);
			}
			
			bookingSet.close();
			statement.close();
			con.close();
		} catch (SQLException e1) {
			BookingSystem.log.error(e1);
			e1.printStackTrace();
		} 
		
		Orders.setItems(dta);
		Date.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().date));
		Time.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().startTime));
		Employee.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().empID));
		Customer.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().customerNumber));
		Service.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().serviesID));
		Status.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().status));
	}
	
	@FXML
	private void searchButonPressed() throws IOException {
		dta.clear();
		String search = "";
		if(this.searchFeild.getText() != null){
			search = this.searchFeild.getText();
		}
		System.out.println("searching for: "+search);
		this.loadBookings(searchFeild.getText());
	}
	
	private String getEmpName(String id){
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet1 = null;
		String empname = null;
		try{
			con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			statement = con.createStatement();	
			
			resultSet1 = statement.executeQuery("SELECT empid, name FROM employee");
			while(resultSet1.next()) {
				if(id.equals(resultSet1.getString("empid"))) {
					empname = resultSet1.getString("name");
				}					
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return empname;
	}
	
	private String getCustName(String number){
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet1 = null;
		String custname = null;
		try{
			con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			statement = con.createStatement();	
			
			resultSet1 = statement.executeQuery("SELECT number, name FROM customer");
			while(resultSet1.next()) {
				if(number.equals(resultSet1.getString("number"))) {
					custname = resultSet1.getString("name");
				}					
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return custname;
	}
	private String getStatus(String status){
		String stat = null;
		if(status.equals("w")){
			stat = "Booked";
		}
		else if(status.equals("c")){
			stat = "Cancelled";
		}
		return stat;
	}
}
