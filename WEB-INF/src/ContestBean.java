//
//  ContestBean.java
//  Zephyr
//
//  Created by Will Sawyer on 6/10/13.
//  Copyright (c) 2013 Zephyr. All rights reserved.
//

import java.sql.Timestamp;
import java.util.ArrayList;


public class ContestBean {

	private long contestID, numberOfContestants, creatorID, runningTime;
	private String title, description, rules;
	private Media_Type mediaType;
	private boolean isGlobal, isLocal, isDone;
	private Timestamp startTime, endTime;
	private ArrayList<PostBean> posts = new ArrayList<PostBean>();
	
	public void setContestID(long contestID){this.contestID = contestID;}
	public void setCreatorID(long creatorID){this.creatorID = creatorID;}
	public void setNumberOfContestants(long number){this.numberOfContestants = number;}
	public void setRunningTime(long days){this.runningTime = days;}
	public void setTitle(String title){this.title = title;}
	public void setDescription(String description){this.description = description;}
	public void setRules(String rules){this.rules = rules;}
	public void setStartTime(Timestamp t){this.startTime = t;}
	public void setEndTime(Timestamp t){this.endTime = t;}
	public void setIsGlobal(boolean b){this.isGlobal = b;}
	public void setIsLocal(boolean b){this.isLocal = b;}
	public void setIsDone(boolean b){this.isDone = b;}
	public void setMediaType(Media_Type mt){this.mediaType = mt;}
	public void setPosts(ArrayList<PostBean> list)
	{
		for(int j=0; j<list.size(); j++)
		{
			this.posts.add(list.get(j));
		}
	}
	
	
	//Getters
	public long creatorID(){return this.creatorID;}
	public long contestID(){return this.contestID;}
	public long numberOfContestants(){return this.numberOfContestants;}
	public long runningTime(){return this.runningTime;}
	public String title(){return this.title;}
	public String description(){return this.description;}
	public String rules(){return this.rules;}
	public Timestamp startTime(){return this.startTime;}
	public Timestamp endTime(){return this.endTime;}
	public boolean isGlobal(){return this.isGlobal;}
	public boolean isLocal(){return this.isLocal;}
	public boolean isDone(){return this.isDone;}
	public Media_Type mediaType(){return this.mediaType;}
	public ArrayList<PostBean> posts(){return this.posts;}
	
	public void findWinner()
	{
		PostBean winningPost = this.posts.get(0);
		for(int j=1; j < this.posts.size(); j++)
		{
			if(this.posts.get(j).votes() >= winningPost.votes())
			{
				//If same amount of votes, the earlier post wins. CAN BE CHANGED
				if(this.posts.get(j).votes() == winningPost.votes() && this.posts.get(j).timestamp().getTime() < winningPost.timestamp().getTime())
				{
					winningPost = this.posts.get(j);
				}
				winningPost = this.posts.get(j);
			}
		}
		winningPost.setIsWinner(true);
		PostDAO.setPost(winningPost);
	}
}
