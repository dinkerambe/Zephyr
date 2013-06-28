import java.util.*;
import java.sql.ResultSet;
import java.sql.Connection;

public class PostOwnerDAO{
	final static String UID_DB = "user_id";
	final static String CONTEST_DB = "contest_id";
	final static String POST_DB = "post_id";

	final static String USER_TABLE = "tomcat_users";
	final static String CONTEST_TABLE = "contests";
	final static String POST_OWNERS_TABLE= "post_owners";
	final static String POST_TABLE = "posts;

	public static void initTable(){
		try{
			SQLCMD.initConnection();
			List<String> fields = new ArrayList<String>();
			fields.add(UID_DB + " BIGINT UNSIGNED NOT NULL REFERENCES " + USER_TABLE + "(" + UID_DB + ")");
			fields.add(CONTEST_DB + " BIGINT UNSIGNED NOT NULL REFERENCES " + CONTEST_TABLE + "(" + CONTEST_DB + ")");
			fields.add(POST_DB + " BIGINT UNSIGNED NOT NULL REFERENCES " + POST_TABLE + "(" + POST_DB + ")");

			SQLCMD.createTable(POST_OWNERS_TABLE, fields);
		}catch(Exception e){
			System.out.println("POST_OWNER_DAO: INIT TABLE FAILED " + e.getMessage());}
		finally{
			SQLCMD.closeConnection();
		}
	}

	//CONTEST TO OTHER
	public static List<long> grabContestPosts(long contestID){
		return grabManyID(CONTEST_DB, POST_DB, contestID);
	}

	public static List<long> grabContestUsers(long userID){
		return grabManyID(CONTEST_DB, UID_DB, contestID);
	}

	//POST TO OTHER
	public static long grabPostContest(long postID){
		return grabOneID(POST_DB, CONTEST_DB, postID);
	}

	public static long grabPostUser(long postID){
		return grabOneID(POST_DB, UID_DB, postID);
	}
	
	//USER TO OTHER
	public static List<long> grabUserPosts(long userID){
		return grabManyID(UID_DB, POST_DB, userID);
	}

	public static List<long> grabUserContests(long userID){
		return grabManyID(UID_DB, CONTEST_DB, userID);
	}
	
	//BASE METHODS
	private static List<long> grabManyID(String subject, String object, long ID){
		List<long> result = new ArrayList<long>();	
		try{
			SQLCMD.initConnection();
			rs = SQLCMD.select(POST_OWNERS_TABLE, subject, ID);
			while(rs.next()){
				result.add(rs.getLong(object));
			}
		}catch(Exception e){System.out.println("POST_OWNER_DAO: grab" +subject + object + " failed");}
		finally{
			SQLCMD.closeConnection();
		}
		return result;
	}

	private static long grabOneID(String subject, String object, long ID){
		long result = -1;
		try{
			SQLCMD.initConnection();
			rs = SQLCMD.select(POST_OWNERS_TABLE, subject, ID);
			result = rs.getLong(object);
		}catch(Exception e){System.out.println("POST_OWNER_DAO: grab" + subject + object + " failed");}
		finally{
			SQLCMD.closeConnection();
		}
		return result;

	}


}
