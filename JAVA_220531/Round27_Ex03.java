package study0531;

import java.sql.*;

public class Round27_Ex03 {
	public static void main(String[] args) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println("����̺갡 �ֽ��ϴ�~! ^_^v");
		} catch (ClassNotFoundException ee) {
			System.out.println("����̺� ����!!");
		}
		
		Connection conn = null;
		String url = "jdbc:mysql://127.0.0.1:3306/study?useUnicode=true&characterEncoding=utf8";
		String user = "root";// ID
		String password = "qwer";// ��й�ȣ
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("����Ǿ����ϴ�.");
		} catch (SQLException ee) {
			System.err.println("���ᰴü ��������!!");
		}
		
		String pquery = "insert into test values (null, ?,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(pquery);
			pstmt.setString(1, "�׽���");
			pstmt.setString(2, "11111");
			pstmt.setString(3, "��ȭ��");
			pstmt.executeUpdate();
			System.out.println("���༺��");
		} catch (SQLException ee) {
			System.err.println("Query ���� Ŭ���� ���� ����~!! : " + ee.toString());
		}
		
		ResultSet rs = null;
		Statement stmt = null;
		String query = "select * from test";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int idx = rs.getInt("idx");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String addr = rs.getString("addr");
				System.out.println(idx + " : " + name + " : " + hp + " : " + addr);
			}
		} catch (SQLException ee) {
			System.err.println("������ ȹ�����!!");
		}
		// 5. Close �۾�
		try {
			rs.close();
			pstmt.close();
			stmt.close();
			if (conn != null) {
				if (!conn.isClosed()) {
					conn.close();
				}
				conn = null;
			}
		} catch (SQLException ee) {
			System.err.println("�ݱ� ����~!!");
		}
	}
}
