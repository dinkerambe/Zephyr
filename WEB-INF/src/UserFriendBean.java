import java.math.BigInteger;


public class UserFriendBean{

	private long userID;
	private long friendID;

	private String userID_str;
	private String friendID_str;

	private boolean linked;

	public UserFriendBean(){}

	public UserFriendBean(long userID, long friendID){
		this.userID = userID;
		this.friendID = friendID;
		userID_str = ID_to_str(userID);
		friendID_str = ID_to_str(friendID); 
	}

	public UserFriendBean(long userID, long friendID, boolean linked){
		this.userID = userID;
		this.friendID = friendID;
		this.linked = linked;
		userID_str = ID_to_str(userID);
		friendID_str = ID_to_str(friendID); 
	}

	private String ID_to_str(long ID){
		final BigInteger TWO_64 = BigInteger.ONE.shiftLeft(64);
		BigInteger b = BigInteger.valueOf(ID);
		if(b.signum() < 0)
			b=b.add(TWO_64);

		return b.toString();
	}
	
	public long getUserID(){
		return userID;
	}
	public long getFriendID(){
		return friendID;
	}
	public String getUserID_str(){
		return userID_str;
	}
	public String getFriendID_str(){
		return friendID_str;
	}
	public boolean getLinked(){
		return linked;
	}
	
	public void setUserID(long userID){
		this.userID = userID;
		this.userID_str = ID_to_str(userID);
	}
	public void setFriendID(long friendID){
		this.friendID = friendID;
		this.friendID_str = ID_to_str(friendID);
	}
	public void setLinked(boolean linked){
		this.linked = linked;
	}










}
