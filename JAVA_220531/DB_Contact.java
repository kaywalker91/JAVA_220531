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
	//��� ���� ���� 
	Connection conn = null;
	String url = "jdbc:mysql://127.0.0.1:3306/study?useUnicode=true&characterEncoding=utf8";
	String id = "root";// ID
	String pass = "qwer";// ��й�ȣ	
	
	//��� ����Ÿ ���Կ�
	PreparedStatement pstmt = null;		
	String query = "insert into friends values (null, ?, ?, ?)";
	
	//��� ��ȸ��
	Statement stmt = null;
	ResultSet rs = null;
	
	String result="";
	private Dimension dimen, dimen1;
	private int xpos, ypos;
	
	Button btnAdd = new Button("ģ���߰�");	
	Label lbTitle = new Label("ģ���߰����α׷�", Label.LEFT);	
	Label lbName = new Label("�̸� : ", Label.LEFT);
	Label lbAge = new Label("����  :", Label.LEFT);
	Label lbAddr = new Label("�ּ� : ", Label.LEFT);
	TextField tfName  = new TextField(20);	
	TextField tfAge  = new TextField(20);
	TextField tfAddr  = new TextField(20);
	private TextArea ta = new TextArea();
	
	
	public FriendsDb() {
		super("ģ���߰����α׷�");
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
		// �ۼַ�Ʈ���  : ���� ��ǥ �Է¹��.
		this.setLayout(null);

		
		Font font20 = new Font("SansSerif", Font.BOLD, 20);
		Font font15 = new Font("SansSerif", Font.BOLD, 15);
		Font font10 = new Font("SansSerif", Font.BOLD, 10);
		
		//Ÿ��Ʋ
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
			
			//�Էµ� ���� ������ ����.
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
			
			
			
			
			//���
			//���ڿ��� �����Ǽ� �����ֱ�	
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
			
			
			//�Է�â ����ϰ� �ʱ�ȭ
			tfName.setText("");
			tfAge.setText("");
			tfAddr.setText("");
		}
		
	}
	void dataLoad()
	{
		//��� �����ؼ� �ʱ⿡ ����Ÿ �ε��ϱ�.
		

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


	


	
	
