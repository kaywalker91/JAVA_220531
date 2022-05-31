package study0531;

import java.sql.*;

public class Round27_Ex03 {
	public static void main(String[] args) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println("드라이브가 있습니당~! ^_^v");
		} catch (ClassNotFoundException ee) {
			System.out.println("드라이브 없음!!");
		}
		
		Connection conn = null;
		String url = "jdbc:mysql://127.0.0.1:3306/study?useUnicode=true&characterEncoding=utf8";
		String user = "root";// ID
		String password = "qwer";// 비밀번호
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("연결되었습니다.");
		} catch (SQLException ee) {
			System.err.println("연결객체 생성실패!!");
		}
		
		String pquery = "insert into test values (null, ?,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(pquery);
			pstmt.setString(1, "테스터");
			pstmt.setString(2, "11111");
			pstmt.setString(3, "선화동");
			pstmt.executeUpdate();
			System.out.println("실행성공");
		} catch (SQLException ee) {
			System.err.println("Query 실행 클래스 생성 에러~!! : " + ee.toString());
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
			System.err.println("실행결과 획득실패!!");
		}
		// 5. Close 작업
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
			System.err.println("닫기 실패~!!");
		}
	}
}
