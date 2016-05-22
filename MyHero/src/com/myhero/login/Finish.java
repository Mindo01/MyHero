package com.myhero.login;
import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
//회원가입 완료 화면
public class Finish extends JFrame {

	JLabel label1, label2, label3, image1;
	String characterName;// 캐릭터이름
	int type;

	public Finish(String characterName, int type) {
		this.characterName = characterName;
		this.type = type;
		Container c = this.getContentPane();
		setTitle("가입완료");
		setSize(400, 250);
		setLayout(null);
		c.setBackground(new Color(0, 112, 192));
		createUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void createUI() {
		label1 = new JLabel("회원가입이 완료되었습니다.");
		label1.setForeground(Color.white);
		label1.setBounds(30, 10, 180, 100);
		add(label1);

		label2 = new JLabel("LV.1");
		label2.setForeground(Color.white);
		label2.setBounds(30, 40, 180, 100);
		add(label2);

		label3 = new JLabel(characterName);
		label3.setForeground(Color.white);
		label3.setBounds(80, 40, 180, 100);
		add(label3);

		// switch문 이상함............
		//int type = 1;
		switch (type) {
		case 1:
			// ImageIcon i1 = new ImageIcon("images/herk.png");
			image1 = new JLabel(new ImageIcon("images/herk.png"));
			image1.setLocation(200, 20);
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
