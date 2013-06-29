import java.util.*;
import java.sql.ResultSet;
import java.sql.Connection;

public class UserFriendDAO{
	static ResultSet rs;
	static Connection con;
	final static String UID_DB = "user_id";
	final static String UID1_DB = "user1_id";
	final static String UID2_DB = "user2_id";
	final static String USER_TABLE= "tomcat_users";
	final static String USER_FRIEND_TABLE = "user_friends";

	public static void initTable(){
		try{
			SQLCMD.initConnection();
			List<String> fields = new ArrayList<String>();
			fields.add(UID1_DB + " BIGINT UNSIGNED NOT NULL REFERENCES " + USER_TABLE +"(" + UID_DB + ")");
			fields.add(UID2_DB + " BIGINT UNSIGNED NOT NULL REFERENCES " + USER_TABLE +"(" + UID_DB + ")");
			fields.add("CHECK (" + UID1_DB " != " UID2_DB +")");	
			SQLCMD.createTable(USER_FRIEND_TABLE, fields);

		}catch(Exception e){
			System.out.println("USER_FRIEND_DAO: INIT TABLE FAILED: " + e.getMessage());
		}finally{
			SQLCMD.closeConnection();
		}
	}


	public static boolean isFriend(UserFriendBean bean){
		boolean result = false;
		try{
			SQLCMD.initConnection();
			long user1 = bean.getUserID();
			long user2 = bean.getFriendID();
			String user1_str = bean.getUserID_str();
			String user2_str = bean.getFriendID_str();
			//MUST add unsigned long negative check property later

			List<String> fields = new ArrayList<String>();
			fields.add(UID1_DB);
			fields.add(UID2_DB);

			List<String> values = new ArrayList<String>();
			values.add(user1_str);
			values.add(user2_str);

			rs = SQLCMD.select(USER_FRIEND_TABLE, fields, values);
			result = rs.next();
		}catch(Exception e){System.out.println("USER_FRIEND_DAO isFriend ERROR");}
		finally{
			SQLCMD.closeConnection();
		}
		return result;
	}
	
	public static void insertFriend(UserFriendBean bean){
		try{
			SQLCMD.initConnection();
			long user1 = bean.getUserID();
			long user2 = bean.getFriendID();
			String user1_str = bean.getUserID_str();
			String user2_str = bean.getFriendID_str();
			//MUST add unsigned long negative check property later

			List<String> fields = new ArrayList<String>();
			fields.add(UID1_DB);
			fields.add(UID2_DB);

			List<String> values = new ArrayList<String>();
			values.add(user1_str);
			values.add(user2_str);

			SQLCMD.insertField(USER_FRIEND_TABLE, fields, values);
		}catch(Exception e){System.out.println("USER_FRIEND_DAO insert failed");}
		finally{
			SQLCMD.closeConnection();
		}
	}
	
	public static boolean insertFriend_safe(UserFriendBean bean){
		boolean check = !isFriend(bean);
		if(check)
			insertFriend(bean);

		return check;
	}
		
	public static void removeFriend(UserFriendBean bean){
		try{
			SQLCMD.initConnection();
			long user1 = bean.getUserID();
			long user2 = bean.getFriendID();
			String user1_str = bean.getUserID_str();
			String user2_str = bean.getFriendID_str();
			//MUST add unsigned long negative check property later

			List<String> fields = new ArrayList<String>();
			fields.add(UID1_DB);
			fields.add(UID2_DB);

			List<String> values = new ArrayList<String>();
			values.add(user1_str);
			values.add(user2_str);

			SQLCMD.delete(USER_FRIEND_TABLE, fields, values);
		}catch(Exception e){System.out.println("USER_FRIEND_DAO removeFriend failed");}
		finally{
			SQLCMD.closeConnection();
		}
	}

	public static boolean removeFriend_safe(UserFriendBean bean){
		boolean check = isFriend(bean);
		if(check)
			removeFriend(bean);

		return check;
	}
	
	public static ResultSet grabAllFriends(String userID){
		
		try{
			SQLCMD.initConnection();
			rs = SQLCMD.select(USER_FRIEND_TABLE, UID1_DB, userID);
		}catch(Exception e){System.out.println("USER_FRIEND_DAO grabAll failed");}
		finally{
			SQLCMD.closeConnection();
		}
		return rs;

	}

}		
	


