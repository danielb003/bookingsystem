package MainPackage.Customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import MainPackage.BookingSystem;
import Object.Person;
import Object.booking;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class FullBookingSummaryCustomerController {
	
	private Connection con = null;

	
	@FXML
	private TableView<booking> Orders;
	
	@FXML
	private TextField searchFeild;
	
	@FXML
	private Button searchButton;
	
	@FXML
	private TableColumn<booking,String> Date;
	@FXML
	private TableColumn<booking,String> Time;
	@FXML
	private TableColumn<booking,String> Employee;
	@FXML
	private TableColumn<booking,String> Service;
	@FXML
	private TableColumn<booking,String> Status;
	
	private final ObservableList<booking> dta = FXCollections.observableArrayList();
	
	@FXML
	private void initialize() {
		System.out.println("loading bookings");
		loadBookings("");
	}
	
	private void loadBookings(String search){
		try {
			con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			
			//SQL statement to get the mobile number that matches the logged in customer ID at the 
			//beginning of the booking
			
			ResultSet custSet = con.createStatement().executeQuery("SELECT number FROM customer Where customer.custID = '" + Person.retrieveID()+"' and customer.ownerID = '"+BookingSystem.companyLogin+"'");
			String customerNumber = custSet.getString("number");
			custSet.close();
			
			
			
			ResultSet bookingSet = con.createStatement().executeQuery("SELECT * FROM newbooking where newbooking.customerNumber = '"+customerNumber+"'");
			if(!search.equals("")){
				bookingSet = con.createStatement().executeQuery("SELECT * FROM newbooking WHERE `bookingID` LIKE '"+search+"' or `date` LIKE '"+search+"' or `startTime` LIKE '"+search+"' or `empID` LIKE '"+search+"' or `servicesID` LIKE '"+search+"' or `status` LIKE '"+search+"'  and newbooking.customerNumber = '" + customerNumber+"'");
			}
			while(bookingSet.next()) {
				System.out.println("loading bookings");
				BookingSystem.log.info("displaying booking history"+bookingSet.getString("bookingID"));
				String id = bookingSet.getString("bookingID");
				String date = bookingSet.getString("date");
				String time = bookingSet.getString("startTime");
				String employee = getEmpName(bookingSet.getString("empID"));
				String servies = getServiceName(bookingSet.getString("servicesID"));
				String customer = bookingSet.getString("customerNumber");
				String status = getStatus(bookingSet.getString("status"));
				booking booking = new booking(id,date,time,customer,employee,servies,status);
				dta.add(booking);
			}
			Orders.setItems(dta);
			Date.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().date));
			Time.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().startTime));
			Employee.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().empID));
			Service.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().serviesID));
			Status.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().status));
			bookingSet.close();
			con.close();
		} catch (SQLException e1) {
			BookingSystem.log.error(e1);
			e1.printStackTrace();
		} 
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
	
	@FXML
	private void removeButtonPressed(){
		booking selectedOrder = Orders.getSelectionModel().getSelectedItem();
		try {
			selectedOrder.updateStatus("c");
			BookingSystem.log.info(selectedOrder.bookingID +" as been cancelled");
		} catch (SQLException e) {
			BookingSystem.log.error(e.toString());
		}
	}
	//This function is to get the serviceID and search for a service name that matches it.
	private String getServiceName(String serviceID){
		Connection con = null;
		
		ResultSet resultSet1 = null;
		String servicename = null;
		try{
			con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
				
			
			resultSet1 = con.createStatement().executeQuery("SELECT servicesID, Services FROM BusinessActivities");
			while(resultSet1.next()) {
				if(serviceID.equals(resultSet1.getString("servicesID"))) {
					servicename = resultSet1.getString("Services");
				}					
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return servicename;
	}
	//This function is to get the ID and search for the employee name that matches it.
	private String getEmpName(String empID){
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet1 = null;
		String empname = null;
		try{
			con = DriverManager.getConnection("jdbc:sqlite:BookingSystem.db");
			statement = con.createStatement();	
			
			resultSet1 = statement.executeQuery("SELECT empid, name FROM employee");
			while(resultSet1.next()) {
				if(empID.equals(resultSet1.getString("empid"))) {
					empname = resultSet1.getString("name");
				}					
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return empname;
	}
	//This function is to get the character and change it to a complete word.
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
