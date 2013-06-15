package registration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, java.io.IOException {

		try
		{	    

			UserBean user = new UserBean();
			user.setEmail(request.getParameter("reg-email"));
			user.setPassword(request.getParameter("reg-password"));

			user = UserDAO.login(user);

			if (user.isValid())
			{

			}

			else 
				response.sendRedirect("login_error.jsp"); //error page 
		} 


		catch (Throwable theException) 	    
		{
			System.out.println(theException); 
		}
	}
}
