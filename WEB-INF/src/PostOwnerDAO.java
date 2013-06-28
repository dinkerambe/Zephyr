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

	public static List<long> grabContestPosts(long contestID){
		List<long> result = new ArrayList<long>();	
		try{
			SQLCMD.initConnection();
			rs = SQLCMD.select(POST_OWNERS_TABLE, CONTEST_DB, contestID);
			while(rs.next()){
				result.add(rs.getLong(POST_DB));
			}
		}catch(Exception e){System.out.println("POST_OWNER_DAO: grabContestPosts failed");}
		finally{
			SQLCMD.closeConnection();
		}
		return result;
	}

	public static List<long> grabUserPosts(long userID){
		List<long> result = new ArrayList<long>();	
		try{
			SQLCMD.initConnection();
			rs = SQLCMD.select(POST_OWNERS_TABLE, UID_DB, userID);
			while(rs.next()){
				result.add(rs.getLong(POST_DB));
			}
		}catch(Exception e){System.out.println("POST_OWNER_DAO: grabUserPosts failed");}
		finally{
			SQLCMD.closeConnection();
		}
		return result;
	}

	public static long grabPostContest(long postID){
		long result = -1;
		try{
			SQLCMD.initConnection();
			rs = SQLCMD.select(POST_OWNERS_TABLE, POST_DB, postID);
			result.add(rs.getLong(CONTEST_DB));
		}catch(Exception e){System.out.println("POST_OWNER_DAO: grabPostContest failed");}
		finally{
			SQLCMD.closeConnection();
		}
		return result;
	}

	public static List<long> grabUserContests(long userID){
		List<long> result = new ArrayList<long>();	
		try{
			SQLCMD.initConnection();
			rs = SQLCMD.select(POST_OWNERS_TABLE, UID_DB, userID);
			while(rs.next()){
				result.add(rs.getLong(CONTEST_DB));
			}
		}catch(Exception e){System.out.println("POST_OWNER_DAO: grabUserContests failed");}
		finally{
			SQLCMD.closeConnection();
		}
		return result;
	}



}
