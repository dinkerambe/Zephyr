import java.sql.*;
import java.util.*;
//import java.net.URL;
//import java.net.URLClassLoader;
//import java.io.File;

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

			//File root = new File("/usr/share/tomcat7/lib/mysql-connector-java-5.1.25/src/");
			//URLClassLoader classLoader  = null;
			//try{
			//classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
			//}catch(Exception ex){System.out.println(ex.getMessage());}
			//Class.forName("org.gjt.mm.mysql.Driver");//, true, classLoader);
			Class.forName("com.mysql.jdbc.Driver");
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
