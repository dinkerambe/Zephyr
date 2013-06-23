//
//  PostBean.java
//  Zephyr
//
//  Created by Will Sawyer on 6/10/13.
//  Copyright (c) 2013 Zephyr. All rights reserved.
//

import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.*;

enum Media_Type
{
	COMMENT, PICTURE, VIDEO;
}
public class PostBean {
 
	private long postID, userID, votes, contestID, votedForPostID;
	private Timestamp timestamp;
	private boolean isVideo, isText, isImage, isWinner;
	private ArrayList<Long> voters = new ArrayList<Long>();
	private Blob picture;
	private String comment, videoURL; //???
	private Media_Type mediaType;
	
	//Setters
	public void setPostID(long postID){this.postID = postID;}
	public void setUserID(long userID){this.userID = userID;}
	public void setContestID(long contestID){this.contestID = contestID;}
	public void setVotedForPostID(long votedForPostID){this.votedForPostID = votedForPostID;}
	public void setVotes(long votes){this.votes = votes;}
	public void setComment(String comment){this.comment = comment;}
	public void setPicture(Blob picture){this.picture = picture;}
	public void setVideoURL(String url){this.videoURL = url;}
	public void setMediaType(Media_Type mt){this.mediaType = mt;}
	public void setTimestamp(Timestamp t){this.timestamp = t;}
	public void setIsWinner(boolean b){this.isWinner = b;}
	public void setVoters(ArrayList<Long> list)
	{
		for(int j=0; j<list.size(); j++)
		{
			this.voters.add(list.get(j));
		}
	}
	
	//Getters
	public long postID(){return this.postID;}
	public long userID(){return this.userID;}
	public long contestID(){return this.contestID;}
	public long votedForPostID(){return this.votedForPostID;}
	public long votes(){return this.votes;}
	public String comment(){return this.comment;}
	public Blob picture(){return this.picture;}
	public String videoURL(){return this.videoURL;}
	public Media_Type mediaType(){return this.mediaType;}
	public Timestamp timestamp(){return this.timestamp;}
	public boolean isWinner(){return this.isWinner;}
	public ArrayList<Long> voters(){return this.voters;}
	
	
}