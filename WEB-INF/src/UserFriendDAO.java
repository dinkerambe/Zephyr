
public class UserFriendDAO{

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


}
