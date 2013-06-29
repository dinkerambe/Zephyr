import java.util.*;
public class Tester{

	public static void main(String[] args){
		UserDAO.initTable();
		UserInfoDAO.initTable();
		UserFriendDAO.initTable();
		ContestDAO.initTable();
		PostDAO.initTable();

		PostOwnerDAO.initTable();
		PostVotesDAO.initTable();
	}	
}
