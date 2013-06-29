import java.text.*;
import java.util.*;
import java.sql.*;

public class UserDAO 	
{
	static Connection currentCon = null;
	static ResultSet rs = null;  
	
	final static String USER_TABLE= "tomcat_users";
	final static String F_NAME_DB = "first_name";
	final static String L_NAME_DB = "last_name";
	final static String EMAIL_DB = "email"; 
	final static String PASS_DB = "password";
	final static String UID_DB = "user_id";


	/*
	 *@Param: fully loaded UserBean
	 *@Return: same UserBean provided
	*/
	public static UserBean registerUser(UserBean bean){

		String firstName = bean.getFirstName();
		String lastName = bean.getLastName();
		String email = bean.getEmail();
		String password = bean.getPassword();

		List<String> fields = new ArrayList<String>();	
		List<String> values = new ArrayList<String>();
	
		fields.add(F_NAME_DB);
		fields.add(L_NAME_DB);
		fields.add(EMAIL_DB);
		fields.add(PASS_DB);

		values.add(firstName);
		values.add(lastName);
		values.add(email);
		values.add(password);

		try{
			SQLCMD.initConnection();	
			SQLCMD.insertField(USER_TABLE, fields, values);	
		}catch(Exception e){}
		finally{
			SQLCMD.closeConnection();
		}
		return bean;
		
	}

	/*
	 *@Param: valid email
	 *@Return: fully loaded UserBean
	*/
	public static UserBean grabUser(String emailSearch){
		UserBean result = null;
		try{
			SQLCMD.initConnection();
			rs = SQLCMD.select(USER_TABLE, EMAIL_DB, emailSearch);

			String firstName = rs.getString(F_NAME_DB);
			String lastName = rs.getString(L_NAME_DB);
			String email = rs.getString(EMAIL_DB);
			String password = rs.getString(PASS_DB);
			long user_id = rs.getInt(UID_DB);
			
			result = new UserBean(user_id, email, firstName, lastName, password);

		}catch(Exception e){System.out.println("USERDAO: Grab failed");}
		finally{
			SQLCMD.closeConnection();
		}

		return result;	

	}

	/*
	 *Checks if email is no taken as to make a new user
	 *@Param: email
	 *@Return: True if email not taken
	*/
	public static boolean checkUniqueEmail(String emailSearch){
		boolean result = false;
		try{
			SQLCMD.initConnection();
			rs = SQLCMD.select(USER_TABLE, EMAIL_DB, emailSearch);
			result = !(rs.next());	
		}catch(Exception e){System.out.println("USERDAO: Email Check failed");}
		finally{
			SQLCMD.closeConnection();
		}

		return result;	

	}

	/*
	 *Checks if email is already in database as to grab user correctly
	 *@Param: email
	 *@Return: True if email is in database
	*/
	public static boolean checkValidEmail(String emailSearch){
		return !(checkUniqueEmail(emailSearch));
	}

	/*
	 *Init the User Table
	*/
	public static void initTable(){
		try{
			SQLCMD.initConnection();
			
			List<String> fields = new ArrayList<String>();
			fields.add(UID_DB + 
				" BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY");
			fields.add(EMAIL_DB + " varchar(255) NOT NULL UNIQUE");
			fields.add(F_NAME_DB + " varchar(255) NOT NULL");
			fields.add(L_NAME_DB + " varchar(255) NOT NULL");
			fields.add(PASS_DB + " varchar(255) NOT NULL");

			SQLCMD.createTable(USER_TABLE, fields);
		}catch(Exception e){
			System.out.println("USERDAO: init user table failed");
		}
		finally{
			SQLCMD.closeConnection();
		}
	}

	/*=========================Old Code==============================*/




	public static UserBean register(UserBean bean) {

		//preparing some objects for connection 
		Statement stmt = null;    

		String name = bean.getName();
		//System.out.println(name);
		String email = bean.getEmail();    
		String password = bean.getPassword();   


		String searchQuery =
		"select * from tomcat_users where email='"
		+ email + "'";

		String sqlQuery =
		"INSERT INTO tomcat_users (`name`, `email`, `password`) VALUES ( '" + name + "' ,  '" + email + "' , '" + password + "' );" ;
		String sqlQuery2 =
		"INSERT INTO tomcat_users_roles (email, role_name) VALUES ('" + email + "', 'user');";


		try 
		{
			//connect to DB 
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);	        
			boolean more = rs.next();

			// if user exists set the isValid variable to false
			if (more) 
			{
				bean.setValid(false);
			} 

			//if user does not exist set the isValid variable to true
			else if (!more) 
			{
				bean.setValid(true);
				stmt=currentCon.createStatement();
				stmt.executeUpdate(sqlQuery);
				stmt=currentCon.createStatement();
				stmt.executeUpdate(sqlQuery2);

			}
		} 

		catch (Exception ex) 
		{
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		} 

		//some exception handling
		finally 
		{
			if (rs != null)	{
				try {
					rs.close();
				} catch (Exception e) {}
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {}
				stmt = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}

		return bean;

	}	

	public static void initDatabase(){
		try{
			currentCon =ConnectionManager.getConnection();
			currentCon.createStatement().execute("CREATE TABLE `tomcat_users` ("
			+"`id` int(64) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
			+"`name` varchar(255) NOT NULL,"
			+"`email` varchar(255) NOT NULL UNIQUE,"
			+"`password` varchar(255) NOT NULL"
			+")");
		}catch(Exception ex){System.out.println("Login failure: " + ex);}
		finally{
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}
	}
}


/*conn.createStatement().execute("CREATE TABLE `user_name` ("
+"`id` int(64) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
+"`name` varchar(255) NOT NULL,"
+"`email` varchar(255) NOT NULL UNIQUE,"
+"`password` varchar(255) NOT NULL,"
+"UNIQUE (`email`),"
+"FULLTEXT(`email`, `password`)) ENGINE=MYISAM"); //Create the table
}catch(SQLException ex){}
conn.createStatement().execute("INSERT INTO `user_name` ('name', 'email', 'password') VALUES ( '" + name + "' ,  '" + email + "' , '" + password + "' )");

<Realm className="org.apache.catalina.realm.JDBCRealm"
driverName="org.gjt.mm.mysql.Driver" 
connectionURL="jdbc:mysql://localhost:3306/tomcat_realm"
connectionName = "realm_access" connectionPassword="realmpass"
userTable="tomcat_users"
userNameCol="user_name" userCredCol="password"
userRoleTable="tomcat_users_roles" roleNameCol="role_name"/>
/>

/*conn.createStatement().execute("CREATE TABLE `tomcat_users` ("
+"`id` int(64) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
+"`name` varchar(255) NOT NULL,"
+"`email` varchar(255) NOT NULL UNIQUE,"
+"`password` varchar(255) NOT NULL"
+")) ; //Create the table
}catch(SQLException ex){}
conn.createStatement().execute("INSERT INTO `tomcat_users` ('name', 'email', 'password') VALUES ( '" + name + "' ,  '" + email + "' , '" + password + "' )");



*/


