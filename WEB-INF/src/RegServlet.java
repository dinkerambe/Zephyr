import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
* Servlet implementation class LoginServlet
*/
public class RegServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, java.io.IOException {

		try
		{	    

			//Temp Testing for inits
			UserDAO.initUserTable();
			UserInfoDAO.initUserInfoTable();
			UserFriendDAO.initTable();
			ContestDAO.initTable();
			PostDAO.initTable();

			PostOwnerDAO.initTable();
			PostVotesDAO.initTable();



			UserBean user = new UserBean();
			user.setFirstName(request.getParameter("reg-name"));
			user.setLastName(request.getParameter("reg-name"));
			user.setEmail(request.getParameter("reg-email"));
			user.setPassword(request.getParameter("reg-password"));

			user = UserDAO.registerUser(user);

			if (user.isValid())
			{
				response.sendRedirect("./Secured/UserData.jsp");   		
			}

			else 
				response.sendRedirect("./Secured/login_error.jsp"); //error page 
		} 


		catch (Throwable theException) 	    
		{
			System.out.println(theException); 
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, java.io.IOException {
	doGet(request, response);
	}
}

