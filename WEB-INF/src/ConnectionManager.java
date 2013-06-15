import java.sql.*;
import java.util.*;


public class ConnectionManager {

	static Connection con;
	static String url;
	static String dataSource = "//localhost:3306/tomcat_realm";
	static String connectionName = "root";
	static String connectionPassword = "dsa555";


	public static Connection getConnection()
	{

		try
		{
			String url = "jdbc:mysql:" + dataSource; 
			Class.forName("org.gjt.mm.mysql.Driver");

			try
			{            	
				con = DriverManager.getConnection(url, connectionName, connectionPassword);

			}

			catch (SQLException ex)
			{
				ex.printStackTrace();
			}
		}

		catch(ClassNotFoundException e)
		{
			System.out.println(e);
		}

		return con;
	}
}
