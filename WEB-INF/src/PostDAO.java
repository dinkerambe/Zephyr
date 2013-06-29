//
//  PostDAO.java
//  Zephyr
//
//  Created by Will Sawyer on 6/10/13.
//  Copyright (c) 2013 Zephyr. All rights reserved.
//

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PostDAO {
	final static String UID_DB = "user_id";
	final static String POST_DB = "post_id";
	final static String USER_TABLE = "tomcat_users";
	final static String POST_TABLE = "posts";
	final static String CONTEST_TABLE = "contests";
	final static String CONTEST_DB = "contest_id";

	public static void initDatabase()
	{
		//Post Table
		List<String> postFields = new ArrayList<String>();
		String str;
		str = POST_DB + " BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY";
		postFields.add(str);
		str = UID_DB + " BIGINT UNSIGNED NOT NULL REFERENCES " + USER_TABLE + "(" + UID_DB + ")";
		postFields.add(str);
		str = CONTEST_DB + " BIGINT UNSIGNED NOT NULL REFERENCES " + CONTEST_TABLE + "(" + CONTEST_DB + ")";
		postFields.add(str);
		str = "'votes' int(64) NOT NULL";
		postFields.add(str);
		str = "'votedForPostID' int(64) NOT NULL";
		postFields.add(str);
		str = "'mediaType' varchar(255) NOT NULL";
		postFields.add(str);
		str = "'picture' BLOB";
		postFields.add(str);
		str = "'comment' varchar(255)";
		postFields.add(str);
		str = "'videoURL' varchar(255)";
		postFields.add(str);
		str = "'timestamp' timestamp NOT NULL";
		postFields.add(str);
		str = "'isWinner' boolean NOT NULL";
		postFields.add(str);
		
		//Voter List Table

		//Make new Class for this and fix the variable names
	/*	
		List<String> voterFields = new ArrayList<String>();
		str = "'postID' BIGINT NOT NULL PRIMARY KEY";
		postFields.add(str);
		str = "'voterID' BIGINT NOT NULL";
		postFields.add(str);*/
		try{
			SQLCMD.initConnection();
			SQLCMD.createTable(POST_TABLE, postFields);
			//SQLCMD.createTable("Voters", voterFields);
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} finally{
			SQLCMD.closeConnection();
		}
		
	}
	public static PostBean post(long postID)
	{
		PostBean post = new PostBean();
		ResultSet rs;
		try {
			SQLCMD.initConnection();
			rs = SQLCMD.select(POST_TABLE, POST_DB, ""+postID);
			post.setPostID(rs.getLong(POST_DB));
			post.setUserID(rs.getLong(UID_DB));
			post.setContestID(rs.getLong(CONTEST_DB));
			post.setVotes(rs.getLong("votes"));
			post.setVotedForPostID(rs.getLong("votedForPostID"));
			post.setTimestamp(rs.getTimestamp("timestamp"));
			post.setIsWinner(rs.getBoolean("isWinner"));
			String mt = rs.getString("mediaType");
			if(mt.equals("COMMENT"))
			{
				post.setMediaType(mt);
				post.setComment(rs.getString("comment"));
			}
			else if(mt.equals("PICTURE"))
			{
				post.setMediaType(mt);
				post.setPicture(rs.getBlob("picture"));
			}
			else if(mt.equals("VIDEO")) 
			{
				post.setMediaType(mt);
				post.setVideoURL(rs.getString("videoURL"));
			}
	//FIX NEXT LINE
			rs = SQLCMD.select("Voters", "postID", ""+postID);
			if (!rs.isBeforeFirst()) 
			{    
				 System.out.println("No data"); 
			}
			else if(SQLCMD.select(CONTEST_TABLE, CONTEST_DB, ""+post.contestID()).getBoolean("isDone"))
			{
				ArrayList<Long> list = new ArrayList<Long>();
				int votes = 0;
				if (!rs.next())
				{
					list.add(rs.getLong("voterID"));
					votes++;
				}
				post.setVoters(list);
				post.setVotes(votes);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			SQLCMD.closeConnection();
		}
		
		return post;
	}
	public static void setPost(PostBean post)
	{
		ResultSet rs;
		try {
			SQLCMD.initConnection();
			rs = SQLCMD.select(POST_TABLE, POST_DB, ""+post.postID());
			rs.updateLong("votes", post.votes());
			rs.updateLong("votedForPostID", post.votedForPostID());
			rs.updateTimestamp("timestamp", post.timestamp());
			rs.updateBoolean("isWinner", post.isWinner());
			rs.updateRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			SQLCMD.closeConnection();
		}
	}
	

}
