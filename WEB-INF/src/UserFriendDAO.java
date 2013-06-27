
public class UserFriendDAO{
	static ResultSet rs;
	static Connection con;
	final static String UID_DB = "user_id";
	final static String UID1_DB = "user1_id";
	final static String UID2_DB = "user2_id";
	final static String USER_TABLE= "tomcat_users";
	final static String USER_FRIEND_TABLE = "user_friends"

	public static initTable(){
		try{
			SQLCMD.initConnection();
			List<String> fields = new ArrayList<String>();
			fields.add(UID1_DB + " BIGINT UNSIGNED NOT NULL REFERENCES " + USER_TABLE +"(" + UID_DB + ")");
			fields.add(UID2_DB + " BIGINT UNSIGNED NOT NULL REFERENCES " + USER_TABLE +"(" + UID_DB + ")");
			fields.add("CONSTRAINT CheckOneWay CHECK (" + UID1_DB + " < " + UID2_DB ")");
			fields.add("CONSTRAINT PK_1_2 PRIMARY KEY (" + UID1_DB + ", " + UID2_DB ")");
			fields.add("CONSTRAINT UQ_2_1 UNIQUE (" + UID2_DB + ", " + UID1_DB + ")");
			SQLCMD.createTable(USER_FRIEND_TABLE, fields);

		}catch(Exception e){
			System.out.println("USER_FRIEND_DAO: INIT TABLE FAILED: " + e.getMessage());
		}finally{
			SQLCMD.closeConnection();
		}
	}


	public static isFriend(UserFriendBean bean){
		boolean result;
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

			List<String values = new ArrayList<String>();
			if(user1 < user2){
				values.add(user1_str);
				values.add(user2_str);
			}
			else{
				values.add(user2_str);
				values.add(user1_str);
			}

			rs = SQLCMD.select(USER_FRIEND_TABLE, fields, values);
			result = rs.next();
		}catch(Exception e){System.out.println("USER_FRIEND_DAO isFriend ERROR");}
		finally{
			SQLCMD.closeConnection();
		}
		return result;
	}
	
	public static insertFriend(UserFriendBean bean){
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

			List<String values = new ArrayList<String>();
			if(user1 < user2){
				values.add(user1_str);
				values.add(user2_str);
			}
			else{
				values.add(user2_str);
				values.add(user1_str);
			}

			SQLCMD.insertField(USER_FRIEND_TABLE, fields, values);
		}catch(Exception e){System.out.println("USER_FRIEND_DAO insert failed");}
		finally{
			SQLCMD.closeConnection();
		}
	}
	
	public static inserFriend_safe(UserFriendBean bean){
		if(!isFriend(bean))
			insertFriend(bean);
	}
			
	


}
