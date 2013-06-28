import java.util.*;
import java.sql.ResultSet;
import java.sql.Connection;
public class PostVotesDAO{
	static ResultSet rs;
	static Connection con;

	final static String UID_DB = "user_id";
	final static String POST_DB = "post_id";
	
	final static String USER_TABLE = "tomcat_users";
	final static String POST_TABLE = "posts";
	final static String POST_VOTES_TABLE = "post_voters";
	public static void initTable(){
		try{
			SQLCMD.initConnection();
			List<String> fields = new ArrayList<String>();
			fields.add(POST_DB + " BIGINT UNSIGNED NOT NULL");
			fields.add(UID_DB + " BIGINT UNSIGNED NOT NULL");
			fields.add("FOREIGN KEY (" + POST_DB + ") REFERENCES " + POST_TABLE + "(" + POST_DB + ")");
			fields.add("FOREIGN KEY (" + UID_DB + ") REFERENCES " + USER_TABLE + "(" + UID_DB + ")");

			SQLCMD.createTable(POST_VOTES_TABLE, fields);
		}catch(Exception e){
			System.out.println("POST_VOTERS_DAO: INIT TABLE FAILED " + e.getMessage());}
		finally{
			SQLCMD.closeConnection();
		}
	}

	public static List<Long> grabPostVoters(long postID){
		List<Long> result = new ArrayList<Long>();
		try{
			SQLCMD.initConnection();
			rs = SQLCMD.select(POST_VOTES_TABLE, POST_DB, "" + postID);
			while(rs.next()){
				result.add(new Long(rs.getLong(UID_DB)));
			}
		}catch(Exception e){System.out.println("POST_VOTERS_DAO: grabPostVoters failed " + e.getMessage());}
		finally{
			SQLCMD.closeConnection();
		}
		return result;
	}

	public static List<Long> grabVoterPosts(long userID){
		List<Long> result = new ArrayList<Long>();
		try{
			SQLCMD.initConnection();
			rs = SQLCMD.select(POST_VOTES_TABLE, UID_DB, "" + userID);
			while(rs.next()){
				result.add(new Long(rs.getLong(POST_DB)));
			}
		}catch(Exception e){System.out.println("POST_VOTERS_DAO: grabVoterPosts failed " + e.getMessage());}
		finally{
			SQLCMD.closeConnection();
		}
		return result;
	}

	public static int getVoteCount(long postID){
		return grabPostVoters(postID).size();
	}

	public static void pushVoter(long postID, long voterID)
	{
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		fields.add(POST_DB);
		fields.add(UID_DB);
		values.add(""+postID);
		values.add(""+voterID);
		try {
			SQLCMD.initConnection();
			SQLCMD.insertField(POST_VOTES_TABLE, fields, values);
		} catch (Exception e) {
			System.out.println("POST_VOTERS_DAO: pushVoter failed" + e.getMessage());
		} finally{
			SQLCMD.closeConnection();
		}
	}

}
