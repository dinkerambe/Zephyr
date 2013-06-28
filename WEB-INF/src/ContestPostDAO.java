import java.util.*;
import java.sql.ResultSet;
import java.sql.Connection;

public class ContestPostDAO{
	final static String CONTEST_DB = "contest_id";
	final static String POST_DB = "post_id";

	final static String CONTEST_TABLE = "contest_table";
	final static String CONTEST_POST_TABLE= "contest_post_table";
	final static String POST_TABLE = "post_table;

	public static void initTable(){
		try{
			SQLCMD.initConnection();
			List<String> fields = new ArrayList<String>();
			fields.add(CONTEST_DB + " BIGINT UNSIGNED NOT NULL REFERENCES " + CONTEST_TABLE + "(" + CONTEST_DB + ")");
			fields.add(POST_DB + " BIGINT UNSIGNED NOT NULL REFERENCES " + POST_TABLE + "(" + POST_DB + ")");

			SQLCMD.createTable(CONTEST_POST_TABLE, fields);
		}catch(Exception e){
			System.out.println("CONTEST_POST_DAO: INIT TABLE FAILED " + e.getMessage());}
		finally{
			SQLCMD.closeConnection();
		}
	}

	public static ArrayList<long> grabPosts(long ContestID){
		List<long> result = new ArrayList<long>();	
		try{
			SQLCMD.initConnection();
			rs = SQLCMD.select(CONTEST_POST_TABLE, CONTEST_DB, "" + ContestID);
			while(rs.next()){
				result.add(rs.getLong(POST_DB));
			}
		}catch(Exception e){System.out.println("CONTEST_POST_DAO grabPosts failed");}
		finally{
			SQLCMD.closeConnection();
		}
		return result;
	}



}
