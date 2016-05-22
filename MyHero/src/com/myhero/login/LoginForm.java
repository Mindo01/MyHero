package com.myhero.login;

import java.awt.Color;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.myhero.register.*;
import com.myhero.widget.*;

public class LoginForm extends JFrame implements ActionListener {

	JLabel id, pass, label;// ���̵�,��й�ȣ,�α����Ҷ� ����ġ ���� ��Ȳ���� ������.
	JTextField tId;// ���̵� �Է�â
	JPasswordField pw;// ��й�ȣ �Է�â
	JButton login, register;// �α��� ��ư,ȸ������ ��ư

	LoginForm() {
		Container c = this.getContentPane();
		setTitle("�α��� ȭ��");
		setSize(400, 250);
		setLayout(null);
		c.setBackground(new Color(0, 112, 192));
		createUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void createUI() {
		id = new JLabel("ID : ");
		id.setForeground(Color.white);
		id.setLocation(30, 30);
		id.setSize(100, 30);
		add(id);

		tId = new JTextField();
		tId.setLocation(80, 30);
		tId.setSize(210, 30);
		add(tId);

		pass = new JLabel("PW : ");
		pass.setForeground(Color.white);
		pass.setLocation(30, 80);
		pass.setSize(100, 30);
		add(pass);

		pw = new JPasswordField();
		pw.setLocation(80, 80);
		pw.setSize(210, 30);
		add(pw);

		login = new JButton("�α���");
		login.setBackground(Color.lightGray);
		login.setLocation(80, 140);
		login.setSize(100, 20);
		login.addActionListener(this);
		add(login);

		register = new JButton("ȸ������");
		register.setBackground(Color.lightGray);
		register.setLocation(190, 140);
		register.setSize(100, 20);
		register.addActionListener(this);
		add(register);

		label = new JLabel(" ");
		label.setForeground(Color.white);
		label.setLocation(80, 100);
		label.setSize(150, 50);
		add(label);

	}

	public static void main(String[] args) {

		new LoginForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		if (s == "�α���") {
			LoginDAO login = new LoginDAO();
			try {
				// �α��� �Ȱ��
				if (login.validate(tId.getText(), pw.getText()) == null) {
					dispose();
					//new After(tId.getText());
					new WidgetFrame();

				}
				// �α��� �ȵ� ���
				else if (login.validate(tId.getText(), pw.getText()) != null) {
					pw.setText("");
					label.setText(login.validate(tId.getText(), pw.getText()));
				}
				
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (s == "ȸ������") {//ȸ������ ��ư Ŭ����

			new Register();
		}
	}

}