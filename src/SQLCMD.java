import java.text.*;
import java.util.*;
import java.sql.*;

/*
All methods are static and to be used as SQLCMD.method()

initConnection at the start
carry out any of the methods embedded in a try catch statement with Exception
closeConnection in the finally{} block after try, catch

String fields should have single quotes around each field:
	String fieldExample = " 'field1', 'field2', 'field3' ";
String values should have single quotes around each value:
	String valueExample = " 'value1', 'value2', 'value3' ";

Table name need not be in single quotes:
	valid: String table = " 'myTable' ";
	valid: String table = "myTable";
*/

public class SQLCMD{

	static Connection currentCon = null;
	static ResultSet rs = null;
	static Statement stmt = null;
	
	/*
	*Must impliment before any other method
	*/
	public static void initConnection(){
		try{
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
		}catch(SQLException ex){
			System.out.println("SQL INIT EXCEPTION");
		}
	}

	/*
	*Must impliment after use of SQLCMD is finished
	*/
	public static void closeConnection(){
		if(rs != null){
			try{
				rs.close();
			}catch(Exception e){
				System.out.println("SQL ResultSet close failure");
			}
			rs = null;
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				System.out.println("SQL Statement close failure");
			}
			stmt = null;
		}

		if (currentCon != null) {
			try {
				currentCon.close();
			} catch (Exception e) {
				System.out.println("SQL Connection close failure");
			}
			currentCon = null;
		}


	}

	/*
	fields and values must be in single quotes separated by commas
	*/
	public static void insertField(String table, String fields, String values){
		try{
			String insert_statement 
				= "INSERT INTO " + table 
				+ " (" + fields +") VALUES (" + values + ");";
			stmt.execute(insert_statement);
		} catch(Exception ex){
			System.out.println("Insert failed: SQL insertField input error(s)");
		}
	}
	
	/*
	*multiple insertion with List<String> 
	*Do not have single quotes around values
	*/
	public static void insertField(String table, List<String> fields, List<String> values){
		try{
			String insert_statement 
				= "INSERT INTO " + table + " (";
			for(int i =0, n = fields.size(); i < n; i++){
					if(i==n-1)
						insert_statement += "'" + fields.get(i) + "'";
					else
						insert_statement += "'" + fields.get(i) + "', ";

					
			}
			
			insert_statement += ") VALUES ("; 
			
			for(int i =0, n = values.size(); i < n; i++){
				if(i==n-1)
					insert_statement += "'" + values.get(i) + "');";
				else
					insert_statement += "'" + values.get(i) + "', ";

				
			}
			
			stmt.execute(insert_statement);
		} catch(Exception ex){
			System.out.println("Insert failed: SQL insertField input error(s)");
		}
	}

	/*
	*fields and values must be in single quotes separated by commas
	*/
	public static ResultSet select(String table, String field, String value){
		try{
			String queryStatement 
				= "SELECT * FROM " + table + " WHERE " + field + " = " + value + ";";
			rs = stmt.executeQuery(queryStatement);
		} catch(Exception ex){
			System.out.println("Search failed: SQL Select Single input error(s)");
		}
		return rs;
	}
	public static ResultSet select(String column, String table, String field, String value){
		try{
			String queryStatement 
				= "SELECT "+ column + " FROM " + table + " WHERE " + field + " = " + value + ";";
			rs = stmt.executeQuery(queryStatement);
		} catch(Exception ex){
			System.out.println("Search failed: SQL Select Single input error(s)");
		}
		return rs;
	}


	/*
	*Multiple fields and values stored as List<String>
	*Do not have single quotes around the strings
	*/
	public static ResultSet select(String table, List<String> fields, List<String> values){
		try{
			String queryStatement 
				= "SELECT * FROM " + table + " WHERE ";
			for(int i =0, n = fields.size(); i < n; i++){
					if(i==n-1)
						queryStatement += fields.get(i) 
						+ " = " + "'" +  values.get(i) + "';";
					else
						queryStatement += fields.get(i) 
						+ " = " + "'" +  values.get(i) + "' AND ";

					
			}
			
			rs = stmt.executeQuery(queryStatement);
		} catch(Exception ex){
			System.out.println("Search failed: SQL Select Mult input error(s)");
		}
		return rs;
	}

	/*
	*creates new table
	*Uses List<String>
	*Do not use single quotes around strings
	*/
	public static void createTable(String table, List<String> fields){
		try{
			String table_statement 
				= "CREATE TABLE " + table  + " ( ";
			for(int i =0, n = fields.size(); i < n; i++){
					if(i==n-1)
						table_statement += fields.get(i) + " );";
					else
						table_statement += fields.get(i) + ", ";
			}
			
			stmt.execute(table_statement);
		} catch(Exception ex){
			System.out.println("Table init failed: SQL createTable input error(s)");
		}

	}

	/*
	*Dropped = "DATABASE" or "TABLE"
	*Deletes the object
	*/
	public static void drop(String dropped, String name){
		try{
			String dropStatement = "DROP " + dropped + " IF EXISTS " + name;
			stmt.execute(dropStatement);
		}catch(Exception ex){
			System.out.println("Drop failed: SQL drop input error");
		}
	}
		

}
