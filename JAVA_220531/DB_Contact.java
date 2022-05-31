package study0531;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Contact {

	public static void main(String[] args) {
		FriendsDb f = new FriendsDb();
	}
}

class FriendsDb extends Frame implements ActionListener {
	//디비 접속 정보 
	Connection conn = null;
	String url = "jdbc:mysql://127.0.0.1:3306/study?useUnicode=true&characterEncoding=utf8";
	String id = "root";// ID
	String pass = "qwer";// 비밀번호	
	
	//디비 데이타 삽입용
	PreparedStatement pstmt = null;		
	String query = "insert into friends values (null, ?, ?, ?)";
	
	//디비 조회용
	Statement stmt = null;
	ResultSet rs = null;
	
	String result="";
	private Dimension dimen, dimen1;
	private int xpos, ypos;
	
	Button btnAdd = new Button("친구추가");	
	Label lbTitle = new Label("친구추가프로그램", Label.LEFT);	
	Label lbName = new Label("이름 : ", Label.LEFT);
	Label lbAge = new Label("나이  :", Label.LEFT);
	Label lbAddr = new Label("주소 : ", Label.LEFT);
	TextField tfName  = new TextField(20);	
	TextField tfAge  = new TextField(20);
	TextField tfAddr  = new TextField(20);
	private TextArea ta = new TextArea();
	
	
	public FriendsDb() {
		super("친구추가프로그램");
		this.init();
		this.start();
		this.dataLoad();
		this.setSize(420, 550);	
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = this.getSize();
		xpos = (int) (dimen.getWidth() / 2 - dimen1.getWidth() / 2);
		ypos = (int) (dimen.getHeight() / 2 - dimen1.getHeight() / 2);
		this.setLocation(xpos, ypos);
		this.setVisible(true);
		
		
		
	}

	public void init() {
		// 앱솔루트방식  : 직접 좌표 입력방식.
		this.setLayout(null);

		
		Font font20 = new Font("SansSerif", Font.BOLD, 20);
		Font font15 = new Font("SansSerif", Font.BOLD, 15);
		Font font10 = new Font("SansSerif", Font.BOLD, 10);
		
		//타이틀
		this.add(lbTitle);
		lbTitle.setBounds(100, 50, 300, 30);		
		lbTitle.setFont(font20);
		
		this.add(lbName);
		lbName.setBounds(100, 100, 50, 30);
		lbName.setFont(font15);
		
		this.add(tfName);
		tfName.setBounds(170, 100, 150, 30);
		tfName.setFont(font15);
		
		this.add(lbAge);
		lbAge.setBounds(100, 150, 50, 30);
		lbAge.setFont(font15);
		
		this.add(tfAge);
		tfAge.setBounds(170, 150, 150, 30);
		tfAge.setFont(font15);
		
		this.add(lbAddr);
		lbAddr.setBounds(100, 200, 50, 30);
		lbAddr.setFont(font15);
		
		this.add(tfAddr);
		tfAddr.setBounds(170, 200, 150, 30);
		tfAddr.setFont(font15);
		
		this.add(btnAdd);
		btnAdd.setBounds(140, 250, 150, 30);
		btnAdd.setFont(font15);
		

		
		
		this.add(ta);
		ta.setBounds(100, 300, 250, 200);
		ta.setFont(font15);
		
	}

	public void start() {
		
		
		 btnAdd.addActionListener(this); 
		
		
		
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		result="";
		
		if(e.getSource()==btnAdd)
		{
			
			//입력된 값을 변수에 저장.
			String name = tfName.getText();
			String age = tfAge.getText();
			String addr = tfAddr.getText();
			
			
			
			try {
				conn = DriverManager.getConnection(url, id, pass);			
				pstmt = conn.prepareStatement(query);			
				pstmt.setString(1, name);
				pstmt.setString(2, age);
				pstmt.setString(3, addr);
				pstmt.executeUpdate();
				
			} catch (SQLException ee) {
				System.err.println("error = " + ee.toString());
				System.exit(0);
			}		
			
			
			
			
			//출력
			//문자열에 누적되서 보여주기	
			Statement stmt = null;
			ResultSet rs = null;
			try {
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select * from friends");
				while (rs.next()) {
					result += rs.getString("name") +"/"+ rs.getString("age") + "/"+rs.getString("addr")+"\n";	
				}
				ta.setText(result);
				rs.close();
			} catch (SQLException error) {
				System.err.println("error = " + error.toString());			
			}	
			
			
			//입력창 깔끔하게 초기화
			tfName.setText("");
			tfAge.setText("");
			tfAddr.setText("");
		}
		
	}
	void dataLoad()
	{
		//디비에 접속해서 초기에 데이타 로드하기.
		

		try {
			conn = DriverManager.getConnection(url, id, pass);			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from friends");
			while (rs.next()) {
				result += rs.getString("name") +"/"+ rs.getString("age") + "/"+rs.getString("addr")+"\n";	
			}
			ta.setText(result);
			rs.close();
		} catch (SQLException error) {
			System.err.println("error = " + error.toString());			
		}	
	}
	
}


	


	
	
