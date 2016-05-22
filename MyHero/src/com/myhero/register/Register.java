package com.myhero.register;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.myhero.login.LoginDAO;

//회원가입 화면
public class Register extends JFrame implements ActionListener {
	// id, 비밀번호, 비밀번호확인, 캐릭터이름, 중복확인, 에러메시지
	JLabel lId, lPass1, lPass2, lcharacterName, lDuplicate, lError, lUserName;
	JPasswordField Pass1, Pass2;// 비밀번호 띄우기
	JTextField id, characterName, UserName;// 아이디, 캐릭터 이름 설정
	JButton bDupli, bOk, bCancel;// 중복버튼, 확인버튼, 취소버튼

	public Register() {
		Container c = this.getContentPane();
		setTitle("회원가입창");
		setSize(400, 330);
		setLayout(null);
		c.setBackground(new Color(0, 112, 192));
		createUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void createUI() {
		lId = new JLabel("ID :");
		lId.setForeground(Color.white);
		lId.setLocation(30, 20);
		lId.setSize(150, 50);
		add(lId);

		id = new JTextField();
		id.setLocation(100, 30);
		id.setSize(100, 25);
		add(id);

		bDupli = new JButton("중복확인");
		bDupli.setBackground(Color.lightGray);
		bDupli.setLocation(210, 30);
		bDupli.setSize(90, 30);
		bDupli.addActionListener(this);
		add(bDupli);

		lPass1 = new JLabel("PW :");
		lPass1.setForeground(Color.white);
		lPass1.setLocation(30, 50);
		lPass1.setSize(150, 50);
		add(lPass1);

		Pass1 = new JPasswordField();
		Pass1.setLocation(100, 60);
		Pass1.setSize(100, 25);
		add(Pass1);

		lPass2 = new JLabel("PW확인 :");
		lPass2.setForeground(Color.white);
		lPass2.setLocation(30, 80);
		lPass2.setSize(150, 50);
		add(lPass2);

		Pass2 = new JPasswordField();
		Pass2.setLocation(100, 90);
		Pass2.setSize(100, 25);
		add(Pass2);

		lUserName = new JLabel("사용자 이름");
		lUserName.setForeground(Color.white);
		lUserName.setLocation(30, 140);
		lUserName.setSize(150, 50);
		add(lUserName);

		UserName = new JTextField();
		UserName.setLocation(100, 150);
		UserName.setSize(100, 25);
		add(UserName);

		lcharacterName = new JLabel("캐릭터 이름 :");
		lcharacterName.setForeground(Color.white);
		lcharacterName.setLocation(30, 170);
		lcharacterName.setSize(150, 50);
		add(lcharacterName);

		characterName = new JTextField();
		characterName.setLocation(100, 180);
		characterName.setSize(100, 25);
		add(characterName);

		bOk = new JButton("확인");
		bOk.setBackground(Color.lightGray);
		bOk.setLocation(100, 220);
		bOk.setSize(90, 30);
		bOk.addActionListener((ActionListener) this);
		add(bOk);

		bCancel = new JButton("취소");
		bCancel.setBackground(Color.lightGray);
		bCancel.setLocation(200, 220);
		bCancel.setSize(90, 30);
		bCancel.addActionListener((ActionListener) this);
		add(bCancel);

		lDuplicate = new JLabel("");
		lDuplicate.setForeground(Color.white);
		lDuplicate.setLocation(210, 50);
		lDuplicate.setSize(150, 50);
		add(lDuplicate);

		lError = new JLabel("");
		lError.setForeground(Color.white);
		lError.setLocation(200, 150);
		lError.setSize(150, 50);
		add(lError);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		LoginDAO loginDAO = new LoginDAO();
		if (s == "확인") {
			try {// 아이디 중복 아닐 경우
				if (loginDAO.register(id.getText(), Pass1.getText(), UserName.getText()) == null) {
					dispose();
					new Character(id.getText(), characterName.getText());
				}

				else {// 아이디 중복일경우
					lError.setText(loginDAO.register(id.getText(), Pass1.getText(), UserName.getText()));
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (s.equals("취소")) {
			// System.exit(0);
			dispose();
		} else if (s.equals("중복확인")) {
			try {// 아이디 중복일 경우
				if (loginDAO.duplicate(id.getText())) {
					lDuplicate.setText("ID중복입니다.");
				} else {// 아이디 중복 아닐 경우
					lDuplicate.setText("사용가능한 ID입니다.");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}
