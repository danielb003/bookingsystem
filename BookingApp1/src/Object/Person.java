package Object;

//Person class that contains all basic attributes of a person
public abstract class Person {
	protected String ID;
	protected String name;
	protected String password;
	protected String address;
	protected String number;
	static String[] customer = new String[2]; 
	static String[] customer1 = new String[3];
	static String[] custID = new String[1];
	String companyID = null;

	public Person(){
	
	}
	public Person(String ID, String name, String password, String address, String number){
		this.ID = ID;
		this.name = name;
		this.password = password;
		this.address = address;
		this.number = number;

	}
	public abstract void setID(String ID);
	public abstract void setName(String name);
	public abstract void setPassword(String password);
	public abstract void setAddress(String address);
	public abstract void setNumber(String number);
	/*public String retrieveIDName()
	public String retrieveNameNumAddress()*/
	
	public String getID() { return ID; }
	public String getName() { return name; }
	public String getPassword() { return password; }
	public String getAddress() { return address; }
	public String getNumber() { return number; }
	
	public String toString(){
		return ID + "," + name + "," + password + "," + address + "," + number;
	}
	public void addCustomer(Person nC) {
		// TODO Auto-generated method stub
		
	}
	//These 2 functions is used during booking to store the customer information
	public static void storeIDName(String ID, String name) {
		customer[0] = ID;
		customer[1] = name;
	}
	public static void storeNameNumAddress(String name, String number, String address) {
		customer1[0] = name;
		customer1[1] = number;
		customer1[2] = address;
	}
	//This function is to store the customer ID when they successfully logged in for the booking purposes.
	public static void storeID(String ID) {
		custID[0] = ID;
	}
	//These 2 functions is used during booking to retrieve the customer information and insert them
	//into the database
	public static String retrieveIDName() {
		String ID = customer[0];
		String name = customer[1];
		String result = null;
		if(ID == null) {
			if(name == null) {
				result = null;
			}
		}
		else {
			result = ID;
		}
		return result;
	}
	public static String[] retrieveNameNumAddress() {
		String name = customer1[0];
		String number = customer1[1];
		String address = customer1[2];
		String[] result = null;
		if(name == null && number == null && address == null) {
			result = null;
		}
		else {
			result = customer1;
		}
		return result;
	}
	//This function is to retrieve the customer ID to search for more information and insert them
	//into the database
	public static String retrieveID() {
		String custid = custID[0];
		return custid;
	}
	public void addOwner(Person nO) {
		// TODO Auto-generated method stub
		
	}
	public void setcompanyID(String companyID) {
		this.companyID = companyID;			
	}
	public String retreivecompID(){
		return companyID;
	}
}
