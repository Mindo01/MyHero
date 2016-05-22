package com.myhero.login;

import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//로그인 성공 뒤 화면 
public class After extends JFrame {

	JLabel label1, label2, label3, image1;
	User user = null;

	After(String id) throws Exception {
		Container c = this.getContentPane();
		LoginDAO loginDAO = new LoginDAO();
		//로그인 한 id에 데이터를 user 객처에 저장함.
		user = loginDAO.selectById(id);
		setTitle("가입완료");
		setSize(400, 250);
		setLayout(null);
		c.setBackground(new Color(0, 112, 192));
		createUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void createUI() {
		label1 = new JLabel(user.getId());
		label1.setForeground(Color.white);
		label1.setBounds(30, 10, 180, 100);
		add(label1);

		label2 = new JLabel(user.getLevel() + "");
		label2.setForeground(Color.white);
		label2.setBounds(30, 40, 180, 100);
		add(label2);

		label3 = new JLabel(user.getName());
		label3.setForeground(Color.white);
		label3.setBounds(80, 40, 180, 100);
		add(label3);

		//이상해......
		switch (user.getType()) {
		case 1:
			ImageIcon i1 = new ImageIcon("images/herk.png");
			image1 = new JLabel(i1);
			image1.setLocation(20, 120);
			image1.setSize(100, 100);
			add(image1);
			break;
		case 2:
			ImageIcon i2 = new ImageIcon("images/herk.png");
			image1 = new JLabel(i2);
			image1.setLocation(20, 120);
			image1.setSize(100, 100);
			add(image1);
			break;
		case 3:
			ImageIcon i3 = new ImageIcon("images/herk.png");
			image1 = new JLabel(i3);
			image1.setLocation(20, 120);
			image1.setSize(100, 100);
			add(image1);
			break;
		case 4:
			ImageIcon i4 = new ImageIcon("images/herk.png");
			image1 = new JLabel(i4);
			image1.setLocation(20, 120);
			image1.setSize(100, 100);
			add(image1);
			break;
		default:

		}
	}

}
