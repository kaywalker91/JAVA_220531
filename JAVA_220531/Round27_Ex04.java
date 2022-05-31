package study0531;

import java.sql.*;
import java.io.*;

public class Round27_Ex04 {
	public static void main(String[] args) throws IOException {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException ee) {
			System.err.println("error = " + ee.getMessage());
			System.exit(0);
		}
		Connection conn = null;
		String url = "jdbc:mysql://127.0.0.1:3306/study?useUnicode=true&characterEncoding=utf8";
		String id = "root";
		String pass = "qwer";
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "insert into test values (null, ?, ?, ?)";
		try {
			conn = DriverManager.getConnection(url, id, pass);
			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(query);
		} catch (SQLException ee) {
			System.err.println("error = " + ee.toString());
			System.exit(0);
		}
		// DB �����۾� ��~!!
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.print("1.�Է�  2.���  3.���� = ");
			int x = System.in.read() - 48;
			System.in.read();
			System.in.read();
			if (x == 1) {
				System.out.print("�� �� : ");
				String name = in.readLine();
				System.out.print("���� : ");
				String hp = in.readLine();
				System.out.print("�ּ� : ");
				String addr = in.readLine();
				try {
					pstmt.setString(1, name);
					pstmt.setString(2, hp);
					pstmt.setString(3, addr);
					pstmt.executeUpdate();
				} catch (SQLException ee) {
				}
			} else if (x == 2) {
				try {
					rs = stmt.executeQuery("select * from test");
					while (rs.next()) 
					{
					System.out.println(rs.getInt("idx") + " : " + rs.getString("name") + ", " + rs.getString("hp") + ", " + rs.getString("addr"));
					}
					rs.close();
				} catch (SQLException ee) {
				}
			} else if (x == 3)
				break;
			else
				System.err.println("�߸� �Է�!!");
		}
		// DB ����!!
		try {
			conn.close();
		} catch (Exception ee) {
		}
	}
}

