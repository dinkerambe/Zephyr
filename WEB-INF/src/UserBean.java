import java.math.BigInteger;

public class UserBean {

	private String email = null;
	private String password = null;
	private String name = null;
	private long userID;
	private String userID_str = null;

	private String firstName = null;
	private String lastName = null;
	

	private boolean valid;

	public UserBean(){}
	public UserBean(long userID, String email, String firstName, String lastName, 
		String password){

		this.userID = userID;
		setUserID_str();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;

		setUserID_str();
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email= email;
	}

	
	
	//this will not give the correct value (if int is really long) 
	//because it is signed
	public long getUserID(){
		return userID;
	}
	public void setUserID(long userID){
		this.userID = userID;
		setUserID_str();
	}
	
	//solution to long not being able to store 64bit unsigned ints
	public void setUserID_str(){
		final BigInteger TWO_64 = BigInteger.ONE.shiftLeft(64);
		BigInteger b = BigInteger.valueOf(this.userID);
		if(b.signum() < 0)
			b=b.add(TWO_64);
		userID_str = b.toString();	
	}
	public void setUserID_str(long userID){
		setUserID(userID);
		final BigInteger TWO_64 = BigInteger.ONE.shiftLeft(64);
		BigInteger b = BigInteger.valueOf(userID);
		if(b.signum() < 0)
			b=b.add(TWO_64);
		userID_str = b.toString();	
	}
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}	
}
