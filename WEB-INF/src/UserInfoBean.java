import java.sql.Timestamp;
import java.util.Date;

public class UserInfoBean{

	private long userID;
	private String userID_str=null;
	private String address = null;
	private Date age = null;
	private int popularity;
	private int wisdom;
	private Timestamp timestamp = null;



	public UserInfoBean(){}

	public UserInfoBean(long userID){
		this.userID = userID;	
	}

	public UserInfoBean(long userID, String address, Date age, int popularity, int wisdom, Timestamp timestamp){
		this.userID = userID;
		this.address = address;
		this.age = age;
		this.popularity = popularity;
		this.wisdom = wisdom;
		this.timestamp = timestamp;
	}

	public void setTimeStamp(Timestamp timestamp){
		this.timestamp = timestamp;
	}
	public Timestamp getTimeStamp(){
		return timestamp;
	}
	public Timestamp makeTimeStamp(){
		Date temp = new Date();
		timestamp = new Timestamp(temp.getTime());
		return timestamp;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address= address;
	}
	
	public Date getAge(){
		return age;
	}

	public void setAge(Date age){
		this.age = age;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public int getWisdom() {
		return wisdom;
	}

	public void setWisdom(int wisdom) {
		this.wisdom= wisdom;
	}
}
