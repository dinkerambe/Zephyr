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
	
	public static void initDatabase()
	{
		//Post Table
		List<String> postFields = new ArrayList<String>();
		String str;
		str = "'postID' BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY";
		postFields.add(str);
		str = "'userID' BIGINT NOT NULL";
		postFields.add(str);
		str = "'contestID' BIGINT  NOT NULL";
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
		List<String> voterFields = new ArrayList<String>();
		str = "'postID' BIGINT NOT NULL PRIMARY KEY";
		postFields.add(str);
		str = "'voterID' BIGINT NOT NULL";
		postFields.add(str);
		try{
			SQLCMD.initConnection();
			SQLCMD.createTable("Post", postFields);
			SQLCMD.createTable("Voters", voterFields);
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
			rs = SQLCMD.select("Post", "postID", ""+postID);
			post.setPostID(rs.getLong("postID"));
			post.setUserID(rs.getLong("userID"));
			post.setContestID(rs.getLong("contestID"));
			post.setVotes(rs.getLong("votes"));
			post.setVotedForPostID(rs.getLong("votedForPostID"));
			post.setTimestamp(rs.getTimestamp("timestamp"));
			post.setIsWinner(rs.getBoolean("isWinner"));
			String mt = rs.getString("mediaType");
			if(mt.equals("COMMENT"))
			{
				post.setMediaType(Media_Type.COMMENT);
				post.setComment(rs.getString("comment"));
			}
			else if(mt.equals("PICTURE"))
			{
				post.setMediaType(Media_Type.PICTURE);
				post.setPicture(rs.getBlob("picture"));
			}
			else if(mt.equals("VIDEO")) 
			{
				post.setMediaType(Media_Type.VIDEO);
				post.setVideoURL(rs.getString("videoURL"));
			}
			rs = SQLCMD.select("Voters", "postID", ""+postID);
			if (!rs.isBeforeFirst()) 
			{    
				 System.out.println("No data"); 
			}
			else if(SQLCMD.select("Contest", "contestID", ""+post.contestID()).getBoolean("isDone"))
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
			rs = SQLCMD.select("Post", "postID", ""+post.postID());
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
	public static void addVoter(long postID, long voterID)
	{
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		fields.add("postID");
		fields.add("voterID");
		values.add(""+postID);
		values.add(""+voterID);
		try {
			SQLCMD.initConnection();
			SQLCMD.insertField("Voters", fields, values);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			SQLCMD.closeConnection();
		}
	}

}
