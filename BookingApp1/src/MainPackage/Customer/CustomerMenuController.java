package MainPackage.Customer;

import java.io.IOException;

import MainPackage.BookingSystem;
import javafx.fxml.FXML;

public class CustomerMenuController {
	
	@SuppressWarnings("unused")
	private BookingSystem main;
	
	@FXML
	private void goLogout() throws IOException {
		BookingSystem.showLogin3();
	}
	@FXML
	private void goBookingHistory() throws IOException{
		BookingSystem.showBookingHistoryCustomer();
	}
	@FXML
	private void goCustCreateBookingDetails() throws IOException {
		BookingSystem.showCustCreateBookingDetails();
	}
}
