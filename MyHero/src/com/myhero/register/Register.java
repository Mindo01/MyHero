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

//ȸ������ ȭ��
public class Register extends JFrame implements ActionListener {
	// id, ��й�ȣ, ��й�ȣȮ��, ĳ�����̸�, �ߺ�Ȯ��, �����޽���
	JLabel lId, lPass1, lPass2, lcharacterName, lDuplicate, lError, lUserName;
	JPasswordField Pass1, Pass2;// ��й�ȣ ����
	JTextField id, characterName, UserName;// ���̵�, ĳ���� �̸� ����
	JButton bDupli, bOk, bCancel;// �ߺ���ư, Ȯ�ι�ư, ��ҹ�ư

	public Register() {
		Container c = this.getContentPane();
		setTitle("ȸ������â");
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

		bDupli = new JButton("�ߺ�Ȯ��");
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

		lPass2 = new JLabel("PWȮ�� :");
		lPass2.setForeground(Color.white);
		lPass2.setLocation(30, 80);
		lPass2.setSize(150, 50);
		add(lPass2);

		Pass2 = new JPasswordField();
		Pass2.setLocation(100, 90);
		Pass2.setSize(100, 25);
		add(Pass2);

		lUserName = new JLabel("����� �̸�");
		lUserName.setForeground(Color.white);
		lUserName.setLocation(30, 140);
		lUserName.setSize(150, 50);
		add(lUserName);

		UserName = new JTextField();
		UserName.setLocation(100, 150);
		UserName.setSize(100, 25);
		add(UserName);

		lcharacterName = new JLabel("ĳ���� �̸� :");
		lcharacterName.setForeground(Color.white);
		lcharacterName.setLocation(30, 170);
		lcharacterName.setSize(150, 50);
		add(lcharacterName);

		characterName = new JTextField();
		characterName.setLocation(100, 180);
		characterName.setSize(100, 25);
		add(characterName);

		bOk = new JButton("Ȯ��");
		bOk.setBackground(Color.lightGray);
		bOk.setLocation(100, 220);
		bOk.setSize(90, 30);
		bOk.addActionListener((ActionListener) this);
		add(bOk);

		bCancel = new JButton("���");
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
		if (s == "Ȯ��") {
			try {// ���̵� �ߺ� �ƴ� ���
				if (loginDAO.register(id.getText(), Pass1.getText(), UserName.getText()) == null) {
					dispose();
					new Character(id.getText(), characterName.getText());
				}

				else {// ���̵� �ߺ��ϰ��
					lError.setText(loginDAO.register(id.getText(), Pass1.getText(), UserName.getText()));
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (s.equals("���")) {
			// System.exit(0);
			dispose();
		} else if (s.equals("�ߺ�Ȯ��")) {
			try {// ���̵� �ߺ��� ���
				if (loginDAO.duplicate(id.getText())) {
					lDuplicate.setText("ID�ߺ��Դϴ�.");
				} else {// ���̵� �ߺ� �ƴ� ���
					lDuplicate.setText("��밡���� ID�Դϴ�.");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}
