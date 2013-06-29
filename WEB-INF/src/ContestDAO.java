//
//  ContestDAO.java
//  Zephyr
//
//  Created by Will Sawyer on 6/10/13.
//  Copyright (c) 2013 Zephyr. All rights reserved.
//

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ContestDAO {

	final static String UID_DB = "user_id";
	final static String CONTEST_DB = "contest_id";
	final static String POST_DB = "post_id";

	final static String USER_TABLE = "tomcat_users";
	final static String CONTEST_TABLE = "contests";
	final static String POST_TABLE = "post";


	public static void initDatabase()
	{
		List<String> contestFields = new ArrayList<String>();
		String str;
		str = CONTEST_DB + " BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY";
		contestFields.add(str);
		str = UID_DB + " BIGINT UNSIGNED NOT NULL REFERENCES " + USER_TABLE + "(" + UID_DB + ")";
		contestFields.add(str);
		str = "'numberOfContestants' int(64) NOT NULL";
		contestFields.add(str);
		str = "'runningTime' int(64) NOT NULL";
		contestFields.add(str);
		str = "'title' varchar(255) NOT NULL";
		contestFields.add(str);
		str = "'description' varchar(255) NOT NULL";
		contestFields.add(str);
		str = "'rules' varchar(255) NOT NULL";
		contestFields.add(str);
		str = "'mediaType' varchar(255) NOT NULL";
		contestFields.add(str);
		str = "'isGlobal' boolean NOT NULL";
		contestFields.add(str);
		str = "'isLocal' boolean NOT NULL";
		contestFields.add(str);
		str = "'isDone' boolean NOT NULL";
		contestFields.add(str);
		str = "'startTime' timestamp NOT NULL";
		contestFields.add(str);
		str = "'endTime' timestamp NOT NULL";
		contestFields.add(str);
		
		/*implemented elsewhere we need to fix this
		List<String> contestPostFields = new ArrayList<String>();
		str = "'contestID' int(64) NOT NULL PRIMARY KEY";
		contestPostFields.add(str);
		str = "'postID' int(64) NOT NULL";
		contestPostFields.add(str);*/
		try{
			SQLCMD.initConnection();
			SQLCMD.createTable(CONTEST_TABLE, contestFields);
			//SQLCMD.createTable("ContestPosts", contestPostFields);
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} finally{
			SQLCMD.closeConnection();
		}
	}
	public static ContestBean grabContest(int contestID)
	{
		ContestBean contest = new ContestBean();
		ResultSet rs;
		try {
			SQLCMD.initConnection();
			rs = SQLCMD.select(CONTEST_TABLE, CONTEST_DB, ""+contestID);
			contest.setContestID(rs.getLong(CONTEST_DB));
			contest.setNumberOfContestants(rs.getLong("numberOfContestants"));
			contest.setRunningTime(rs.getLong("runningTime"));
			contest.setStartTime(rs.getTimestamp("startTime"));
			contest.setEndTime(rs.getTimestamp("endTime"));
			contest.setIsGlobal(rs.getBoolean("isGlobal"));
			contest.setIsLocal(rs.getBoolean("isLocal"));
			contest.setIsDone(rs.getBoolean("isDone"));
<<<<<<< HEAD
			String mt = rs.getString("mediaType");
			if(mt.equals("COMMENT"))
			{
				contest.setMediaType(Media_Type.COMMENT);
			}
			else if(mt.equals("PICTURE"))
			{
				contest.setMediaType(Media_Type.PICTURE);
			}
			else if(mt.equals("VIDEO")) 
			{
				contest.setMediaType(Media_Type.VIDEO);
			}
			/*implemented elsewhere....we need to work this out
=======
			contest.setMediaType(rs.getString("mediaType"));
		
>>>>>>> cc2661f756464ae9e47c24e44594e16db99df7e7
			rs = SQLCMD.select("ContestPosts", "contestID", ""+contestID);
			if (!rs.isBeforeFirst()) 
			{    
				 System.out.println("No data"); 
			}
			else
			{
				ArrayList<PostBean> list = new ArrayList<PostBean>();
				int numberOfContestants = 0;
				if (!rs.next())
				{
					list.add(PostDAO.post(rs.getLong("postID")));
					numberOfContestants++;
				}
				contest.setPosts(list);
				contest.setNumberOfContestants(numberOfContestants);
			}
			*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			SQLCMD.closeConnection();
		}
		
		return contest;
	}
	public static void setContest(ContestBean contest)
	{
		ResultSet rs;
		try {
			SQLCMD.initConnection();
			rs = SQLCMD.select(CONTEST_TABLE, CONTEST_DB, ""+contest.contestID());
			rs.updateLong("numberOfContestants", contest.numberOfContestants());
			rs.updateBoolean("isDone", contest.isDone());
			rs.updateRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			SQLCMD.closeConnection();
		}
	}
	/*implemented elsewhere we need to update this
	public static void addPost(long contestID, long postID)
	{
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		fields.add("contestID");
		fields.add("postID");
		values.add(""+contestID);
		values.add(""+postID);
		try {
			SQLCMD.initConnection();
			SQLCMD.insertField("ContestPosts", fields, values);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			SQLCMD.closeConnection();
		}
	}
	*/
}
