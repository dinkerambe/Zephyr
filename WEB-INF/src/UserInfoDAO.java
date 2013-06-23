import java.util.*;
import java.sql.*;

public class UserInfoDAO{
	
	static Connection currentCon = null;
	static ResultSet rs = null;

	public final static String USER_INFO_TABLE = "user_info";
	public final static String USER_TABLE = "tomcat_users";
	public final static String UID_DB = "user_id";
	public final static String TIMESTAMP_DB = "timestamp";
	public final static String ADDRESS_DB = "address";
	public final static String POPULAR_DB = "popularity";
	public final static String WISDOM_DB = "wisdom";
	public final static String AGE_DB = "age";

	public static <T> void pushField(String CONST_FIELD_VALUE, T value){
		try{
			SQLCMD.initConnection();
			SQLCMD.insertField(USER_INFO_TABLE, CONST_FIELD_VALUE, value.toString());
		}catch(Exception e){}
		finally{
			SQLCMD.closeConnection();
		}

	}

	public static void pushField(List<String> CONST_FIELD_VALUES, List<String> values){
		try{
			SQLCMD.initConnection();
			SQLCMD.insertField(USER_INFO_TABLE, CONST_FIELD_VALUES, values);
		}catch(Exception e){}
		finally{
			SQLCMD.closeConnection();
		}
	}

	public static UserInfoBean grabUserInfo(String userID_str){
		
		UserInfoBean result = null;

		try{

			SQLCMD.initConnection();
			rs = SQLCMD.select(USER_INFO_TABLE, UID_DB, userID_str);

			java.util.Date age = rs.getDate(AGE_DB);
			int popularity =rs.getInt(POPULAR_DB);
			int wisdom = rs.getInt(WISDOM_DB);
			String address = rs.getString(ADDRESS_DB);
			Timestamp timestamp = rs.getTimestamp(TIMESTAMP_DB);
			long userID_long  = rs.getLong(UID_DB);	

			result = new UserInfoBean(userID_long, address, age, popularity, wisdom, timestamp); 
		}catch(Exception e){System.out.println("USER_INFO_DAO: grabUserInfo String failed");}
		finally{
			SQLCMD.closeConnection();
		}
		return result;
	}

	public static UserInfoBean grabUserInfo(long userID){
		
		UserInfoBean result = null;

		try{

			SQLCMD.initConnection();
			rs = SQLCMD.select(USER_INFO_TABLE, UID_DB, "" + userID);

			java.util.Date age = rs.getDate(AGE_DB);
			int popularity =rs.getInt(POPULAR_DB);
			int wisdom = rs.getInt(WISDOM_DB);
			String address = rs.getString(ADDRESS_DB);
			Timestamp timestamp = rs.getTimestamp(TIMESTAMP_DB);
			long userID_long  = rs.getLong(UID_DB);	

			result = new UserInfoBean(userID_long, address, age, popularity, wisdom, timestamp); 
		}catch(Exception e){System.out.println("USER_INFO_DAO: grabUserInfo long  failed");}
		finally{
			SQLCMD.closeConnection();
		}
		return result;
	}

	public static void updateField(List<ValuePairs> oldParams, List<ValuePairs> newParams){
		try{
			SQLCMD.initConnection();
			SQLCMD.updateSelection(USER_INFO_TABLE, oldParams, newParams);
		}catch(Exception e){
			System.out.println("USER_INFO_DAO: update failed");
		}
		finally{
			SQLCMD.closeConnection();
		}

	}
	public static void initUserInfoTable(){
		try{
			SQLCMD.initConnection();

			List<String> fields = new ArrayList<String>();

			fields.add(UID_DB + " BIGINT UNSIGNED NOT NULL");
			fields.add("FOREIGN KEY (" + UID_DB + ") REFERENCES " + USER_TABLE + "(" + UID_DB + ")");

			fields.add(AGE_DB + " DATE");
			fields.add(TIMESTAMP_DB + " TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP");
			fields.add(POPULAR_DB + " INT DEFAULT 0");
			fields.add(WISDOM_DB + " INT DEFAULT 0");
			fields.add(ADDRESS_DB + " VARCHAR(255)");

			SQLCMD.createTable(USER_INFO_TABLE, fields);
		}catch(Exception e){
			System.out.println("USER_INFO_DAO: init user info table failed");
		}
		finally{
			SQLCMD.closeConnection();
		}
	
	}

}
