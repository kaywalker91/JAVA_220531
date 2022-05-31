package study0531;

import java.sql.*;

public class Round27_Ex02 
{
	public static void main(String[] args) 
	{
		try 
		{
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException ee) 
		{
			System.exit(0);
		}
		Connection conn = null;
		String url = "jdbc:mysql://127.0.0.1:3306/study?useUnicode=true&characterEncoding=utf8";
		String id = "root";
		String pass = "qwer";
		Statement stmt = null;
		ResultSet rs = null;
		String query = "select * from test";
		try 
		{
			conn = DriverManager.getConnection(url, id, pass);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) 
			{
				System.out.println(rs.getInt(1) + " : " + rs.getString(2) + " : " + rs.getString(3) + " : " + rs.getString(4));
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException ee)
		{
			System.err.println("error = " + ee.toString());
		}
	}
}

